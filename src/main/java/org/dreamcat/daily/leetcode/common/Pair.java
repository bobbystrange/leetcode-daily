package org.dreamcat.daily.leetcode.common;

import lombok.ToString;

/**
 * Create by tuke on 2021/1/6
 */

@ToString
public class Pair<T1, T2> {

    public final T1 _1;
    public final T2 _2;

    public Pair(T1 t1, T2 t2) {
        _1 = t1;
        _2 = t2;
    }
}
