package org.dreamcat.daily.leetcode.on2011;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by tuke on 2020/11/9
 * <p>
 * https://leetcode-cn.com/problems/the-dining-philosophers/
 * <p/>
 * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子） 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，
 * 而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。 只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿； 也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 */
public class On08_the_dining_philosophers {

    static class DiningPhilosophers {

        // 哲学家数量
        private final int philosopherSize;
        // 每次尝试获取锁的次数
        private final int tryTimes;
        // 叉子占位锁
        private final ReentrantLock[] locks;

        public DiningPhilosophers(int philosopherSize, int tryTimes) {
            this.philosopherSize = philosopherSize;
            this.tryTimes = tryTimes;
            this.locks = new ReentrantLock[philosopherSize];

            for (int i = 0; i < philosopherSize; i++) {
                locks[i] = new ReentrantLock();
            }
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(
                int philosopher, // 哲学家的编号,0-4
                Runnable pickLeftFork,
                Runnable pickRightFork,
                Runnable eat,
                Runnable putLeftFork,
                Runnable putRightFork) {
            var leftLeftFork = locks[philosopher - 1 < 0 ? philosopherSize - 1 : philosopher - 1];
            var leftFork = locks[philosopher];
            var rightFork = locks[(philosopher + 1) % philosopherSize];

            for (; ; ) {
                // 如果左边的左边的叉子被其他人拿起，则放弃拿左边的叉子，去争抢右边的叉子
                if (leftLeftFork.isLocked()) {
                    rightFork.lock();
                    try {
                        pickRightFork.run();
                        // 已有右边的叉子，再去争抢左边的叉子
                        for (int i = 0; i < tryTimes; i++) {
                            if (leftFork.tryLock()) {
                                try {
                                    // 成功争抢到左边的叉子
                                    pickLeftFork.run();
                                    eat.run();
                                    return;
                                } finally {
                                    leftFork.unlock();
                                    putLeftFork.run();
                                }

                            }
                        }
                    } finally {
                        rightFork.unlock();
                        putRightFork.run();
                    }
                }
                // 左边的左边的叉子没有被其他人拿起，则争抢左边的叉子
                else {
                    leftFork.lock();
                    try {
                        pickLeftFork.run();
                        // 已有左边的叉子，再去争抢右边的叉子
                        for (int i = 0; i < tryTimes; i++) {
                            if (rightFork.tryLock()) {
                                try {
                                    // 成功争抢到右边的叉子
                                    pickRightFork.run();
                                    eat.run();
                                    return;
                                } finally {
                                    rightFork.unlock();
                                    putRightFork.run();
                                }
                            }
                        }
                    } finally {
                        leftFork.unlock();
                        putLeftFork.run();
                    }
                }
                Thread.yield(); // to avoid race condition
            }
        }

        public void runWantsToEat(int philosopher) {
            wantsToEat(
                    philosopher,
                    () -> {
                        System.out.printf("%d pick left fork\n", philosopher);
                    }, () -> {
                        System.out.printf("%d pick right fork\n", philosopher);
                    }, () -> {
                        System.out.printf("%d eat\n", philosopher);
                    }, () -> {
                        System.out.printf("%d put left fork\n", philosopher);
                    }, () -> {
                        System.out.printf("%d put right fork\n", philosopher);
                    });
        }
    }

    // philosopherSize 个线程的竞争
    private static void raceRun(int philosopherSize, int tryTimes, int times) {
        var latch = new CountDownLatch(philosopherSize * times);
        var diningPhilosophers = new DiningPhilosophers(philosopherSize, tryTimes);
        for (int i = 0; i < philosopherSize; i++) {
            int philosopher = i;
            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    diningPhilosophers.runWantsToEat(philosopher);
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("竞态竞争-阻塞式");
        raceRun(5, 3, 10);
    }
}
