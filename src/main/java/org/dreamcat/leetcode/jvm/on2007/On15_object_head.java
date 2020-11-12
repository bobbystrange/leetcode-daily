package org.dreamcat.leetcode.jvm.on2007;

import org.openjdk.jol.info.ClassLayout;

/**
 * Create by tuke on 2020/7/16
 */
public class On15_object_head {

    // -XX:-UseCompressedOops, default is -XX:+UseCompressedOops
    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        int[] arr1 = new int[0];
        System.out.println(ClassLayout.parseInstance(arr1).toPrintable());
        int[] arr2 = new int[3];
        System.out.println(ClassLayout.parseInstance(arr2).toPrintable());
    }

    public static void useCompressedOops() {
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$A object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           4b d2 16 00 (01001011 11010010 00010110 00000000) (1495627)
        //      12     4        (loss due to the next object alignment)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        //
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$B object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           50 0e 17 00 (01010000 00001110 00010111 00000000) (1510992)
        //      12     4        (alignment/padding gap)
        //      16     8   long B.s                                       0
        // Instance size: 24 bytes
        // Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
        //
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$C object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           71 0f 17 00 (01110001 00001111 00010111 00000000) (1511281)
        //      12     4    int C.a                                       0
        //      16     8   long C.s                                       0
        // Instance size: 24 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        // [I object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           75 12 07 00 (01110101 00010010 00000111 00000000) (463477)
        //      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      16     0    int [I.<elements>                             N/A
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        // [I object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           75 12 07 00 (01110101 00010010 00000111 00000000) (463477)
        //      12     4        (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
        //      16    12    int [I.<elements>                             N/A
        //      28     4        (loss due to the next object alignment)
        // Instance size: 32 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }

    public static void noUseCompressedOops() {
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$A object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           30 cc 40 08 (00110000 11001100 01000000 00001000) (138464304)
        //      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$B object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           08 c5 a0 08 (00001000 11000101 10100000 00001000) (144753928)
        //      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      16     8   long B.s                                       0
        // Instance size: 24 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        // org.dreamcat.leetcode.jvm.on2007.On15_object_head$C object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           a8 12 cb 08 (10101000 00010010 11001011 00001000) (147526312)
        //      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      16     8   long C.s                                       0
        //      24     4    int C.a                                       0
        //      28     4        (loss due to the next object alignment)
        // Instance size: 32 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        //
        // [I object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           70 5c f9 07 (01110000 01011100 11111001 00000111) (133782640)
        //      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      16     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      20     4        (alignment/padding gap)
        //      24     0    int [I.<elements>                             N/A
        // Instance size: 24 bytes
        // Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
        //
        // [I object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           70 5c f9 07 (01110000 01011100 11111001 00000111) (133782640)
        //      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      16     4        (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
        //      20     4        (alignment/padding gap)
        //      24    12    int [I.<elements>                             N/A
        //      36     4        (loss due to the next object alignment)
        // Instance size: 40 bytes
        // Space losses: 4 bytes internal + 4 bytes external = 8 bytes total
    }
    // Note 16 = 4 mark_word + 4 padding + 4 class_address + 4 array_length
    //      24 = 8 mark_word + 8 class_address + 4 array_length + 4 alignment
    // int[0]
    // Note 32 = 4 mark_word + 4 padding + 4 class_address + 4 array_length + 12 elements + 4 alignment
    //      40 = 8 mark_word + 8 class_address + 4 array_length + 4 padding + 12 elements + 4 alignment
    // int[3]

    // Note 16 =  4 mark_word + 4 padding + 4 class_address + 4 alignment
    //      16 =  8 mark_word + 8 class_address
    static class A {

    }

    // Note 24 = 4 mark_word + 4 padding + 4 class_address + 4 alignment + 8 long
    //      24 = 8 mark_word + 8 class_address + 8 long
    static class B {

        long s;
    }

    // Note 24 = 4 mark_word + 4 padding + 4 class_address + 4 int + 8 long
    //      32 = 8 mark_word + 8 class_address + 8 long + 4 int + 4 alignment
    static class C {

        // field rearrangement to <a, s> caused by 'alignment/padding gap'
        long s;
        int a;
    }
}
