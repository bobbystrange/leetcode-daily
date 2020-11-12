package org.dreamcat.leetcode.daily.on2011;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

/**
 * Create by tuke on 2020/11/11
 * <p>
 * https://leetcode-cn.com/problems/fizz-buzz-multithreaded/ 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。 如果这个数字可以被 5 整除，输出"buzz"。 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。 例如，当n =
 * 15，输出：1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 请你实现一个有四个线程的多线程版FizzBuzz，同一个FizzBuzz实例会被如下四个线程使用：
 * <p>
 * 线程A将调用fizz()来判断是否能被 3 整除，如果可以，则输出fizz。 线程B将调用buzz()来判断是否能被 5 整除，如果可以，则输出buzz。
 * 线程C将调用fizzbuzz()来判断是否同时能被 3 和 5 整除，如果可以，则输出fizzbuzz。 线程D将调用number()来实现输出既不能被 3 整除也不能被 5 整除的数字。
 */
public class On11_fizz_buzz_multithreaded {

    static class FizzBuzz {

        private final int n;
        // 0 for num, 3 for fizz, 5 for buzz, 15 for fizzbuzz
        private volatile int state = 0;

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i += 3) {
                if (stateOf(i) != 3) {
                    continue;
                }
                while (state != 3) {
                    Thread.yield();
                }
                printFizz.run();
                state = stateOf(i + 1);
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i += 5) {
                if (stateOf(i) != 5) {
                    continue;
                }
                while (state != 5) {
                    Thread.yield();
                }
                printBuzz.run();
                state = stateOf(i + 1);
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i += 15) {
                while (state != 15) {
                    Thread.yield();
                }
                printFizzBuzz.run();
                state = 0;
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (stateOf(i) != 0) {
                    continue;
                }
                while (state != 0) {
                    Thread.yield();
                }
                printNumber.accept(i);
                state = stateOf(i + 1);
            }
        }

        private int stateOf(int i) {
            if (i % 15 == 0) {
                return 15;
            } else if (i % 3 == 0) {
                return 3;
            } else if (i % 5 == 0) {
                return 5;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        int n = 16;

        FizzBuzz fizzBuzz = new FizzBuzz(n);

        var latch = new CountDownLatch(4);
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz, "));
                System.out.print("<fizz end>, ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz, "));
                System.out.print("<buzz end>, ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz, "));
                System.out.print("<fizzbuzz end>, ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(i -> System.out.print(i + ", "));
                System.out.print("<number end>, ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
