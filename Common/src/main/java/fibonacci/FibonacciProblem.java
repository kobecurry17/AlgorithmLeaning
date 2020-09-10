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
        int t_1 = 1;
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
        dp[1] = 1;
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
    public static int f4(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] matrix = {
                {1, 1},
                {1, 0}
        };
        int res[][] = matrixPower(matrix, n - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * 拓展1
     * F(n)=3F(n-1)+2F(n-2)+5F(n-3)
     */
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 拓展1
     * F(n)=3F(n-1)+2F(n-2)+5F(n-3)
     */
    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }

        return s2(n - 1) + s2(n - 2);
    }

    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    /**
     * f(0) = 0
     * f(1) = 2
     * f(2) = 2
     * f(n) = 2f(n-1) + f(n-2)
     *
     * @param n
     * @return
     */
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return 2;
        }
        return c1(n - 1) + 2 * c1(n - 2);
    }

    public static int c2(int n) {

        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return 2;
        }
        int[][] base = {
                {1, 1},
                {2, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + 2 * res[1][0];
    }


    /**
     * 矩阵乘积
     */
    private static int[][] matrixMut(int[][] matrix1, int[][] matrix2) {
        int[][] matrix = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix;
    }


    /**
     * 矩阵的N次幂
     * 时间复杂度O(logN)
     *
     * @param matrix
     * @param p
     * @return
     */
    private static int[][] matrixPower(int[][] matrix, int p) {
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = matrix;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = matrixMut(res, tmp);
            }
            tmp = matrixMut(tmp, tmp);
        }
        return res;
    }


    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int max = 30;

        int[][] matrix1 = {
                {1, 3},
                {1, 0},
                {1, 2}
        };
        int[][] matrix2 = {
                {0, 0, 2},
                {3, 2, 0},
        };

        int[][] ints = matrixMut(matrix1, matrix2);

        for (int i = 0; i < loops; i++) {
            int n = generate(max);
//            int ans3 = f3(n);
//            int ans4 = f4(n);
//            if (ans3 != ans4) {
//                System.out.println("Oops!");
//            }

//            int s1 = s1(n);
            int s2 = s2(n);
            int s3 = s3(n);
            if (s3 != s2) {
                System.out.println("Oops!");
            }

            int c1 = c1(n);
            int c2 = c2(n);
            if (c1 != c2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");


    }

}
