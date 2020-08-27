package trymodel;

/**
 * 机器人走路
 * 假设有排成一行的SIZE个位置，记为 1~SIZE,SIZE一定大于或等于2
 * 开始时机器人在其中的FROM的位置上（FROM一定是1~SIZE的其中一个）
 * 如果机器人来到 1 的位置下一步一定只能向右来到 2 的位置
 * 如果机器人来到 SIZE 的位置下一步一定只能向左来到 SIZE-1 的位置
 * 如果机器人来到 中间 的位置下一步能向左也能向右
 * 规定机器人必须走 STEP 步，最终能来到TO的位置（TO也是1~SIZE的其中一个）的方法一共有多少种
 * 给定四个参数 FROM,STEP,SIZE,TO返回方法数
 */
public class RobotWalk {
    /**
     * 递归方法
     *
     * @param from
     * @param step
     * @param size
     * @param to
     * @return
     */
    public static int walkWays1(int from, int step, int size, int to) {
        // 起点和终点间隔大于SIZE，到不了
        if (Math.abs(to - from) > size || size < 2) {
            return 0;
        }
        return process(from, step, size, to);
    }

    /**
     * 记忆化搜索
     *
     * @return
     */
    public static int walkWays2(int from, int step, int size, int to) {
        // 起点和终点间隔大于SIZE，到不了
        if (Math.abs(to - from) > size || size < 2) {
            return 0;
        }
        int[][] arr = new int[size + 1][step + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = -1;
            }
        }
        return process1(from, step, size, to, arr
        );
    }

    /**
     * 记忆化搜索优化
     *
     * @param from
     * @param step
     * @param size
     * @param to
     * @param arr
     * @return
     */
    private static int process1(int from, int step, int size, int to, int[][] arr) {
        if (step == 0) {
            if (from != to) {
                return 0;
            } else {
                return 1;
            }
        }
        int res = 0;
        if (from == 1) {
            if (arr[2][step - 1] != -1) {
                return arr[2][step - 1];
            }
            int i = process1(2, step - 1, size, to, arr);
            arr[2][step - 1] = i;
            return i;
        }
        if (from == size) {
            int i = process1(size - 1, step - 1, size, to, arr);
            arr[size - 1][step - 1] = i;
            return i;
        }
        res += process1(from + 1, step - 1, size, to, arr);
        res += process1(from - 1, step - 1, size, to, arr);
        arr[from][step] = res;
        return res;
    }

    /**
     * 从from出发走step步到to有多少种方法
     *
     * @param from
     * @param step
     * @param size
     * @param to
     * @return
     */
    private static int process(int from, int step, int size, int to) {
        if (step == 0) {
            if (from != to) {
                return 0;
            } else {
                return 1;
            }
        }
        int res = 0;
        if (from == 1) {
            return process(2, step - 1, size, to);
        }
        if (from == size) {
            return process(size - 1, step - 1, size, to);
        }
        res += process(from + 1, step - 1, size, to);
        res += process(from - 1, step - 1, size, to);
        return res;
    }

    /**
     * 动态规划
     *
     * @param from
     * @param step
     * @param size
     * @param to
     * @return
     */
    private static int dpWays(int from, int step, int size, int to) {
        // 起点和终点间隔大于SIZE，到不了
        if (Math.abs(to - from) > size || size < 2) {
            return 0;
        }
        int[][] dp = new int[step + 1][size + 1];

        // dp[*][0] = 0
        // dp[to][0] = 1
        dp[0][to] = 1;
        for (int i = 1; i <= step; i++) {
            for (int j = 1; j <= size; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == size) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[step][from];
    }

    // for testing
    public static int generate(int max) {
        return (int) (Math.random() * max) + 1;
    }


    public static void main(String[] args) {

        int loops = 50_0000;
        int maxSize = 5;
        int maxStep = 20;
        for (int i = 0; i < loops; i++) {
            int size = generate(maxSize);
            int step = generate(maxStep);
            int from = generate(size);
            int to = generate(size);
            if (walkWays2(from, step, size, to) != dpWays(from, step, size, to)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");

    }


}
