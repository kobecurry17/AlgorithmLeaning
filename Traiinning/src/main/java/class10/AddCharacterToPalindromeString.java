package class10;

/**
 * 给定一个字符串，问最少添加几个字符让字符串整体都是回文串
 */
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
        int[][] dp = new int[n + 1][n + 1];

        // dp[i][j] = dp[i-1][j-1] 如果str[i-1] == str[j-1]
        // dp[i][j] = dp[i+1][j] + 1
        // dp[i][j] = dp[i][j-1] + 1
        // 每个位置依赖他的左下角,左边和下边
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }


        // 沿着对角线遍历
        for (int l = 2; l <= n; l++) {
            for (int k = 0; k <= n - l; k++) {
                int x = k;
                int y = l + k;
                dp[x][y] = dp[x][y - 1] + 1;
                if (x + 1 <= n) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y] + 1);
                }
                if (x  < n && str[x] == str[y - 1]) {
                    dp[x][y] = Math.min(dp[x][y], dp[x + 1][y - 1]);
                }
            }
        }

        // 最终返回 dp[0][n]
        return dp[0][n];
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
//            String str = "bbaba";
            System.out.print(str + "===>");
            System.out.print(minCharacter(str));
            System.out.print("\n==================\n");
        }
    }
}


