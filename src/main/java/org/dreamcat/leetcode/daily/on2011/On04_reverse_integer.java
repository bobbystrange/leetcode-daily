package org.dreamcat.leetcode.daily.on2011;

/**
 * Create by tuke on 2020/11/5
 * <p>
 * https://leetcode-cn.com/problems/reverse-integer
 */
public class On04_reverse_integer {

    public static int reverse(int x) {
        var buf = new int[10]; // Math.log10(Integer.MAX_VALUE) = 9.xxx
        var offset = 0;
        while (x != 0) {
            var r = x % 10;
            buf[offset++] = r;
            x /= 10;
        }
        var result = 0L;
        int i = 0;
        for (; i < offset; i++) {
            if (buf[i] != 0) {
                break;
            }
        }
        for (var j = i; j < offset; j++) {
            var digest = buf[j];
            result = result * 10 + digest;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(12021));
        System.out.println(reverse(1202100));
        System.out.println(reverse(12021001));
        System.out.println(reverse(1534236469));
        System.out.println(reverse(-2147483648));
    }
}
