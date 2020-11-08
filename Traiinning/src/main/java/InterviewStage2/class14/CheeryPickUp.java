package InterviewStage2.class14;

/**
 * 拯救公主2
 * <p>
 * 给定一个二维数组map,含义是一张地图。例如，如下矩阵:
 * map={
 * {1,1,1},
 * {1,0,1},
 * {1,1,1}
 * }
 * 游戏的规则如下，其实从左上角出发，每次只能向右或者向下走,最后到达右下角见到公主
 * 到达有下角后,返回，问能得到的最多糖果数
 * </p>
 */
@SuppressWarnings("all")
public class CheeryPickUp {

    public static int maxCandy(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 思路 两个小人同时从0,0出发,到达公主的位置
        // 计算最多能得到的糖果数
        return process(matrix, 0, 0, 0);
    }

    private static int process(int[][] matrix, int aX, int aY, int bX) {
        int bY = aX + aY - bX;
        if (aX < 0 || aY < 0 || aX >= matrix.length || aY >= matrix[0].length || bX < 0 || bY < 0 || bX >= matrix.length || bY >= matrix[0].length) {
            return 0;
        }
        int res = matrix[aX][aY] + (aX == bX ? 0 : matrix[bX][bY]);
        int p1 = process(matrix, aX + 1, aY, bX + 1);
        int p2 = process(matrix, aX + 1, aY, bX);
        int p3 = process(matrix, aX, aY + 1, bX + 1);
        int p4 = process(matrix, aX, aY + 1, bX);
        return res + Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static int cherryPickup2(int[][] grid) {
        int N = grid.length;
        int[][][] dp = new int[N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        int ans = process2(grid, 0, 0, 0, dp);
        return ans < 0 ? 0 : ans;
    }

    public static int process2(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
        if (x1 == grid.length || y1 == grid.length || x2 == grid.length || x1 + y1 - x2 == grid.length) {
            return Integer.MIN_VALUE;
        }

        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }

        if (x1 == grid.length - 1 && y1 == grid.length - 1) {
            dp[x1][y1][x2] = grid[x1][y1];
            return dp[x1][y1][x2];
        }
        int next = Integer.MIN_VALUE;
        next = Math.max(next, process2(grid, x1 + 1, y1, x2 + 1, dp));
        next = Math.max(next, process2(grid, x1 + 1, y1, x2, dp));
        next = Math.max(next, process2(grid, x1, y1 + 1, x2, dp));
        next = Math.max(next, process2(grid, x1, y1 + 1, x2 + 1, dp));
        if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
            dp[x1][y1][x2] = -1;
            return dp[x1][y1][x2];
        }
        if (x1 == x2) {
            dp[x1][y1][x2] = grid[x1][y1] + next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
        return dp[x1][y1][x2];

    }

    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for testing
    public static int[][] generateMatrix(int col, int row) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            arr[i] = generate(col, 2);
        }
        return arr;
    }


    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
        };
        System.out.println(maxCandy(matrix));


    }

}
