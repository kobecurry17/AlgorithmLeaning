package InterviewStage2.class14;

/**
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
 * 为了保证骑士能遇到工作，初始血量至少是多少?根据map返回初始血量
 * </p>
 */
public class SaveThePrincess {

    public static int needMin(int[][] matrix) {
        return process(matrix, matrix.length, matrix[0].length, 0, 0);
    }

    // 来到了matrix[row][col]，还没登上去，到达右下角，返回至少的初始血量
    public static int process(int[][] matrix, int N, int M, int row, int col) {
        if (row == N - 1 && col == M - 1) { // 已经达到右下角了
            return matrix[N - 1][M - 1] < 0 ? (-matrix[N - 1][M - 1] + 1) : 1;
        }
        if (row == N - 1) {
            int rightNeed = process(matrix, N, M, row, col + 1);
            if (matrix[row][col] < 0) { // 3    -7    10
                return -matrix[row][col] + rightNeed;
            } else if (matrix[row][col] >= rightNeed) {  // 3    3    1
                return 1;
            } else { //  3   1    2
                return rightNeed - matrix[row][col];
            }
        }
        if (col == M - 1) {
            int downNeed = process(matrix, N, M, row + 1, col);
            if (matrix[row][col] < 0) { // 3    -7    10
                return -matrix[row][col] + downNeed;
            } else if (matrix[row][col] >= downNeed) {  // 3    3    1
                return 1;
            } else { //  3   1    2
                return downNeed - matrix[row][col];
            }
        }
        int minNextNeed = Math.min(process(matrix, N, M, row, col + 1), process(matrix, N, M, row + 1, col));
        if (matrix[row][col] < 0) { // 3    -7    10
            return -matrix[row][col] + minNextNeed;
        } else if (matrix[row][col] >= minNextNeed) {  // 3    3    1
            return 1;
        } else { //  3   1    2
            return minNextNeed - matrix[row][col];
        }
    }

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

        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = map[n - 1][m - 1] > 0 ? 1 : -map[n - 1][m - 1] + 1;

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
                dp[i][j] = Math.min(down, right);
            }
        }
        // 结果就是 dp[0][0]
        return dp[0][0];
    }

    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max * 2 - max);
        }
        return arr;
    }

    // for testing
    public static int[][] generateMatrix(int col, int row, int max) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            arr[i] = generate(col, max);
        }
        return arr;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int max = 30;
        int[][] matrix = generateMatrix(5, 5, max);
        for (int i = 0; i < loops; i++) {
            if (needMin(matrix) != initHP(matrix)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }
}
