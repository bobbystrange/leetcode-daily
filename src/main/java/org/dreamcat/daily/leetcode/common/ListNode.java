package org.dreamcat.daily.leetcode.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Create by tuke on 2020/10/13
 * <p>
 * Definition for singly-linked list
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListNode<E> {

    public E element;
    public ListNode<E> next;

    public ListNode(E element) {
        this.element = element;
    }

    public static ListNode<Integer> of(int... nums) {
        ListNode<Integer> node = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            node = new ListNode<>(nums[i], node);
        }
        return node;
    }
}
