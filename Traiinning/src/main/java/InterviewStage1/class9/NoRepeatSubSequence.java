package InterviewStage1.class9;

import java.util.HashSet;

/**
 * <p>
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * </p>
 * <p>
 * 例如：
 * abcabcbb没有重复字符的最常子串是abc，长度为3
 * bbbbbb 答案是b，长度为1
 * pwwkew ，答案是wkr,长度是3
 * </p>
 */
public class NoRepeatSubSequence {

    /**
     * 暴力解
     * O(n^2)
     *
     * @param s
     * @return
     */
    public static int maxLength1(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length() + 1; j++) {
                if (isRepeat(s.substring(i, j))) {
                    max = Math.max(j - i, max);
                }
            }
        }
        return max;
    }


    /**
     * 尝试每个位置开头的解
     * 时间复杂度O(n)
     * l => [0....n]
     * r => [0....n]
     *
     * @param s
     * @return
     */
    public static int maxLength2(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        HashSet<Character> set = new HashSet();
        int l = 0;
        int r = 0;
        int max = -1;
        while (r != s.length()) {
            if (set.contains(str[r])) {
                max = Math.max(max, r - l);
                set.remove(str[l++]);
            } else {
                set.add(str[r]);
                r++;
            }
        }
        max = Math.max(max, r - l);
        return max;
    }

    private static boolean isRepeat(String str) {
        char[] array = str.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i])) {
                return false;
            } else {
                set.add(array[i]);
            }
        }
        return true;
    }

    // for testing
    public static String generate(int maxSize) {
        int size = (int) (Math.random() * maxSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char letter = (char) ('a' + ((int) (Math.random() * 26)));
            sb.append(letter);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 30;
        for (int i = 0; i < loops; i++) {
            String str = generate(maxSize);
            if (maxLength1(str) != maxLength2(str)) {
                System.out.println(str);
            }
        }
        System.out.println("Nice");
    }
}
