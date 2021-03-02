package org.dreamcat.daily.leetcode.on2011;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by tuke on 2020/11/9
 * <p/>
 * 乐观锁CAS实现，非阻塞式
 */
public class On08_the_dining_philosophers__cas {

    static class DiningPhilosophers {

        // 哲学家数量
        private final int philosopherSize;
        // 轮数，每一轮，每个哲学家都会吃到一次
        private final int times;
        // 叉子占位锁
        private final AtomicInteger[] locks;
        // 标识在一轮抢食中是否已吃到
        private final BitSet eaten;

        private final AtomicInteger cost;

        public DiningPhilosophers(int philosopherSize, int times) {
            this.philosopherSize = philosopherSize;
            this.times = times;
            this.locks = new AtomicInteger[philosopherSize];
            this.eaten = new BitSet(philosopherSize);
            this.cost = new AtomicInteger(0);

            for (int i = 0; i < philosopherSize; i++) {
                locks[i] = new AtomicInteger(0);
            }
        }

        public boolean isFinished() {
            return cost.get() == times;
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(
                int philosopher, // 哲学家的编号,0-4
                Runnable pickLeftFork,
                Runnable pickRightFork,
                Runnable eat,
                Runnable putLeftFork,
                Runnable putRightFork) {
            // 本轮已吃，则自动放弃；或已达到轮数
            if (eaten.get(philosopher) || isFinished()) {
                return;
            }
            // 如果左边的左边的叉子被拿起，则放弃拿左边的叉子
            var leftLeftFork = locks[philosopher - 1 < 0 ? philosopherSize - 1 : philosopher - 1];
            var leftFork = locks[philosopher];
            var rightFork = locks[(philosopher + 1) % philosopherSize];

            // 争抢左边的叉子
            if (leftLeftFork.get() == 0 && leftFork.compareAndSet(0, 1)) {
                pickLeftFork.run();
            } else {
                Thread.yield(); // to avoid race condition
                return;
            }

            // 争抢右边的叉子
            if (rightFork.compareAndSet(0, 1)) {
                pickRightFork.run();
                eat.run();
                eaten.set(philosopher);
                if (eaten.cardinality() == philosopherSize) {
                    eaten.clear();
                    if (cost.incrementAndGet() == times) {
                        return;
                    }
                }
                putRightFork.run();
                rightFork.compareAndSet(1, 0);
            }

            putLeftFork.run();
            leftFork.compareAndSet(1, 0);
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

    private static void randomRun(int philosopherSize, int times) {
        var random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();

        var diningPhilosophers = new DiningPhilosophers(philosopherSize, times);
        while (!diningPhilosophers.isFinished()) {
            executorService.submit(() -> {
                final int philosopher = random.nextInt(philosopherSize);
                diningPhilosophers.runWantsToEat(philosopher);
            });
        }
        executorService.shutdown();
    }

    // philosopherSize 个线程的竞争
    private static void raceRun(int philosopherSize, int times) {
        var diningPhilosophers = new DiningPhilosophers(philosopherSize, times);
        for (int i = 0; i < philosopherSize; i++) {
            int philosopher = i;
            new Thread(() -> {
                while (!diningPhilosophers.isFinished()) {
                    diningPhilosophers.runWantsToEat(philosopher);
                }
            }).start();
        }

        while (!diningPhilosophers.isFinished()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("竞态竞争-非阻塞式");
        raceRun(5, 2);
        System.out.println("\n\n\n");
        System.out.println("随机竞争-非阻塞式");
        randomRun(5, 2);
    }
}
