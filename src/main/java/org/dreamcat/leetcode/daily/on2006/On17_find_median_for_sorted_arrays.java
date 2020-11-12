package org.dreamcat.leetcode.daily.on2006;

/**
 * Create by tuke on 2020/6/17
 */
public class On17_find_median_for_sorted_arrays {

    public static void main(String[] args) {
        // 1, 2, 3, 4, 5, 6, 7, 8, 9
        var a = new int[]{1, 4, 6, 7};
        var b = new int[]{2, 3, 5, 8, 9};
        System.out.println(findMedianForSortedArrays(a, b));
    }

    public static double findMedianForSortedArrays(int[] a, int[] b) {
        int total = a.length + b.length;
        if (total % 2 == 1) {
            return findKth(a, 0, b, 0, total / 2 + 1);
        } else {
            int left = findKth(a, 0, b, 0, total / 2);
            int right = findKth(a, 0, b, 0, total / 2 + 1);
            return (left + right) / 2.0;
        }
    }

    // find k-th element from two sorted array
    public static int findKth(int[] a, int i, int[] b, int j, int k) {
        if (a.length - i > b.length - j) {
            return findKth(b, i, a, i, k);
        }
        // if a is empty, then return the median of b
        if (i == a.length) {
            return b[j + k - 1];
        }
        if (k == 1) {
            return Math.min(a[i], b[j]);
        }

        int si = Math.min(a.length, i + k / 2);
        int sj = Math.min(b.length, j + k - k / 2);

        if (a[si - 1] > a[sj - 1]) {
            return findKth(a, i, b, sj, k - (sj - j));
        } else {
            return findKth(a, si, b, j, k - (si - i));
        }
    }
}
