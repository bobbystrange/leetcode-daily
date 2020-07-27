package org.dreamcat.leetcode.daily.on2006;

/**
 * Create by tuke on 2020/6/18
 */
public class On18_match_simple_regexp {

    public static void main(String[] args) {
        System.out.println(matchSimpleRegexp("aa", "a"));
        System.out.println(matchSimpleRegexp("aa", "a*"));
        System.out.println(matchSimpleRegexp("ab", ".*"));
        System.out.println(matchSimpleRegexp("aab", "c*a*b"));
        System.out.println(matchSimpleRegexp("mississippi", "mis*is*p*."));
    }

    public static boolean matchSimpleRegexp(String s, String p) {
        if (s.isEmpty()) return p.isEmpty();

        //["xx", "xx", ""]
        var pats = p.split("\\*");
        for (int i = 0, size = pats.length; i < size; i++) {
            var pat = pats[i];
            if (pat.isEmpty() && i != size - 1) {
                throw new IllegalArgumentException("invalid format, include continual *");
            }

            int plen = pat.length();
            char matchChar;
            if (plen > 1) {
                var stringThatExcludesStars = pat.substring(0, plen - 1);
                matchChar = pat.charAt(plen - 1);
                if (!matchSimpleRegexp(s, pat.substring(0, plen - 1))) {
                    return false;
                } else {
                    s = s.substring(plen);
                }
            } else {
                matchChar = pat.charAt(plen - 1);
            }

        }
        return false;
    }

    // s [a-z]*, p [a-z.*]*
    public static boolean matchSimpleRegexp2(String s, String p) {
        if (s.isEmpty()) return p.isEmpty();

        int si = 0;
        for (int i = 0, plen = p.length(), slen = s.length(); i < plen; i++) {
            if (si == slen) return false;

            // c is never *, since the assumption
            char c = p.charAt(i);

            // [a-z]
            if (i == plen - 1) {
                if (c == '.') return true;
                else {
                    return c == s.charAt(si);
                }
            }

            char c1 = p.charAt(i + 1);
            if (c1 != '*') {
                // single [a-z] match
                if (c != '.') {
                    if (c != s.charAt(si)) return false;
                }
                // single . match
                si++;
                continue;
            }

            // .*, or [a-z]*
            if (i == plen - 2) {
                // .*, match
                if (c == '.') return true;
                // [a-z]* match empty substring
                if (si == slen - 1) return true;

                // [a-z]* match not-empty-string
                for (int k = si; k < slen; k++) {
                    if (c != s.charAt(k)) return false;
                }
                return true;
            }

            // [a-z]*xxx
            if (c != '.') {

            }

            // .*xxx

        }
        return false;
    }
}
