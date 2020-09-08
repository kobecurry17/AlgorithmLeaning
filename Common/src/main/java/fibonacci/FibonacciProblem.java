package fibonacci;

/**
 * 斐波那契额数列
 */
public class FibonacciProblem {


    /**
     * f(n) = f(n-1)+f(n-2)
     *
     * @return
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * 不用递归
     *
     * @param n
     * @return
     */
    public static int f2(int n) {
        int t_2 = 1;
        int t_1 = 2;
        if (n == 1 || n == 2) {
            return 1;
        }
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = t_2 + t_1;
            t_2 = t_1;
            t_1 = ans;
        }
        return ans;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 菲波那切数列特性
     * 当一个递归变化符合 F(N) = aF(N-1) + bF(N-2) +...+ cF(N-N) 时
     * F(N)变化可以看做是F(1) * 矩阵的N次幂
     * 也称作快速幂
     *
     * @param n
     * @return
     */
    public int f4(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int ans = 0;
        int[][] matrix = {{1, 1}, {1, 0}};

        int power = n / 2;
        int[][] finalMatrix = mutlMatrix(matrix, power);
        return ans;
    }

    /**
     * 矩阵乘积
     *
     * @param matrix
     * @param power
     * @return
     */
    private int[][] mutlMatrix(int[][] matrix, int power) {

        return new int[0][];
    }


    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int max = 30;
        for (int i = 0; i < loops; i++) {
            int n = generate(max);
//            int ans1 = f1(n);
            int ans2 = f2(n);
            int ans3 = f3(n);
            if (ans3 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }

}
