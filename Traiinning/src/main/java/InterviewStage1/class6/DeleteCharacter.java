package InterviewStage1.class6;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符串可以成为s1的子串?
 * 比如 s1 = "abcde" s2 = "axbc"
 * 返回1。 s2删掉x就变成了s1的子串
 */
@SuppressWarnings("all")
public class DeleteCharacter {


    /**
     * 思路1:
     * 求出 str2 所有子序列
     * 在str1中使用KMP算法
     * 时间复杂度 O(M2 * N)
     */
    public static int way1(String str1, String str2) {
        int ans = 0;
        List<String> subSequence = subSequence(str2);
        subSequence.sort((String s1, String s2) -> {
            return ((Integer) s2.length()).compareTo(s1.length());
        });
        for (int i = 0; i < subSequence.size(); i++) {
            if (KMP(str1.toCharArray(), subSequence.get(i).toCharArray()) != -1) {
                return str2.length() - subSequence.get(i).length();
            }
        }
        return -1;
    }

    /**
     * KMP 算法
     * 在str1上寻找str2最早出现的位置
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int KMP(char[] str1, char[] str2) {
        if (str2 == null || str2.length == 0) {
            return 0;
        }
        if (str2.length > str1.length) {
            return -1;
        }
        if (str1.length == str2.length) {
            return new String(str1).equals(new String(str2)) ? 0 : -1;
        }
        int[] help = help(str2);
        int cur = 0;
        int index = 0;
        while (index < str2.length && cur < str1.length) {
            if (str1[cur] == str2[index]) {
                cur++;
                index++;
            } else if (help[index] == 0) {
                cur++;
            } else {
                index = help[index];
            }
        }
        return index == str2.length ? cur - str2.length : -1;
    }

    private static int[] help(char[] str) {
        int[] help = new int[str.length];
        help[0] = 0;
        help[1] = 1;
        int pre = 0;
        for (int i = 2; i < help.length; i++) {
            if (str[i - 1] == str[pre]) {
                help[i] = help[i - 1] + 1;
            } else {
                pre = 0;
            }
        }
        return help;
    }

    private static List<String> subSequence(String str2) {
        List<String> ans = new ArrayList<>();
        process(str2.toCharArray(), "", ans, 0);
        return ans;
    }

    private static void process(char[] str2, String path, List<String> ans, int index) {
        if (index == str2.length) {
            ans.add(path);
            return;
        }
        process(str2, path, ans, index + 1);
        process(str2, path + str2[index], ans, index + 1);
    }

    /**
     * 思路2:
     * 求出str1的所有满足条件子串，计算str2->str1的编辑距离
     */
    public static int way2(String str1, String str2) {
        if ("".equals(str1)) {
            return str2.length();
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = str1.length(); j > i; j--) {
                String s = str1.substring(i, j);
                min = Math.min(min, distance(s.toCharArray(), str2.toCharArray()));
            }
        }
        return min;
    }

    /**
     * str2通过删除字符串变成str1
     * 最少需要删几个字符串
     * 如果无法删成目标字符串，则返回-1
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int distance(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] == Integer.MAX_VALUE ? str2[i] == str1[0] ? i : Integer.MAX_VALUE : dp[0][i - 1] + 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = i; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                // 我是被删的
                if (dp[i][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i][j - 1] + 1;
                }
                // 我是留下来的最后一位
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[N - 1][M - 1];
    }


    public static void main(String[] args) {
        String s = "abcdes";
        int randSize = 10;
        int loops = 50_0000;
        for (int i = 0; i < loops; i++) {
            String s1 = randomString("", (Math.random() * randSize));
            String s2 = randomString(s1, (Math.random() * randSize));
            if (way1(s1, s2) != way2(s1, s2)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }

    /**
     * forTesting
     */
    private static String randomString(String s, double v) {
        StringBuilder sb = new StringBuilder();
        append(sb, 5);
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            append(sb, 3);
        }
        return sb.toString();
    }

    public static void append(StringBuilder sb, int size) {
        size = (int) (Math.random() * size);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + ((int) (Math.random() * 26))));
        }
    }

}
