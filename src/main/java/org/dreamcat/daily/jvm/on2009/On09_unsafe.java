package org.dreamcat.daily.jvm.on2009;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Create by tuke on 2020/9/9
 */
public class On09_unsafe {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(Unsafe.class);
        Object n = unsafe.allocateInstance(int.class);
    }
}
