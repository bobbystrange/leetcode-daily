package org.dreamcat.daily.juc.on2009;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Create by tuke on 2020/9/8
 *
 * @see AbstractQueuedSynchronizer
 */
public class On0908_AQS {

    public static void main(String[] args) throws Exception {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (; ; ) {
                System.out.print("Input a number to continue: ");
                String key = reader.readLine();
                System.out.println();
                switch (key) {
                    case "1":
                        countDownLatch();
                        break;
                    case "2":
                        cyclicBarrier();
                        break;
                    case "3":
                        semaphore();
                        break;
                    case "4":
                        phaser();
                        break;
                    case "5":
                        exchanger();
                        break;
                    default:
                        continue;
                }
                return;
            }
        }
    }

    public static void countDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " count down");
            }).start();
        }

        latch.await();
        System.out.println(Thread.currentThread().getName() + " await");
    }

    public static void cyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("the barrier is tripped");
        });

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.printf("[%s] getNumberWaiting %d, getParties %d\n",
                        Thread.currentThread().getName(),
                        barrier.getNumberWaiting(), barrier.getParties());
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void semaphore() {
        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(1);
                    System.out.printf("%s acquire 1 permits, availablePermits is %d\n",
                            Thread.currentThread().getName(), semaphore.availablePermits());
                    semaphore.release(2);
                    System.out.printf("%s release 2 permits, availablePermits is %d\n",
                            Thread.currentThread().getName(), semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(2);
                    System.out.printf("%s acquire 2 permits, availablePermits is %d\n",
                            Thread.currentThread().getName(), semaphore.availablePermits());
                    semaphore.release(1);
                    System.out.printf("%s release 1 permits, availablePermits is %d\n",
                            Thread.currentThread().getName(), semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void phaser() throws InterruptedException {
        Phaser phaser = new Phaser(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                phaser.register();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }

    public static void exchanger() throws InterruptedException {
        int[] bucket = new int[]{1, 2, 3};

        Exchanger<int[]> exchanger = new Exchanger<>();

        exchanger.exchange(bucket);
    }

}
