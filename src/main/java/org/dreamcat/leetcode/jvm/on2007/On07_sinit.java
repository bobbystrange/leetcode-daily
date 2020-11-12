package org.dreamcat.leetcode.jvm.on2007;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by tuke on 2020/7/7
 * <p>
 * ./gradlew -x test classes && javap -verbose -cp ./build/classes/java/main
 * org.dreamcat.leetcode.jvm.on2007.On07_sinit
 */
public class On07_sinit {

    public static void main(String[] args) {
        //  #7 = Class              #8             // org/dreamcat/leetcode/jvm/on2007/On07_sinit$B
        //  #8 = Utf8               org/dreamcat/leetcode/jvm/on2007/On07_sinit$B
        // #10 = Fieldref           #7.#11         // org/dreamcat/leetcode/jvm/on2007/On07_sinit$B.version:Ljava/lang/String;
        // #11 = NameAndType        #12:#13        // version:Ljava/lang/String;
        // #12 = Utf8               version
        // #13 = Utf8               Ljava/lang/String;
        //
        //         0: new           #7                  // class org/dreamcat/leetcode/jvm/on2007/On07_sinit$B
        //         3: dup
        //         4: invokespecial #9                  // Method org/dreamcat/leetcode/jvm/on2007/On07_sinit$B."<init>":()V
        //         7: astore_1
        //         8: aload_1
        //         9: getstatic     #10                 // Field org/dreamcat/leetcode/jvm/on2007/On07_sinit$B.version:Ljava/lang/String;
        //        12: invokevirtual #14                 // Method org/dreamcat/leetcode/jvm/on2007/On07_sinit$B.setValue:(Ljava/lang/String;)V
        //        15: return
        B b = new B();
        b.setValue(B.version);
    }

    static class A {

        static String version = "1.0";
    }

    @Setter
    @Getter
    static class B extends A {

        String value;
    }
}
