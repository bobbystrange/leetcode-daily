package org.dreamcat.daily.leetcode.on2011;

/**
 * Create by tuke on 2020/11/2
 * <p/>
 * https://leetcode-cn.com/problems/longest-common-prefix
 */
public class On02_longest_common_prefix {

    public static String longestCommonPrefix(String[] strings) {
        var i = 0;
        var len = strings.length;
        if (len == 0) {
            return "";
        }
        if (len == 1) {
            return strings[0];
        }

        outer:
        for (; ; i++) {
            var firstString = strings[0];
            if (i >= firstString.length()) {
                break;
            }

            var firstChar = firstString.charAt(i);
            for (int j = 1; j < len; j++) {
                var string = strings[j];
                if (i >= string.length()) {
                    break outer;
                }
                if (firstChar != string.charAt(i)) {
                    break outer;
                }
            }
        }
        return strings[0].substring(0, i);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(
                new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(
                new String[]{"dog", "racecar", "car"}));
    }
}
