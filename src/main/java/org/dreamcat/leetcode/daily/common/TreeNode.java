package org.dreamcat.leetcode.daily.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Create by tuke on 2020/11/6
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TreeNode<E> {

    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E element) {
        this.element = element;
    }
}
