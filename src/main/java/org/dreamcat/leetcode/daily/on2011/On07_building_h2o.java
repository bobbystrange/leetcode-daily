package org.dreamcat.leetcode.daily.on2011;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Create by tuke on 2020/11/7
 * <p>
 * https://leetcode-cn.com/problems/building-h2o/
 */
public class On07_building_h2o {

    // 不能使用固定大小的线程池，因为线程会挂起
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    static class H2O {

        private final Semaphore h = new Semaphore(2);
        private final Semaphore o = new Semaphore(0);

        // h(2), o(0) at the beginning
        // Task1: h(2) - 1 and o(0) + 1  -->  h(1), o(1)
        // Task2: h(1) - 1 and o(1) + 1  -->  h(0), o(2)
        public void hydrogen(Runnable releaseHydrogen, int id) throws InterruptedException {
            System.out.printf("[%d] h(%d) acquire 1\n", id, h.availablePermits());
            h.acquire(1);
            System.out.printf("[%d] h(%d) acquired 1\n", id, h.availablePermits());

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

            System.out.printf("[%d] o(%d) release 1\n", id, o.availablePermits());
            o.release(1);
            System.out.printf("[%d] o(%d) released 1\n", id, o.availablePermits());
        }

        // Task3: o(2) - 2 and h(0) + 2  --> h(2), o(0)
        // h(2), o(0) at the end, so be the loop
        public void oxygen(Runnable releaseOxygen, int id) throws InterruptedException {
            System.out.printf("[%d] o(%d) acquire 2\n", id, o.availablePermits());
            o.acquire(2);
            System.out.printf("[%d] o(%d) acquired 2\n", id, o.availablePermits());

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();

            System.out.printf("[%d] h(%d) release 2\n", id, h.availablePermits());
            h.release(2);
            System.out.printf("[%d] h(%d) released 2\n", id, h.availablePermits());
        }

        public void execute(String cmd) {
            for (int i = 0, size = cmd.length(); i < size; i++) {
                int finalI = i;
                char c = cmd.charAt(i);
                if (c == 'H') {
                    executorService.submit(() -> {
                        try {
                            this.hydrogen(() -> System.out.printf("<%d>H\n", finalI), finalI);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else if (c == 'O') {
                    executorService.submit(() -> {
                        try {
                            this.oxygen(() -> System.out.printf("<%d>O\n", finalI), finalI);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    throw new IllegalArgumentException("only 'H' and 'O' is permitted");
                }
            }
        }

        public void shutdown() {
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        H2O h20 = new H2O();
        h20.execute("HOH");
        h20.execute("OOHHHH");
        h20.execute("HHHHHHOOO");
        h20.shutdown();
    }
}
