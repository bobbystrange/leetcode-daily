package org.dreamcat.daily.jvm.on2007;

import org.openjdk.jol.info.ClassLayout;

/**
 * Create by tuke on 2020/7/16
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main
 * org.dreamcat.leetcode.jvm.on2007.On16_synchronized
 * <p>
 * object head 1. mark word, 25bit hashCode + 4bit generation + 1bit biased + 2bit lock flag
 * <pre>
 *   30bit ptr-to-lock-of-stack                                       + 2bit 00, lightweight lock
 *   30bit ptr-to-mutex                                               + 2bit 10, heavyweight lock
 *   30bit empty data                                                 + 2bit 11, GC mark
 *   23bit thread id + 2 bit Epoch + 4bit generation + 1bit 1, biased + 2bit 01 biased lock
 *   25bit hashCode                + 4bit generation + 1bit 0, biased + 2bit 01 no lock
 * </pre>
 * <pre>
 *     -XX:BiasedLockingStartupDelay=0 to shutdown the delay of biased lock
 *     -XX:UseBiasedLocking=false to disable biased lock
 *
 *     1. enter synchronized block
 *     2. check object mark_word whether stored the thread id
 *     3. if no store thread id, then CAS it,
 *          then if works, execute the synchronized body,
 *          else,
 * </pre>
 * 2. class metadata address 3. array length
 */
public class On16_synchronized {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

}
