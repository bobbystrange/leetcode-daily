package org.dreamcat.leetcode.daily.on2011;

import java.util.concurrent.Semaphore;

/**
 * Create by tuke on 2020/11/11
 * <p/>
 * https://leetcode-cn.com/problems/print-in-order/
 */
public class On10_print_in_order {

    static class Foo {

        private final Semaphore[] sems;

        public Foo(int seqSize) {
            sems = new Semaphore[seqSize];
            sems[0] = new Semaphore(1);
            for (int i = 1; i < seqSize; i++) {
                sems[i] = new Semaphore(0);
            }
        }

        public void first(Runnable printFirst) throws InterruptedException {
            sems[0].acquire();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            sems[1].release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            sems[1].acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            sems[2].release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            sems[2].acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            sems[0].release();
        }
    }
}
