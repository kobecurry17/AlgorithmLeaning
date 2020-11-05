package InterviewStage1.class8;

/**
 * 给定一个只由0（假），1（真），&（逻辑与），|（逻辑或）和^（异或）五中字符组成的字符串express
 * 再给定一个bool值desired
 * 返回express能有多少种组合方式，可以达到desired的结果
 * 【举例】
 * <p>
 * express = "1^0|0|1" , desired = false
 * 只由 1^(0|0)|1) 和 1^(0|1(0|1)) 的组合可以的到false 返回2.
 * </p>
 * <p>
 * express = "1" , desired = false
 * 无组合能得到 false,返回 0
 * </p>
 */
public class ExpressResult {


    /**
     * 表达式拼出目标结果的方法数
     *
     * @param express
     * @param result
     * @return
     */
    public static int expressResult(String express, boolean result) {
        // base case
        if ((express.length() & 1) != 1) {
            return 0;
        }
        char[] str = express.toCharArray();
        for (int i = 0; i < express.length(); i += 2) {
            if (str[i] == '1' || str[i] == '0') {
                continue;
            }
            return 0;
        }
        for (int i = 1; i < express.length(); i += 2) {
            if (str[i] == '|' || str[i] == '^' || str[i] == '&') {
                continue;
            }
            return 0;
        }
        int[][][] dp = getMap(str);
        return dp[result ? 1 : 0][0][express.length() - 1];
    }

    /**
     * @param express
     * @return
     */
    private static int[][][] getMap(char[] express) {
        int N = express.length;
        // dp[i][j] => express[i ... j) 拼出status 的方法数有多少？
        int[][][] dp = new int[2][N][N];

        // base case
        // 所有偶数列无效
        // 所有偶数列无效
        // i>j 的无效
        // 所有对角线位置取决于自己

        // 生成两张dp表,分别表示 express[i..j] 上有多少种方法变成 true Or false

        for (int t = 0; t < express.length; t += 2) {
            boolean status = express[t] == '1' ? true : false;
            dp[0][t][t] = status ? 0 : 1;
            dp[1][t][t] = status ? 1 : 0;
        }
        for (int i = N - 1; i >= 0; i -= 2) {
            for (int j = i + 2; j < N; j += 2) {
                for (int k = i + 1; k <= j - 1; k += 2) {
                    if (express[k] == '|') {
                        dp[0][i][j] += dp[0][i][k - 1] * dp[0][k + 1][j];
                        dp[1][i][j] += dp[1][i][k - 1] * dp[1][k + 1][j] + dp[0][i][k - 1] * dp[1][k + 1][j] + dp[1][i][k - 1] * dp[0][k + 1][j];
                    } else if (express[k] == '^') {
                        dp[0][i][j] += dp[0][i][k - 1] * dp[0][k + 1][j] + dp[1][i][k - 1] * dp[1][k + 1][j];
                        dp[1][i][j] += dp[0][i][k - 1] * dp[1][k + 1][j] + dp[1][i][k - 1] * dp[0][k + 1][j];
                    } else if (express[k] == '&') {
                        dp[0][i][j] += dp[0][i][k - 1] * dp[0][k + 1][j] + dp[0][i][k - 1] * dp[1][k + 1][j] + dp[1][i][k - 1] * dp[0][k + 1][j];
                        dp[1][i][j] += dp[1][i][k - 1] * dp[1][k + 1][j];
                    }
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String express = "1^0|0|1";
        System.out.println(expressResult(express, false));
    }

}
