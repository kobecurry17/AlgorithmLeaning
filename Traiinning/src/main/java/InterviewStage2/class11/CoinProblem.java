package InterviewStage2.class11;

import java.util.Arrays;

/**
 * <p>
 * 硬币问题
 * </p>
 * 现有 n1 + n2 种面值的硬币，其中前n1种为普通币，可以取任意坆
 * 后n2种为纪念币，每种最多只能取一枚,每种硬币只有一个面值
 * 问：能有多少种方法拼出m的面值
 */
@SuppressWarnings("all")
public class CoinProblem {

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    /**
     * @param arr 货币面值
     * @param n1  普通币个数
     * @param n2  纪念币个数
     * @param m   需要凑的面值
     */
    public static int coinCombination(int[] arr, int n1, int n2, int m) {
        if (n1 + n2 != arr.length) {
            return -1;
        }
        // int[][] dp1  使用[0..i]的普通币凑出 j 有多少种组合
        // int[][] dp2  使用[0..i]的普通币凑出 j 有多少种组合
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        for (int i = 0; i < arr.length; i++) {
            if (i < n1) {
                arr1[i] = arr[i];
            } else {
                arr2[i - n1] = arr[i];
            }
        }

        int[][] dp1 = commonDP(arr1, m);
        int[][] dp2 = specialDP(arr2);


        int ans = 0;

        for (int i = 0; i < m; i++) {
            if (m - i >= 0 && m - i < dp2[0].length)
                ans += dp1[n1][i] * dp2[n2][m - i];
        }
        return ans;

    }

    /**
     * 纪念币
     *
     * @param arr2
     * @return
     */
    private static int[][] specialDP(int[] arr2) {
        int sum = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum += arr2[i];
        }
        int[][] dp = new int[arr2.length + 1][sum + 1];
        for (int i = 0; i <= arr2.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr2[i - 1] > 0) {
                    dp[i][j] += dp[i - 1][j - arr2[i - 1]];
                }
            }
        }

        return dp;
    }

    /**
     * 普通币
     *
     * @param arr1
     * @param m
     * @return
     */
    private static int[][] commonDP(int[] arr1, int m) {
        int[][] dp = new int[arr1.length + 1][m + 1];
        for (int i = 0; i < arr1.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (arr1[i - 1] == j) {
                    dp[i][j]++;
                }
                int base = arr1[i - 1];
                while (base <= m && j - base > 0) {
                    dp[i][j] += dp[i - 1][j - base];
                    base += arr1[i - 1];
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 2, 3, 1};
        int n1 = 3;
        int n2 = 3;
        int m = 20;
        System.out.println(coinCombination(arr, n1, n2, m));
    }
}
