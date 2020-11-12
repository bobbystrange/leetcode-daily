package org.dreamcat.leetcode.daily.on2011;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

/**
 * Create by tuke on 2020/11/11
 * <p>
 * https://leetcode-cn.com/problems/print-zero-even-odd/
 * <p>
 * 每个线程都有一个 printNumber 方法来输出一个整数。 请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * <p>
 * 输入：n = 2 输出："0102" 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()， 最后一个线程调用odd()。正确的输出为 "0102"。
 */
public class On11_print_zero_even_odd {

    static class ZeroEvenOdd {

        private final int n;
        private volatile int state = 0;
        private volatile boolean flag = true;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                while (state != 0) {
                    Thread.yield();
                }
                printNumber.accept(0);
                if (flag) {
                    state = 1;
                } else {
                    state = 2;
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                while (state != 2) {
                    Thread.yield();
                }
                printNumber.accept(i);
                flag = true;
                state = 0;
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                while (state != 1) {
                    Thread.yield();
                }
                printNumber.accept(i);
                flag = false;
                state = 0;
            }
        }
    }

    public static void main(String[] args) {
        int n = 20;

        var latch = new CountDownLatch(3);
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(i -> System.out.printf("%2d zero\n", i));
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(i -> System.out.printf("%2d  odd\n", i));
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(i -> System.out.printf("%2d even\n", i));
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
