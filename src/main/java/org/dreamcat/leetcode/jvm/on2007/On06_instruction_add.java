package org.dreamcat.leetcode.jvm.on2007;

/**
 * Create by tuke on 2020/7/6
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main
 * org.dreamcat.leetcode.jvm.on2007.On06_instruction_add
 */
public class On06_instruction_add {

    public int iadd() {
        //         0: iconst_5
        //         1: istore_1
        //         2: bipush        12
        //         4: istore_2
        //         5: iload_1
        //         6: iload_2
        //         7: iadd
        //         8: ireturn
        int a = 5;
        int b = 12;
        return a + b;
    }

    public int iadd2() {
        //         0: iconst_5
        //         1: istore_1
        //         2: bipush        12
        //         4: istore_2
        //         5: iload_1
        //         6: iinc          1, 1
        //         9: iload_2
        //        10: iadd
        //        11: istore_3
        //        12: iload_2
        //        13: iload_1
        //        14: iadd
        //        15: istore_2
        //        16: iload_2
        //        17: ireturn
        int a = 5;
        int b = 12;
        int c = a++ + b;
        b += a;
        return b;
    }

    public void isub_imul_idiv_irem() {
        //         0: iconst_5
        //         1: istore_1
        //         2: bipush        12
        //         4: istore_2
        //         5: iload_2
        //         6: iload_1
        //         7: isub
        //         8: istore_3
        //         9: iinc          3, -1
        //        12: iload_3
        //        13: iload_2
        //        14: imul
        //        15: istore_1
        //        16: iload_3
        //        17: iload_1
        //        18: idiv
        //        19: istore_2
        //        20: iload_2
        //        21: iload_1
        //        22: irem
        //        23: istore_3
        //        24: return
        int a = 5;
        int b = 12;
        int c = b - a;
        c--;
        a = c * b;
        b = c / a;
        c = b % a;
    }

    public void dadd() {
        //         0: ldc2_w        #7                  // double 5.0d
        //         3: dstore_1
        //         4: ldc2_w        #9                  // double 12.0d
        //         7: dstore_3
        //         8: dload_1
        //         9: dload_3
        //        10: dadd
        //        11: dstore        5
        //        13: dload         5
        //        15: dload_1
        //        16: dsub
        //        17: dload_3
        //        18: dadd
        //        19: dstore        5
        //        21: dload         5
        //        23: dconst_1
        //        24: dsub
        //        25: dstore        5
        //        27: dload         5
        //        29: dload_3
        //        30: dmul
        //        31: dstore_1
        //        32: dload         5
        //        34: dload_1
        //        35: ddiv
        //        36: dstore_3
        //        37: dload_3
        //        38: dload_1
        //        39: drem
        //        40: dstore        5
        //        42: return
        double a = 5;
        double b = 12;
        double c = a + b;
        c = c - a + b;
        c--;
        a = c * b;
        b = c / a;
        c = b % a;
    }
}
