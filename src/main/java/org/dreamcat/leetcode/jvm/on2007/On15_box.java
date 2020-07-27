package org.dreamcat.leetcode.jvm.on2007;

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
