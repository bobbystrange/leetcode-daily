package org.dreamcat.leetcode.jvm.on2007;

/**
 * Create by tuke on 2020/7/6
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main org.dreamcat.leetcode.jvm.on2007.On06_instruction_cmp
 */
public class On06_instruction_cmp {

    public void cmp(int i) {
        //         0: iload_1
        //         1: ifle          10
        //         4: iload_1
        //         5: ineg
        //         6: istore_1
        //         7: goto          14
        //        10: iload_1
        //        11: iconst_2
        //        12: imul
        //        13: istore_1
        if (i > 0) {
            i = -i;
        } else {
            i *= 2;
        }

        //        14: iload_1
        //        15: bipush        10
        //        17: if_icmple     27
        //        20: iload_1
        //        21: iload_1
        //        22: imul
        //        23: istore_1
        //        24: goto          30
        //        27: iinc          1, -10
        //        30: iload_1
        if (i > 10) {
            i = i * i;
        } else {
            i -= 10;
        }

        //        31: sipush        -1024
        //        34: if_icmpge     44
        //        37: iload_1
        //        38: iload_1
        //        39: imul
        //        40: istore_1
        //        41: goto          60
        //        44: iload_1
        //        45: sipush        512
        //        48: if_icmpgt     57
        //        51: iinc          1, -1
        //        54: goto          60
        //        57: iinc          1, 1
        //        60: return
        if (i < -1024) {
            i = i * i;
        } else if (i <= 512) {
            i--;
        } else {
            i++;
        }
    }

}
