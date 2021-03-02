package org.dreamcat.daily.leetcode.on2101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dreamcat.daily.leetcode.common.BTreeNode;
import org.dreamcat.daily.leetcode.common.Pair;

/**
 * Create by tuke on 2021/1/6
 * <p/>
 * https://leetcode-cn.com/problems/evaluate-division/
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
 * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 */
public class On06_evaluate_division {

    public static double[] calcEquation(
            List<List<String>> equations, double[] values, List<List<String>> queries) {
        int querySize = queries.size();
        double[] result = new double[querySize];
        List<BTreeNode<Pair<String, Double>>> tree = buildTree(equations, values);

        for (int i = 0; i < querySize; i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);
            result[i] = searchTree(a, b, tree);
        }
        return result;
    }

    private static List<BTreeNode<Pair<String, Double>>> buildTree(
            List<List<String>> equations, double[] values) {
        int equationSize = equations.size();
        List<BTreeNode<Pair<String, Double>>> tree = new ArrayList<>(equationSize);

        return tree;
    }

    private static double searchTree(
            String a, String b, List<BTreeNode<Pair<String, Double>>> tree) {
        if (a.equals(b)) return 1.0;
        if ()
            return -1.0;
    }

    private static double searchTreeOneWay(
            String a, String b, List<BTreeNode<Pair<String, Double>>> nodes) {
        if (a.equals(b)) return 1.0;

        for (BTreeNode<Pair<String, Double>> node : nodes) {
            searchTreeNodes(a, b, node);
        }
        return -1.0;
    }

    private static BTreeNode<Pair<String, Double>> searchTreeNodes(
            String a, String b, BTreeNode<Pair<String, Double>> node) {
        String key = node.element._1;

    }

    public static void main(String[] args) {
        List<List<String>> equations;
        double[] values;
        List<List<String>> queries;

        equations = List.of(List.of("a", "b"), List.of("b", "c"));
        values = new double[]{2.0, 3.0};
        queries = List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"),
                List.of("a", "a"), List.of("x", "x"));
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));

        equations = List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd"));
        values = new double[]{1.5, 2.5, 5.0};
        queries = List.of(List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"),
                List.of("cd", "bc"));
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));

        equations = List.of(List.of("a", "b"));
        values = new double[]{0.5};
        queries = List.of(List.of("a", "b"), List.of("b", "a"), List.of("a", "c"),
                List.of("x", "y"));
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }
}
