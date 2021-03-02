package org.dreamcat.daily.juc.on2011;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Create by tuke on 2020/11/13
 */
public class On13_synchronous {

    static class MarkWord {

        // 是否是偏向锁，0，1
        private int biased = 0;
        // 00 轻量级，10 重量级，11 GC标识，01 无锁或偏向锁(区别在于biased是否为1)
        private int lockFlag = 0b01;

        // 保存线程栈上的锁记录
        private AtomicReference<MarkWord> lockRecord = new AtomicReference<>(null);
        // 重量级锁的互斥量
        private Semaphore mutex = new Semaphore(1);
        // 线程ID
        private AtomicLong threadId = new AtomicLong(0);

        private void monitorEnterAndExit(long id, Consumer<String> synchronizedBlock)
                throws InterruptedException {
            for (; ; ) {
                if (biased == 0 && lockFlag == 0b01) {
                    if (threadId.compareAndSet(0, id)) {
                        // 执行同步代码
                        synchronizedBlock.accept("Free");
                        return;
                    } else {
                        biased = 1;
                    }
                }
                // 访问Mark Word中偏向锁的标识是否设置成1，锁标志位是否为01
                if (biased == 1 && lockFlag == 0b01) {
                    // 可偏向状态，则测试线程ID是否指向当前线程
                    long oldId;
                    if ((oldId = threadId.get()) == id) {
                        // 执行同步代码
                        synchronizedBlock.accept("Biased");
                        return;
                    }
                    // 线程ID并未指向当前线程，则通过CAS操作竞争锁
                    else {
                        if (threadId.compareAndSet(oldId, id)) {
                            // 竞争成功，则将Mark Word中线程ID设置为当前线程ID
                            // 执行同步代码
                            synchronizedBlock.accept("Biased CAS");
                            return;
                        }
                        // CAS获取偏向锁失败，则表示有竞争。
                        else {
                            // 当到达全局安全点（safe point）时获得偏向锁的线程被挂起，偏向锁升级为轻量级锁，
                            // 然后被阻塞在安全点的线程继续往下执行同步代码
                            biased = 0;
                            lockFlag = 0b00;
                        }
                    }
                }
                // 轻量级锁
                else if (lockFlag == 0b00) {
                    // 在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间，
                    // 用于存储锁对象目前的Mark Word的拷贝
                    MarkWord displacedMarkWord = copy();
                    // 使用CAS操作尝试将对象的Mark Word更新为指向Lock Record的指针
                    for (int i = 0; i++ < 10; ) {
                        if (lockRecord.compareAndSet(null, displacedMarkWord)) {
                            // 执行同步代码
                            synchronizedBlock.accept("Lightweight");

                            //
                            return;
                        }
                    }
                    // 更新操作
                    lockFlag = 0b10;
                } else if (lockFlag == 0b10) {
                    // 重量级锁，
                    mutex.acquire();
                    // 执行同步代码
                    synchronizedBlock.accept("Heavyweight");
                    mutex.release();

                    // 替换为原有的mark words头部
                    clearMutex();
                    return;
                }
            }
        }

        protected synchronized MarkWord copy() {
            MarkWord markWord = new MarkWord();
            markWord.biased = biased;
            markWord.lockFlag = lockFlag;
            markWord.threadId = new AtomicLong(threadId.get());
            markWord.lockRecord = new AtomicReference<>(lockRecord.get());
            markWord.mutex = new Semaphore(mutex.availablePermits());
            return markWord;
        }

        private synchronized void clearMutex() {
            biased = 1;
            lockFlag = 0b01;
            threadId.set(0);
            lockRecord.set(null);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        MarkWord markWord = new MarkWord();

        var latch = new CountDownLatch(n);
        for (int k = 1; k <= n; k++) {
            new Thread(() -> {
                Thread currentThread = Thread.currentThread();
                String threadName = currentThread.getName();
                long threadId = currentThread.getId();
                System.out.printf("begin %s(%d)\n", threadName, threadId);
                for (int i = 0; i < 10; i++) {
                    int finalI = i;
                    try {
                        markWord.monitorEnterAndExit(threadId, type -> System.out.printf(
                                "%s do action-%d: %s\n", threadName, finalI, type));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
                System.out.printf("end %s(%d)\n", threadName, threadId);
            }, "Thread-" + k).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end all");
    }


}
