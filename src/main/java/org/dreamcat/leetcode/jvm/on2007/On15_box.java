package org.dreamcat.leetcode.jvm.on2007;

import org.openjdk.jol.info.ClassLayout;

/**
 * Create by tuke on 2020/7/15
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main org.dreamcat.leetcode.jvm.on2007.On15_box
 */
public class On15_box {
    int a;

    On15_box(int a) {
        this.a = a;
    }

    // -XX:-UseCompressedOops, default is -XX:+UseCompressedOops
    public static void main(String[] args) {
        class A {

        }
        class B {
            long s;
        }
        class C {
            // field rearrangement to <a, s> caused by 'alignment/padding gap'
            long s;
            int a;
        }

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

    public static void move() {
        On15_box box = new On15_box(randi(100));
        int i = 0;
        new Thread(() -> {
            int b = box.a;
            System.out.println(b);
            System.out.println(i);
        }).start();
    }

    private static int randi(int bound) {
        return (int) Math.floor(Math.random() * bound);
    }


}
