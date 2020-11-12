package org.dreamcat.leetcode.juc.on2007;

import java.util.concurrent.TimeUnit;

/**
 * Create by tuke on 2020/7/18
 * <p>
 * <pre>
 *                        [new]
 *                          |
 *                      Thread.start()
 *                          |
 *                      [runnable]
 *                  running yield to ready
 *              /         /         \                \
 * Object.wait()     [terminal]  synchronized         Thread.sleep(long)
 * Thread.join()                  [blocked]           Object.wait(long)
 * LockSupport.park()            enter synchronized   Thread.join(long)
 * [waiting]                                          LockSupport.parkNanos()
 * Object.notify()                                    LockSupport.parkUntil()
 * Object.notifyAll()                                 []
 * LockSupport.unpark(Thread)                         Object.notify()
 *                                                    Object.notifyAll()
 *                                                    LockSupport.unpark(Thread)
 *
 * </pre>
 */
public class On18_thread_state {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    // jstack $(jps | grep On18_thread_state | awk '{print $1}')
    public static void main(String[] args) {
        // "TimeWaiting" #14 prio=5 os_prio=31 cpu=0.14ms elapsed=17.64s tid=0x00007fb28881d800 nid=0x6203 waiting on condition  [0x00007000037bc000]
        //    java.lang.Thread.State: TIMED_WAITING (sleeping)
        //
        // "Waiting" #15 prio=5 os_prio=31 cpu=0.12ms elapsed=17.64s tid=0x00007fb28881e800 nid=0x6403 in Object.wait()  [0x00007000038bf000]
        //    java.lang.Thread.State: WAITING (on object monitor)
        //
        // "Blocked" #16 prio=5 os_prio=31 cpu=0.12ms elapsed=17.64s tid=0x00007fb288054000 nid=0x6503 waiting on condition  [0x00007000039c2000]
        //    java.lang.Thread.State: TIMED_WAITING (sleeping)
        //
        // "Blocked" #17 prio=5 os_prio=31 cpu=1.48ms elapsed=17.64s tid=0x00007fb28881f000 nid=0x6803 waiting for monitor entry  [0x0000700003ac5000]
        //    java.lang.Thread.State: BLOCKED (on object monitor)
        new Thread(() -> {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(30);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "TimeWaiting").start();

        new Thread(() -> {
            try {
                while (true) {
                    synchronized (lock1) {
                        lock1.wait();
                    }
                    TimeUnit.SECONDS.sleep(30);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Waiting").start();

        new Thread(() -> {
            try {
                synchronized (lock2) {
                    while (true) {
                        TimeUnit.SECONDS.sleep(30);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Blocked").start();
        new Thread(() -> {
            try {
                synchronized (lock2) {
                    while (true) {
                        TimeUnit.SECONDS.sleep(30);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Blocked").start();
    }
}
