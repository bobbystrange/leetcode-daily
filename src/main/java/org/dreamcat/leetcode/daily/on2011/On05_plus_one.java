package org.dreamcat.leetcode.daily.on2011;

import java.util.Arrays;

/**
 * Create by tuke on 2020/11/5
 * <p>
 * https://leetcode-cn.com/problems/plus-one/
 */
public class On05_plus_one {

    public static int[] plusOne(int[] digits) {
        var len = digits.length;
        var carry = false;
        for (int i = len - 1; i >= 0; i--) {
            var digit = digits[i];
            if (digit < 9) {
                digits[i]++;
                carry = false;
                break;
            } else {
                digits[i] = 0;
                carry = true;
            }
        }
        if (carry) {
            var newDigits = new int[len + 1];
            newDigits[0] = 1;
            System.arraycopy(digits, 0, newDigits, 1, len);
            return newDigits;
        }
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{
                0})));
        System.out.println(Arrays.toString(plusOne(new int[]{
                1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{
                9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{
                1, 9, 9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{
                1, 9, 8, 9, 0, 9})));
    }
}
