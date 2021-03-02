package org.dreamcat.daily.leetcode.common;

import java.util.List;
import lombok.ToString;

/**
 * Create by tuke on 2021/1/6
 */
@ToString
public class BTreeNode<E> {

    public E element;
    public List<BTreeNode<E>> children;

    public BTreeNode(E element) {
        this.element = element;
    }

    public BTreeNode(E element,
            List<BTreeNode<E>> children) {
        this.element = element;
        this.children = children;
    }
}
