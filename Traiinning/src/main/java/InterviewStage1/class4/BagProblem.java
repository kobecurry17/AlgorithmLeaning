package InterviewStage1.class4;

/**
 * <p>
 * 背包容量为W
 * 一共有n袋零食,第i带零食体积为v[i]
 * 中提及不超过背包容量一共有多少种方法?
 * 总体积为0也算一种。
 * </p>
 */
public class BagProblem {


    public static int ways1(int[] arr, int w) {
        // arr[0...]
        return process(arr, 0, w);
    }

    // 从左往右的经典模型
    // 还剩的容量是rest，arr[index...]自由选择，
    // 返回选择方案
    // index ： 0～N
    // rest : 0~w
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) { // 没有容量了
            // -1 无方案的意思
            return -1;
        }
        // rest>=0,
        if (index == arr.length) { // 无零食可选
            return 1;
        }
        // rest >=0
        // 有零食index
        // index号零食，要 or 不要
        // index, rest
        // (index+1, rest)
        // (index+1, rest-arr[i])
        int next1 = process(arr, index + 1, rest); // 不要
        int next2 = process(arr, index + 1, rest - arr[index]); // 要
        return next1 + (next2 == -1 ? 0 : next2);
    }

    /**
     * 动态规划
     *
     * @param v
     * @param W
     * @return
     */
    public static int dpWays(int[] v, int W) {
        int N = v.length;
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            dp[N][i] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < W + 1; j++) {
                dp[i][j] = dp[i + 1][j] + (j - v[i] >= 0 ? dp[i + 1][j - v[i]] : 0);
            }
        }
        return dp[0][W];
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }

    public static void main(String[] args) {

        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 0; i < loops; i++) {
            int k = generate(500);
            int[] v = generate(maxLength, 10);
//            int[] v = {1, 3, 5};
//            int k = 7;
            if (ways1(v, k) != dpWays(v, k)) {
                System.out.println("Oops!");
            }
        }
    }

}
