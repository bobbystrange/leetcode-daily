package org.dreamcat.daily.leetcode.on2011;

/**
 * Create by tuke on 2020/11/5
 * <p/>
 * https://leetcode-cn.com/problems/length-of-last-word/
 */
public class On05_length_of_last_word {

    public static int lengthOfLastWord(String s) {
        var len = s.length();
        var i = len - 1;
        var startCount = false;
        for (; i >= 0; i--) {
            if (s.charAt(i) <= ' ') {
                if (startCount) {
                    break;
                } else {
                    len--;
                    continue;
                }
            }
            startCount = true;
        }
        return len - i - 1;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("Hello World "));
        System.out.println(lengthOfLastWord(" Hello World "));
    }
}
