package org.dreamcat.daily.juc.on2007;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Create by tuke on 2020/7/18
 * <p>
 * wait       running -> waiting, add current thread to the waiting queue notify     waiting ->
 * blocked, move one waiting thread to synchronized queue
 */
public class On18_wait_notify {

    private static final Object lock = new Object();
    static boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.printf("%s flag is true, wait @ %s\n",
                                Thread.currentThread(),
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        // release lock
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.printf("%s flag is false, running @ %s\n",
                        Thread.currentThread(),
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }, "Wait").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.printf("%s hold lock, notify @ %s\n",
                        Thread.currentThread(),
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                // release lock
                lock.notify();
                flag = false;

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.printf("%s hold lock again, sleep @ %s\n",
                        Thread.currentThread(),
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Notify").start();
    }
}
