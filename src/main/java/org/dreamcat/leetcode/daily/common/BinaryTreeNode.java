package org.dreamcat.leetcode.daily.common;

import lombok.ToString;

/**
 * Create by tuke on 2020/11/6
 */
@ToString
public class BinaryTreeNode<E> {

    public E element;
    public BinaryTreeNode<E> left;
    public BinaryTreeNode<E> right;

    public BinaryTreeNode(E element) {
        this.element = element;
    }

    public BinaryTreeNode(E element, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
