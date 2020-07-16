package org.dreamcat.leetcode.daily.on2006;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Create by tuke on 2020/6/28
 */
public class On28_merge_sorted_lists {

    @SuppressWarnings({"unchecked"})
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1, 4, 5, 6, 7));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(1, 3, 4));
        System.out.println(mergeSortedLists(new LinkedList[]{a, b}));

        a = new LinkedList<>(Arrays.asList(1, 4, 5, 6, 7));
        b = new LinkedList<>(Arrays.asList(1, 3, 4));
        LinkedList<Integer> c = new LinkedList<>(Arrays.asList(2, 6, 8, 10));
        System.out.println(mergeSortedLists(new LinkedList[]{c, b, a}));
    }

    public static <T extends Comparable<T>> LinkedList<T> mergeSortedLists(LinkedList<T>[] lists) {
        int len = lists.length;
        return mergeSortedLists(lists, len);
    }

    public static <T extends Comparable<T>> LinkedList<T> mergeSortedLists(LinkedList<T>[] lists, int length) {
        return switch (length) {
            case 0 -> new LinkedList<>();
            case 1 -> new LinkedList<>(lists[0]);
            case 2 -> mergeSortedLists(lists[0], lists[1]);
            default -> mergeSortedLists(mergeSortedLists(lists, length - 1), lists[length - 1]);
        };
    }

    private static <T extends Comparable<T>> LinkedList<T> mergeSortedLists(LinkedList<T> a, LinkedList<T> b) {
        LinkedList<T> list = new LinkedList<>();
        if (a.isEmpty() || b.isEmpty()) {
            list.addAll(a);
            list.addAll(b);
            return list;
        }

        T x = a.removeFirst(), y = b.removeFirst();
        boolean fetchRight;
        while (true) {
            if (x.compareTo(y) <= 0) {
                list.addLast(x);
                fetchRight = false;
            } else {
                list.addLast(y);
                fetchRight = true;
            }

            if (fetchRight) {
                if (b.isEmpty()) {
                    list.addAll(a);
                    break;
                }
                y = b.removeFirst();
            } else {
                if (a.isEmpty()) {
                    list.addAll(b);
                    break;
                }
                x = a.removeFirst();
            }
        }

        return list;
    }
}
