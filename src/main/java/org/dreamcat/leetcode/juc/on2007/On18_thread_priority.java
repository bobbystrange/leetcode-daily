package org.dreamcat.leetcode.juc.on2007;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

/**
 * Create by tuke on 2020/7/18
 */
public class On18_thread_priority {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    // jstack $(jps | grep On18_thread_priority | awk '{print $1}')
    public static void main(String[] args) throws InterruptedException {
        var jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            var job = new Job(priority);
            jobs.add(job);
            var thread = new Thread(job, "Job-" + i);
            // Note all threads have same the priority
            // "Job-0" #14 prio=1 os_prio=31 cpu=11174.08ms elapsed=16.41s
            // "Job-9" #23 prio=10 os_prio=31 cpu=11255.29ms elapsed=16.41s
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(30);
        notEnd = false;
        for (var job : jobs) {
            System.out.printf("Job priority: %2d, Count: %d\n", job.priority, job.count);
        }
    }

    @RequiredArgsConstructor
    static class Job implements Runnable {

        final int priority;
        long count;

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                count++;
            }
        }
    }
}
