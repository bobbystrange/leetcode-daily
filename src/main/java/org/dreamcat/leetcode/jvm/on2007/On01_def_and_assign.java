package org.dreamcat.leetcode.jvm.on2007;

/**
 * Create by tuke on 2020/7/1
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main
 * org.dreamcat.leetcode.jvm.on2007.On01_def_and_assign
 */
public class On01_def_and_assign {

    public void def() {
        int n;
    }

    public void def2() {
        int n;
        String s;
    }

    public void def_and_assign() {
        /*
         0: iconst_1
         1: istore_1
         2: return
         */
        int n;
        n = 1;
    }

    public void def_and_assign2() {
        int n = 1;
        int i = 127;
        double d = 3.14;
        String s = "abc";
    }

}
