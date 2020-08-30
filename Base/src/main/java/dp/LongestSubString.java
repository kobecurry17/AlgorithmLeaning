package dp;

/**
 * 两个字符串最长子串
 */
public class LongestSubString {

    /**
     * 寻找 a 和 b 中最长的公共子串
     * 暴力方法
     *
     * @param a
     * @param b
     * @return
     */
    public static String longestSubString1(String a, String b) {
        String ans = null;
        for (int i = 0; i < a.length(); i++) {
            int start = i;
            int end = -1;
            for (int j = 0; j < b.length(); j++) {
                if (i + j < a.length() && a.charAt(i + j) == b.charAt(j)) {
                    end = i + j + 1;
                } else {
                    break;
                }
            }
            if (end != -1) {
                if (ans == null) {
                    ans = a.substring(start, end);
                } else {
                    int length = ans.length();
                    if (length < end - start) {
                        ans = a.substring(start, end);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 思路一：得到短字符串的所有子串,玩KMP。时间复杂度O(N²)
     * 思路二：没有思路
     *
     * @param a
     * @param b
     * @return
     */
    public static String longestSubString2(String a, String b) {
        if (null == a || null == b) {
            return null;
        }
        return process(a, b, 0, 0);
    }

    private static String process(String a, String b, int aIndex, int bIndex) {

        return "";
    }


    public static void main(String[] args) {
        System.out.println(longestSubString1("abbddca", "bdd"));

    }

}
