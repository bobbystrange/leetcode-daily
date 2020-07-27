package org.dreamcat.leetcode.jvm.on2007;

/**
 * Create by tuke on 2020/7/16
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main org.dreamcat.leetcode.jvm.on2007.On16_volatile
 * <p>
 * write a volatile variable, then perform the asm code: lock xxx
 * 1. write the cache line of current CPU to system memory
 * 2. invalidate the cache line of other CPUs pointed to the variable address
 * <p>
 * in older processors, lock the bus so that other CPU cannot access the memory
 * intl-64 use <italic>MESI(modify,exclusive，share,invalidate)</italic> <strong>cache lock</strong> to maintain the consistency
 */
public class On16_volatile {

    public static void main(String[] args) {
        //          0: new           #7                  // class org/dreamcat/leetcode/jvm/on2007/On16_volatile$Vol
        //          3: dup
        //          4: invokespecial #9                  // Method org/dreamcat/leetcode/jvm/on2007/On16_volatile$Vol."<init>":()V
        //          7: astore_1
        Vol vol = new Vol();
        //          8: aload_1
        //          9: aload_1
        //         10: getfield      #10                 // Field org/dreamcat/leetcode/jvm/on2007/On16_volatile$Vol.w1:I
        //         13: i2l
        //         14: putfield      #14                 // Field org/dreamcat/leetcode/jvm/on2007/On16_volatile$Vol.w2:J
        vol.w2 = vol.w1;

        //         17: new           #18                 // class org/dreamcat/leetcode/jvm/on2007/On16_volatile$NoVol
        //         20: dup
        //         21: invokespecial #20                 // Method org/dreamcat/leetcode/jvm/on2007/On16_volatile$NoVol."<init>":()V
        //         24: astore_2
        NoVol noVol = new NoVol();
        //         25: aload_2
        //         26: aload_2
        //         27: getfield      #21                 // Field org/dreamcat/leetcode/jvm/on2007/On16_volatile$NoVol.w1:I
        //         30: i2l
        //         31: putfield      #22                 // Field org/dreamcat/leetcode/jvm/on2007/On16_volatile$NoVol.w2:J
        noVol.w2 = noVol.w1;
    }

    static class Vol {
        volatile int w1;
        volatile long w2;
        volatile String s;
    }

    static class NoVol {
        int w1;
        long w2;
        String s;
    }
}
