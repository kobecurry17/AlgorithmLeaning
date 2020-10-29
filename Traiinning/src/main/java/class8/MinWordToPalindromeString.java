package class8;

/**
 * 给定一个字符串
 * 问:至少需要加几个字符串变成全部回文字符串
 */
public class MinWordToPalindromeString {

    public static int minWordToPalindromeString(String s) {
        // base case
        if (null == s || s.length() < 2) {
            return 0;
        }
        int N = s.length();
        // int[][] dp数组
        // dp[i][j] 表示在 s [i...j]上最少要添加几个字符形成回文字符串
        // 可能性组织:
        // dp[i][j] = 1 + dp[i][j-1]
        // dp[i][j] = 1 + dp[i+1][j]
        // 当s[i] == s[j] 时 dp[i][j] = dp[i+1][j-1]
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];

//        for (int i = 0; i < N; i++) {
//            dp[i][i] = 0;
//        }
        for (int i = 1; i < N; i++) {
            dp[i - 1][i] = str[i - 1] == str[i] ? 0 : 1;
        }

        for (int diff = 2; diff < N; diff++) {
            for (int i = 0; i < N - 1; i++) {
                int j = i + diff;
                if (j == N) {
                    break;
                }
                dp[i][j] = dp[i + 1][j - 1] + 2;
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        System.out.println(minWordToPalindromeString("aaedw"));
        System.out.println(minWordToPalindromeString("ccsedfes"));
        System.out.println(minWordToPalindromeString("abbac"));
        System.out.println(minWordToPalindromeString("edffde"));
        System.out.println(minWordToPalindromeString("eqw"));
    }

}
