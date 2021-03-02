package org.dreamcat.leetcode.daily.on2011;

/**
 * Create by tuke on 2020/11/3
 * <p/>
 * https://leetcode-cn.com/problems/valid-mountain-array/
 */
public class On03_valid_mountain_array {

    public static boolean validMountainArray(int[] A) {
        var len = A.length;
        if (len < 3) {
            return false;
        }
        var start = A[0];
        var previous = start;
        var turnAround = false;
        for (int i = 1; i < len; i++) {
            int current = A[i];
            if (current == previous) {
                return false;
            }

            if (turnAround) {
                if (current > previous) {
                    return false;
                }
            } else {
                if (current < previous) {
                    if (previous == start) {
                        return false;
                    }
                    turnAround = true;
                }
            }
            previous = current;
        }
        return turnAround;
    }

    public static void main(String[] args) {
        System.out.println(validMountainArray(new int[]{
                2, 1}));
        System.out.println(validMountainArray(new int[]{
                3, 5, 5}));
        System.out.println(validMountainArray(new int[]{
                0, 3, 2, 1}));
        System.out.println(validMountainArray(new int[]{
                9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        System.out.println(validMountainArray(new int[]{
                1, 7, 9, 5, 4, 1, 2}));
        System.out.println(validMountainArray(new int[]{
                3, 7, 20, 14, 15, 14, 10, 8, 2, 1}));
    }
}
