package org.dreamcat.daily.leetcode.on2011;

import java.util.LinkedList;
import java.util.Objects;
import org.dreamcat.daily.leetcode.common.BinaryTreeNode;

/**
 * Create by tuke on 2020/11/6
 * <p/>
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class On06_symmetric_tree {

    // 递归
    public static <E> boolean isSymmetricByRecurse(BinaryTreeNode<E> root) {
        return root == null || recurseCompare(root.left, root.right);
    }

    private static <E> boolean recurseCompare(BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (!Objects.equals(left.element, right.element)) {
            return false;
        }

        return recurseCompare(left.left, right.right) && recurseCompare(left.right, right.left);
    }

    // 队列递归
    public static <E> boolean isSymmetricByQueue(BinaryTreeNode<E> root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        LinkedList<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> left = queue.removeFirst();
            BinaryTreeNode<E> right = queue.removeFirst();

            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (!Objects.equals(left.element, right.element)) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    // private static <E> boolean compare(BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
    //
    // }

    // 层序遍历
    @SuppressWarnings("unchecked")
    public static <E> boolean isSymmetricByLevelOrder(BinaryTreeNode<E> root) {
        if (root == null) {
            return true;
        }

        BinaryTreeNode<E>[] nodes = (BinaryTreeNode<E>[]) new BinaryTreeNode[]{root.left,
                root.right};
        for (; ; ) {
            var size = nodes.length;
            var halfOfSize = size / 2;
            var nextNodes = (BinaryTreeNode<E>[]) new BinaryTreeNode[size * 2];
            var hasNext = false;
            for (var i = 0; i < halfOfSize; i++) {
                var oneOfHead = nodes[i];
                var oneOfTail = nodes[size - i - 1];
                E oneOfHeadElement = null;
                E oneOfTailElement = null;

                if (oneOfHead != null) {
                    if (!hasNext) {
                        hasNext = oneOfHead.left != null || oneOfHead.right != null;
                    }
                    nextNodes[i * 2] = oneOfHead.left;
                    nextNodes[i * 2 + 1] = oneOfHead.right;
                    oneOfHeadElement = oneOfHead.element;
                }
                if (oneOfTail != null) {
                    if (!hasNext) {
                        hasNext = oneOfTail.left != null || oneOfTail.right != null;
                    }
                    nextNodes[(size - i - 1) * 2] = oneOfTail.left;
                    nextNodes[(size - i - 1) * 2 + 1] = oneOfTail.right;
                    oneOfTailElement = oneOfTail.element;
                }

                if (!Objects.equals(oneOfHeadElement, oneOfTailElement)) {
                    return false;
                }
            }
            if (hasNext) {
                nodes = nextNodes;
                continue;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        var node1 = new BinaryTreeNode<>(1);
        var node21 = new BinaryTreeNode<>(2);
        var node22 = new BinaryTreeNode<>(2);
        var node31 = new BinaryTreeNode<>(3);
        var node32 = new BinaryTreeNode<>(4);
        var node33 = new BinaryTreeNode<>(4);
        var node34 = new BinaryTreeNode<>(3);
        node1.left = node21;
        node1.right = node22;

        node21.left = node31;
        node21.right = node32;

        node22.left = node33;
        node22.right = node34;

        System.out.println(isSymmetricByLevelOrder(node1));
        System.out.println(isSymmetricByRecurse(node1));
        System.out.println(isSymmetricByQueue(node1));
    }
}
