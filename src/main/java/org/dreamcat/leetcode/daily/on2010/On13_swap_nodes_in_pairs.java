package org.dreamcat.leetcode.daily.on2010;

import org.dreamcat.leetcode.daily.common.ListNode;

/**
 * Create by tuke on 2020/10/13
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/submissions/
 * https://leetcode-cn.com/problems/permutations/
 */
public class On13_swap_nodes_in_pairs {

    // 1->2->3->4  -->  2->1->4->3
    public static <E> ListNode<E> swapPairs(ListNode<E> head) {
        if (head == null) {
            return null;
        }
        var next = head.next;
        if (next == null) {
            return head;
        }
        var nextNext = next.next;
        next.next = head;
        head.next = swapPairs(nextNext);
        return next;
    }

    public static void main(String[] args) {
        System.out.println(swapPairs(ListNode.of(1)));
        System.out.println(swapPairs(ListNode.of(1, 2)));
        System.out.println(swapPairs(ListNode.of(1, 2, 3)));
        System.out.println(swapPairs(ListNode.of(1, 2, 3, 4)));
        System.out.println(swapPairs(ListNode.of(1, 2, 3, 4, 5)));

    }

}
