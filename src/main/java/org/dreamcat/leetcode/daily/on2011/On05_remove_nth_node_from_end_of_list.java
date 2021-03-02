package org.dreamcat.leetcode.daily.on2011;

import org.dreamcat.leetcode.daily.common.ListNode;

/**
 * Create by tuke on 2020/11/5
 * <p/>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class On05_remove_nth_node_from_end_of_list {

    public static <E> ListNode<E> removeNthFromEnd(ListNode<E> head, int nth) {
        var len = 1;
        var next = head;
        while ((next = next.next) != null) {
            len++;
        }

        if (nth == len) {
            next = head.next;
            head.next = null; // help GC
            return next;
        }

        next = head;
        for (int i = 0; i < len - nth - 1; i++) {
            next = next.next;
        }
        if (next.next != null) {
            var deleted = next.next;
            next.next = next.next.next;
            deleted.next = null; // help GC
        }
        return head;
    }

    // 1 -> 2 -> 3 -> 4
    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 1));
        System.out.println(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 2));
        System.out.println(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 3));
        System.out.println(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 4));
    }
}
