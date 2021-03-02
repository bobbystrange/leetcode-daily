package org.dreamcat.leetcode.daily.on2011;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Create by tuke on 2020/11/11
 * <p/>
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 */
public class On09_print_foobar_alternately {

    static class FooBar {

        private final int n;

        private final Semaphore fooSem = new Semaphore(1);
        private final Semaphore barSem = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                fooSem.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barSem.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barSem.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooSem.release();
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;

        var latch = new CountDownLatch(2);
        var fooBar = new FooBar(n);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
