package trymodel;

import org.omg.CORBA.BAD_CONTEXT;

/**
 * 背包问题
 * <p>
 * 给定两个长度都为N的数组 weights 和 values
 * 给定一个正数bags,表示一个载重为bag的袋子
 * 你装的物品不能比bags大
 * 返回你能装下的最大价值是多少？
 * </p>
 */
public class Knapsack {


    public static int maxValue1(int[] weights, int[] values, int bags) {
        if (weights.length == 0 || values.length == 0 || bags <= 0) {
            return 0;
        }
        return process(weights, values, bags, 0);
    }

    public static int maxValue2(int[] weights, int[] values, int bags) {
        if (weights.length == 0 || values.length == 0 || bags <= 0) {
            return 0;
        }
        return process2(weights, values, 0, bags);
    }

    /**
     * 记忆化搜索
     *
     * @param weights
     * @param values
     * @param bags
     * @return
     */
    public static int maxValue3(int[] weights, int[] values, int bags) {
        if (weights.length == 0 || values.length == 0 || bags <= 0) {
            return 0;
        }
        int[][] matrix = new int[weights.length + 1][bags + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = -1;
            }
        }
        return process3(weights, values, bags, 0, matrix);
    }

    /**
     * 动态规划
     * 背包问题使用动态规划效率不如记忆化搜索
     * 因为当bags很大时，无用的计算会增加
     *
     * @param w
     * @param v
     * @param bags
     * @return
     */
    public static int dpWays1(int[] w, int[] v, int bags) {
        if (w.length == 0 || v.length == 0 || bags <= 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bags + 1];
        for (int i = N; i >= 0; i--) {
            for (int rest = bags; rest >= 0; rest--) {
                if (i >= N || rest <= 0) {
                    continue;
                }
                // 不选择index
                int v1 = dp[i + 1][rest];
                int v2 = -1;
                if (w[i] <= rest) {
                    v2 = v[i] + dp[i + 1][rest - w[i]];
                }
                dp[i][rest] = Math.max(v1, v2);
            }
        }
        return dp[0][bags];
    }

    /**
     * 递归
     *
     * @param w
     * @param v
     * @param rest
     * @param index
     * @return
     */
    private static int process(int[] w, int[] v, int rest, int index) {
        if (index >= w.length || rest <= 0) {
            return 0;
        }
        // 不选择index
        int v1 = process(w, v, rest, index + 1);
        int v2 = -1;
        if (w[index] <= rest) {
            v2 = v[index] + process(w, v, rest - w[index], index + 1);
        }
        return Math.max(v1, v2);
    }

    // 只剩下rest的空间了，
    // index...货物自由选择，但是不要超过rest的空间
    // 返回能够获得的最大价值
    // 老师答案
    public static int process2(int[] w, int[] v, int index, int rest) {
        if (rest < 0) { // base case 1
            return -1;
        }
        // rest >=0
        if (index == w.length) { // base case 2
            return 0;
        }
        // 有货也有空间
        int p1 = process2(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process2(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    private static int process3(int[] w, int[] v, int rest, int index, int[][] matrix) {
        if (index >= w.length || rest <= 0) {
            return 0;
        }
        if (matrix[index][rest] != -1) {
            return matrix[index][rest];
        }
        // 不选择index
        int v1 = process3(w, v, rest, index + 1, matrix);
        int v2 = -1;
        if (w[index] <= rest) {
            v2 = v[index] + process3(w, v, rest - w[index], index + 1, matrix);
        }
        int max = Math.max(v1, v2);
        matrix[index][rest] = max;
        return max;
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxWeight = 30;
        int maxValue = 300;
        int maxSize = 10;
        int maxBags = 50;
        for (int i = 0; i < loops; i++) {
            int size = (int) (Math.random() * maxSize);
            int[] values = generate(size, maxValue);
            int[] weights = generate(size, maxWeight);
            int bags = (int) (Math.random() * maxBags);
            int i1 = maxValue1(weights, values, bags);
            int i2 = dpWays1(weights, values, bags);
            int i3 = maxValue3(weights, values, bags);
            int i4 = maxValue2(weights, values, bags);
            if (i1 != i2 || i3 != i4) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }


}
