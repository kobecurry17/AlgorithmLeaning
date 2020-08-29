package trymodel;

/**
 * 给一个数组代表货币的面值，给定一个总额，问有多少种组合方法
 */
public class Coin {

    /**
     * 一共多少总组合方法
     * 暴力递归
     *
     * @param coins
     * @param value
     * @return
     */
    public static int coins1(int[] coins, int value) {
        if (value < 0) {
            return 0;
        }
        return process(coins, value, 0);
    }

    /**
     * 暴力递归
     *
     * @param coins
     * @param value
     * @param index
     * @return
     */
    private static int process(int[] coins, int value, int index) {
        if (value == 0) {
            return 1;
        }
        if (index >= coins.length) {
            return 0;
        }
        if (coins[index] > value) {
            return process(coins, value, index + 1);
        }
        int res = 0;
        while (value > coins[index]) {
            res += process(coins, value -= coins[index], index + 1);
        }
        return res;
    }

    /**
     * 一共多少总组合方法
     * 记忆化搜索优化
     *
     * @param coins
     * @param value
     * @return
     */
    public static int coins2(int[] coins, int value) {
        if (value < 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][value + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(coins, value, 0, dp);
    }

    /**
     * 一共多少总组合方法
     * 记忆化搜索优化
     *
     * @param coins
     * @param value
     * @return
     */
    public static int coins3(int[] coins, int value) {
        if (value < 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][value + 1];
        // dp[*][0] = 1
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int index = coins.length - 1; index >= 0; index--) {
            for (int v = 0; v < value + 1; v++) {
                if (coins[index] > value) {
                    dp[index][v] = dp[index + 1][v];
                } else {
                    int res = 0;
                    while (value > coins[index]) {
                        res += dp[index + 1][value -= coins[index]];
                    }
                    dp[index][value] = res;
                }
            }
        }
        return dp[0][value];
    }

    /**
     * 记忆化搜索优化
     *
     * @param coins
     * @param value
     * @param index
     * @param dp
     * @return
     */
    private static int process1(int[] coins, int value, int index, int[][] dp) {
        if (dp[index][value] != -1) {
            return dp[index][value];
        }
        if (value == 0) {
            return 1;
        }
        if (index >= coins.length) {
            return 0;
        }
        int res = 0;
        while (value > coins[index]) {
            res += process1(coins, value -= coins[index], index + 1, dp);
        }
        dp[index][value] = res;
        return res;
    }


    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void main(String[] args) {
        int loops = 5_0000;
        int maxValue = 150;
        int maxSize = 10;
        int maxCoin = 50;
        for (int i = 0; i < loops; i++) {
            int value = generate(maxValue);
            int[] coins = generate(maxSize, maxCoin);
            int i1 = coins1(coins, value);
            int i2 = coins2(coins, value);
            int i3 = coins3(coins, value);
            if (i1 != i2 || i2 != i3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }


}
