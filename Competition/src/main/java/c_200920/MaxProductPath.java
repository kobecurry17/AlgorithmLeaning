package c_200920;

/**
 * 矩阵的最大非负乘积
 * 给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 * 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 * 返回 最大非负积 对 109+ 7 取余 的结果。如果最大积为负数，则返回 -1 。
 * 注意，取余是在得到最大积之后执行的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid =
 * {{-1,-2,-3},
 * {-2,-3,-3},
 * {-3,-3,-2}}
 * 输出：-1
 * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
 * 示例 2：
 * <p>
 * 输入：grid =
 * {{1,-2,1},
 * {1,-2,1},
 * {3,-4,1}}
 * 输出：8
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 = 8)
 * 示例 3：
 * <p>
 * 输入：grid =
 * {{1, 3},
 * {0,-4}}
 * 输出：0
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0)
 * 示例 4：
 * <p>
 * 输入：grid =
 * {{ 1, 4,4,0},
 * {-2, 0,0,1},
 * { 1,-1,1,1}}
 * 输出：2
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 * -1 * 1 * 1 = 2)
 * 提示：
 * 1 <= rows, cols <= 15
 * -4 <= grid[i][j] <= 4
 */
public class MaxProductPath {

    public int maxProductPath(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int mod = (int) (1e9 + 7);
            long[][][] dp = new long[n + 1][m + 1][2];
            dp[n - 1][m - 1][0] = grid[n - 1][m - 1];
            dp[n - 1][m - 1][1] = grid[n - 1][m - 1];

            //初始化
            for (int i = m - 2; i >= 0; i--) {
                dp[n - 1][i][0] = dp[n - 1][i + 1][0] * grid[n - 1][i];
                dp[n - 1][i][1] = dp[n - 1][i + 1][1] * grid[n - 1][i];

            }
            //初始化
            for (int i = n - 2; i >= 0; i--) {
                dp[i][m - 1][0] = dp[i + 1][m - 1][0] * grid[i][m - 1];
                dp[i][m - 1][1] = dp[i + 1][m - 1][1] * grid[i][m - 1];
            }

            for (int i = n - 2; i >= 0; i--) {
                for (int j = m - 2; j >= 0; j--) {
                    long a = grid[i][j] * dp[i + 1][j][0];
                    long b = grid[i][j] * dp[i + 1][j][1];
                    long c = grid[i][j] * dp[i][j + 1][0];
                    long d = grid[i][j] * dp[i][j + 1][1];
                    dp[i][j][0] = Math.max(Math.max(a, b), Math.max(c, d));
                    dp[i][j][1] = Math.min(Math.min(a, b), Math.min(c, d));
                }
            }
            long res1 = dp[0][0][0] % mod;
            long res2 = dp[0][0][1] % mod;
            long res = Math.max(res1, res2);
            return (int) (res < 0 ? -1 : res);
    }

    public static void main(String[] args) {
        MaxProductPath maxProductPath = new MaxProductPath();
        int[][] grid1 = new int[][]{{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        System.out.println(maxProductPath.maxProductPath(grid1));
//        int[][] grid2 = new int[][]{{1, 4, 4, 0}, {-2, 0, 0, 1}, {1, -1, 1, 1}};
//        System.out.println(maxProductPath.maxProductPath(grid2));
        int[][] grid2 = new int[][]{{1, -2, 1}, {1, -2, 1}, {3, -4, 1}};
        System.out.println(maxProductPath.maxProductPath(grid2));
    }
}
