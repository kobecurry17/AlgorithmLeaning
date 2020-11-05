package InterviewStage1.class10;

/**
 * 给定一个字符串，问最少添加几个字符让字符串整体都是回文串
 */
@SuppressWarnings("all")
public class AddCharacterToPalindromeString {

    /**
     * 给定一个字符串，问需要最少添加几个字符让整体变成回文字符串
     *
     * @param s
     * @return
     */
    public static int minCharacter(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = s.length();

        // dp[i][j]表示 str[i...j]需要添加几个字符串，整体变成回文字符串
        int[][] dp = new int[n][n];

        // dp[i][j] = dp[i-1][j-1] 如果str[i-1] == str[j-1]
        // dp[i][j] = dp[i+1][j] + 1
        // dp[i][j] = dp[i][j-1] + 1
        // 每个位置依赖他的左下角,左边和下边
        for (int i = 0; i < n - 2; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }


        // 沿着对角线遍历
        for (int l = 2; l < n; l++) {
            for (int k = 0; k < n - l; k++) {
                int x = k;
                int y = l + k;
                dp[x][y] = Math.min(dp[x + 1][y - 1] + 2, dp[x][y - 1] + 1);
                if (x + 1 <= n) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y] + 1);
                }
                if (x < n && str[x] == str[y]) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y - 1]);
                }
            }
        }

        // 最终返回 dp[0][n]
        return dp[0][n - 1];
    }

    /**
     * 返回最小回文子序列的一种结果
     *
     * @param s
     * @return
     */
    public static String palindromeString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int n = s.length();

        // dp[i][j]表示 str[i...j]需要添加几个字符串，整体变成回文字符串
        int[][] dp = new int[n][n];

        // dp[i][j] = dp[i-1][j-1] 如果str[i-1] == str[j-1]
        // dp[i][j] = dp[i+1][j] + 1
        // dp[i][j] = dp[i][j-1] + 1
        // 每个位置依赖他的左下角,左边和下边
        for (int i = 0; i < n - 2; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }


        // 沿着对角线遍历
        for (int l = 2; l < n; l++) {
            for (int k = 0; k < n - l; k++) {
                int x = k;
                int y = l + k;
                dp[x][y] = dp[x][y - 1] + 1;
                if (x + 1 <= n) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y] + 1);
                }
                if (x < n && str[x] == str[y]) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y - 1]);
                }
            }
        }
        // 最终返回 dp[0][n]
        return process(str, dp, 0, n - 1);
    }

    private static String process(char[] str, int[][] dp, int x, int y) {
        if (x == y) {
            return "" + str[x];
        } else if (x > y) {
            return "";
        } else if (dp[x][y] == 0) {
            String s = new String(str, x, y + 1 - x);
            return s;
        } else if (y - 1 >= 0 && dp[x][y] == dp[x][y - 1] + 1) {
            return str[y] + process(str, dp, x, y - 1) + str[y];
        } else if (x + 1 < str.length && y - 1 >= 0 && dp[x][y] == dp[x + 1][y - 1]) {
            return str[y] + process(str, dp, x + 1, y - 1) + str[y];
        } else if (dp[x][y] == dp[x + 1][y] + 1) {
            return str[x] + process(str, dp, x + 1, y) + str[x];
        } else if (x + 1 < str.length && y - 1 >= 0 && dp[x][y] == dp[x + 1][y - 1] + 2) {
            return str[x] + str[y] + process(str, dp, x, y - 1) + str[y] + str[x];
        } else {
            return "";
        }
    }


    // for test
    public static String generate(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (int) (Math.random() * 2)));
        }
        return sb.toString();
    }

    // for test
    public static void print(String str) {
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 5;
        for (int i = 0; i < loops; i++) {
            String str = generate(maxLength);
            int size = minCharacter(str);
            String res = palindromeString(str);
            if (res.length() - str.length() != size) {
                System.out.println(str + "----" + res);
            }
        }
    }
}


