package InterviewStage2.class14;

/**
 * 拯救公主2
 * <p>
 * 给定一个二维数组map,含义是一张地图。例如，如下矩阵:
 * map={
 * {-2,-3,3},
 * {-5,-10,1},
 * {0,30,-5}
 * }
 * 游戏的规则如下，其实从左上角出发，每次只能向右或者向下走,最后到达右下角见到公主
 * 地图中每个位置的值代表其实要遭遇的事情。
 * 如果是负数,说明此处有怪兽，要让骑士损失血量。
 * 如果是非负数，代表此处有血瓶，能让骑士回血。
 * 骑士从左上角到右下角的过程。走到任何一个位置时，血量都不能少于1
 * 为了保证骑士能遇到公主并且顺利返回，初始血量至少是多少?根据map返回初始血量
 * 注:返回时走过的路不能再走
 * </p>
 */
@SuppressWarnings("all")
public class SaveThePrincess2 {

    /**
     * 初始血量问题
     *
     * @return
     */
    public static int initHP(int[][] map) {

        // 生成和map等大小的dp
        // dp[i][j] => 到达map[i][j] 最少需要的血量
        int n = map.length;
        int m = map[0].length;
        boolean[][] help = new boolean[map.length][map[0].length];
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = map[n - 1][m - 1] > 0 ? 1 : -map[n - 1][m - 1] + 1;
        help[n - 1][m - 1] = true;
        // 初始化最右和最下
        for (int j = m - 2; j >= 0; j--) {
            dp[n - 1][j] = Math.max(dp[n - 1][j + 1] - map[n - 1][j], 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = Math.max(dp[i + 1][m - 1] - map[i][m - 1], 1);
        }

        for (int j = m - 2; j >= 0; j--) {
            for (int i = n - 2; i >= 0; i--) {
                int down = Math.max(dp[i + 1][j] - map[i][j], 1);
                int right = Math.max(dp[i][j + 1] - map[i][j], 1);
                if (down < right) {
                    dp[i][j] = down;
                    help[i + 1][j] = true;
                } else {
                    dp[i][j] = right;
                    help[i][j + 1] = true;
                }
            }
        }
        int res = dp[0][0];

        // 往回走
        dp = new int[n][m];
        dp[0][0] = 1;
        help[n - 1][m - 1] = true;
        // 初始化最左和最上
        for (int j = 1; j < m; j++) {
            dp[0][j] = Math.max(dp[0][j - 1] - map[0][j], 1);
        }
        for (int i = 1; i <= n - 2; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] - map[i][0], 1);
        }

        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                int up = Math.max(dp[i - 1][j] - map[i][j], 1);
                int left = Math.max(dp[i][j - 1] - map[i][j], 1);
                if (up < left && !help[i + 1][j]) {
                    dp[i][j] = up;
                } else {
                    dp[i][j] = left;
                }
            }
        }

        res += dp[n - 1][m - 1];
        return res;
    }


}
