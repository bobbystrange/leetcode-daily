package org.dreamcat.leetcode.juc.on2007;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Create by tuke on 2020/7/18
 */
public class On18_ThreadMXBean {

    public static void main(String[] args) {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = mxBean.dumpAllThreads(false, false);
        // [1] main
        // [2] Reference Handler
        // [3] Finalizer
        // [4] Signal Dispatcher
        // [11] Common-Cleaner
        // [12] Monitor Ctrl-Break
        // [13] Notification Thread
        for (var threadInfo : threadInfos) {
            System.out.printf("[%d] %s\n", threadInfo.getThreadId(), threadInfo.getThreadName());
        }
    }
}
