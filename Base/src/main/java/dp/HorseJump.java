package dp;

/**
 * 在 10 * 9 的棋盘上从 任意位置 跳到 (x,y) 用K步。
 * 问：一共有多少种跳法
 */
public class HorseJump {


    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }


    /**
     * @param fromX 出发位置x
     * @param fromY 出发位置y
     * @param x     目标x
     * @param y     目标y
     * @param k     剩余步数
     * @return
     */
    private static int horseJump(int fromX, int fromY, int x, int y, int k) {
        // 达不到的位置忽略
        if (x >= 10 || y >= 9 || x < 0 || y < 0) {
            return 0;
        }
        return process(fromX, fromY, x, y, k);
    }

    /**
     * @param fromX 出发位置x
     * @param fromY 出发位置y
     * @param x     目标x
     * @param y     目标y
     * @param k     剩余步数
     * @return
     */
    private static int process(int fromX, int fromY, int x, int y, int k) {
        if (x >= 10 || y >= 9 || x < 0 || y < 0) {
            return 0;
        }
        if (fromX >= 10 || fromY >= 9 || fromX < 0 || fromY < 0) {
            return 0;
        }
        if (fromX == x && fromY == y && k == 0) {
            return 1;
        }
        if (k <= 0) {
            return 0;
        }
        int next = k - 1;
        return
                process(fromX + 1, fromY + 2, x, y, next) +
                        process(fromX + 1, fromY - 2, x, y, next) +
                        process(fromX - 1, fromY + 2, x, y, next) +
                        process(fromX - 1, fromY - 2, x, y, next) +
                        process(fromX - 2, fromY - 1, x, y, next) +
                        process(fromX + 2, fromY - 1, x, y, next) +
                        process(fromX - 2, fromY + 1, x, y, next) +
                        process(fromX + 2, fromY + 1, x, y, next);
    }


    private static int horseJumpDP(int fromX, int fromY, int x, int y, int k) {
        if (x >= 10 || y >= 9 || x < 0 || y < 0) {
            return 0;
        }
        if (fromX >= 10 || fromY >= 9 || fromX < 0 || fromY < 0) {
            return 0;
        }
        int dp[][][] = new int[11][10][k + 1];
        //  初始化
        dp[x][y][0] = 1;
        for (int step = 1; step <= k; step++) {
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 9; j++) {
                    int next = step - 1;
                    dp[i][j][step] =
                            getValue(dp, i - 1, j + 2, next) +
                                    getValue(dp, i - 1, j - 2, next) +
                                    getValue(dp, i + 1, j + 2, next) +
                                    getValue(dp, i + 1, j - 2, next) +
                                    getValue(dp, i + 2, j - 1, next) +
                                    getValue(dp, i - 2, j - 1, next) +
                                    getValue(dp, i + 2, j + 1, next) +
                                    getValue(dp, i - 2, j + 1, next);
                }
            }
        }
        return dp[fromX][fromY][k];
    }

    /**
     * 得到dp[i][j][step]位置的值
     *
     * @param dp
     * @param i
     * @param j
     * @param step
     * @return
     */
    private static int getValue(int[][][] dp, int i, int j, int step) {
        if (i >= 10 || i < 0 || j < 0 || j >= 9 || step < 0 || step >= dp[0][0].length) {
            return 0;
        }
        return dp[i][j][step];
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int width = 10;
        int height = 9;
        int maxStep = 12;
        int fromX = generate(width);
        int fromY = generate(height);
        for (int i = 0; i < loops; i++) {
            int x = generate(width);
            int y = generate(height);
            int k = generate(maxStep);
            int ans1 = horseJump(fromX, fromY, x, y, k);
            int ans2 = horseJumpDP(fromX, fromY, x, y, k);
            if (ans1 != ans2) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }


}
