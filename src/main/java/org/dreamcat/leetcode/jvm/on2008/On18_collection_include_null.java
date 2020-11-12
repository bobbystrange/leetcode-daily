package org.dreamcat.leetcode.jvm.on2008;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;

/**
 * Create by tuke on 2020/8/18
 */
public class On18_collection_include_null {

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        for (int i = 0; i++ < 6; ) {
            a.add(null);
        }
        /*
         java.util.ArrayList object internals:
         OFFSET  SIZE                 TYPE DESCRIPTION                               VALUE
         0     4                      (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
         4     4                      (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         8     4                      (object header)                           44 81 05 00 (01000100 10000001 00000101 00000000) (360772)
         12     4                  int AbstractList.modCount                     6
         16     4                  int ArrayList.size                            6
         20     4   java.lang.Object[] ArrayList.elementData                     [null, null, null, null, null, null, null, null, null, null]
         Instance size: 24 bytes
         Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Object[] data = new Object[6];
        System.out.println(ClassLayout.parseInstance(data).toPrintable());
    }

}
