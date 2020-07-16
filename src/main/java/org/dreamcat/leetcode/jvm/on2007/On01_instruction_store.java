package org.dreamcat.leetcode.jvm.on2007;

/**
 * Create by tuke on 2020/7/1
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main org.dreamcat.leetcode.jvm.on2007.On01_instruction
 */
public class On01_instruction_store {

    // -1 0 1 2 3 4 5, iconst_m1 iconst_0 ... iconst_5
    // bipush [-128, 127]
    // sipush [-32768, 32767]
    // ldc [-2147483648, 214748364], push [int float String] constant to stack head
    // ldc_w push [int float String] constant to stack head via wide point
    // ldc2_w push [long double] constant stack head via wide point
    public void iconst() {
        //         0: iconst_m1
        //         1: istore_1
        //         2: iconst_0
        //         3: istore_1
        //         4: iconst_1
        //         5: istore_1
        //         6: iconst_2
        //         7: istore_1
        //         8: iconst_3
        //         9: istore_1
        //        10: iconst_4
        //        11: istore_1
        //        12: iconst_5
        //        13: istore_1
        int i = -1;
        i = 0;
        i = 1;
        i = 2;
        i = 3;
        i = 4;
        i = 5;

        //        14: bipush        6
        //        16: istore_1
        //        17: bipush        -2
        //        19: istore_1
        //        20: bipush        -128
        //        22: istore_1
        //        23: bipush        127
        //        25: istore_1
        i = 6;
        i = -2;
        i = -128;
        i = 127;

        //        26: sipush        128
        //        29: istore_1
        //        30: sipush        -129
        //        33: istore_1
        //        34: sipush        -32768
        //        37: istore_1
        //        38: sipush        32767
        //        41: istore_1
        i = 128;
        i = -129;
        i = -32768;
        i = 32767;

        //        42: ldc           #7                  // int 32768
        //        44: istore_1
        //        45: sipush        32767
        //        48: istore_1
        //        49: ldc           #10                 // int 2147483647
        //        51: istore_1
        //        52: ldc           #11                 // int -2147483648
        //        54: istore_1
        i = 32768;
        i = 32767;
        i = Integer.MAX_VALUE;
        i = Integer.MIN_VALUE;
    }

    // 0 1, lconst_0 lconst_1 fconst_0 fconst_1 dconst_0 dconst_1
    public void lconst() {
        //         0: ldc2_w        #12                 // long 32768l
        //         3: lstore_1
        long l = 32768;

        //         4: lconst_0
        //         5: lstore_1
        //         6: lconst_1
        //         7: lstore_1
        l = 0;
        l = 1;

        //         8: fconst_0
        //         9: fstore_3
        //        10: fconst_1
        //        11: fstore_3
        float f = 0;
        f = 1;

        //        12: dconst_0
        //        13: dstore        4
        //        15: dconst_1
        //        16: dstore        4
        double d = 0;
        d = 1;
    }

    // istore_1 istore_2 istore_3 istore [n] pop stack head to nth local variable
    // fstore_1 fstore_2 fstore_3
    // lstore_1 lstore_2 lstore_3
    public void istore() {
        //         0: iconst_0
        //         1: istore_1
        //         2: iconst_0
        //         3: istore_2
        //         4: iconst_0
        //         5: istore_3
        //         6: iconst_0
        //         7: istore        4
        int i1, i2, i3, i4;
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;

        //         9: fconst_0
        //        10: fstore        5
        //        12: fconst_0
        //        13: fstore        6
        //        15: fconst_0
        //        16: fstore        7
        //        18: fconst_0
        //        19: fstore        8
        float f1, f2, f3, f4;
        f1 = 0;
        f2 = 0;
        f3 = 0;
        f4 = 0;

        //        21: dconst_0
        //        22: dstore        9
        //        24: dconst_0
        //        25: dstore        11
        //        27: dconst_0
        //        28: dstore        13
        //        30: dconst_0
        //        31: dstore        15
        double d1, d2, d3, d4;
        d1 = 0;
        d2 = 0;
        d3 = 0;
        d4 = 0;

        //        33: lconst_0
        //        34: lstore        17
        //        36: lconst_0
        //        37: lstore        19
        //        39: lconst_0
        //        40: lstore        21
        //        42: lconst_0
        //        43: lstore        23
        long l1, l2, l3, l4;
        l1 = 0;
        l2 = 0;
        l3 = 0;
        l4 = 0;
    }

    public void istore2() {
        //         0: iconst_0
        //         1: istore_1
        //         2: fconst_0
        //         3: fstore_2
        //         4: dconst_0
        //         5: dstore_3
        int i = 0;
        float f = 0;
        double d = 0;
    }

    public void istore3() {
        //         0: lconst_0
        //         1: lstore_1
        //         2: iconst_0
        //         3: istore_3
        long l = 0;
        int i = 0;
    }

    public void istore4() {
        //         0: iconst_1
        //         1: istore_1
        //         2: iconst_0
        //         3: istore_2
        //         4: iconst_0
        //         5: istore_3
        //         6: iconst_0
        //         7: istore        4
        boolean b = true;
        byte by = 0;
        short s = 0;
        char c = '\0';
    }

    // astore_1 astore_2 astore_3 astore [n]
    // iastore lastore fastore dastore aastore bastore
    public void astore() {
        //         0: ldc           #7                  // String abc
        //         2: astore_1
        //         3: aload_1
        //         4: astore_2
        String s1 = "abc";
        String s2 = s1;

        //         5: bipush        16
        //         7: newarray      int
        //         9: astore_3
        //        10: aload_3
        //        11: astore        4
        int[] a1 = new int[16];
        int[] a2 = a1;

        //        13: aload_3
        //        14: iconst_4
        //        15: aload         4
        //        17: bipush        12
        //        19: iaload
        //        20: iastore
        a1[4] = a2[12];
    }
}
