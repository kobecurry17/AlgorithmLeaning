package class4;

/**
 * 给定两个字符串Str1和Str2
 * 求最长公共子序列
 * 重点：状态转移？可能性组织能力
 */
public class LongestCommonSubsequence {


    public static int longestCommonSubSequence(String str1, String str2) {
        int N = str1.length();
        int M = str2.length();
        /**
         * dp[i][j]的意义
         * str1[0...i] 与 str2[0...j]上最长公共子序列长度
         */
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int[][] dp = new int[N][M];
        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] > 0 ? dp[i - 1][0] : arr1[i] == arr2[0] ? 1 : 0;
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] > 0 ? dp[0][i - 1] : arr1[0] == arr2[i] ? 1 : 0;
        }
        for (int j = 1; j < M; j++) {
            for (int i = 1; i < N; i++) {
                int p1 = dp[i - 1][j - 1] + (arr1[i] == arr2[j] ? 1 : 0);
                int p2 = dp[i][j - 1];
                int p3 = dp[i - 1][j];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubSequence("aaeebceff", "esadbcaeee"));
    }
}
