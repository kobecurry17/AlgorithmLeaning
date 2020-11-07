package InterviewStage2.class13;


/**
 * <p>
 * 给定一个矩阵matrix,在给定一个k值
 * 返回子矩阵最大累加和
 * </p>
 * 收获:在写动态规划之前，应该提前理清所有边界情况，再动手。
 */
@SuppressWarnings("all")
public class MatrixSum {


    /**
     * 暴力方法
     * O(N^6)
     *
     * @param matrix
     * @return
     */
    public static int maxSumLessThanK1(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int startX = 0; startX < n; startX++) {
            for (int startY = 0; startY < m; startY++) {
                for (int endX = startX; endX < n; endX++) {
                    for (int endY = startY; endY < m; endY++) {
                        int sum = 0;
                        for (int x = startX; x <= endX; x++) {
                            for (int y = startY; y <= endY; y++) {
                                sum += matrix[x][y];
                            }
                        }
                        max = Math.max(max, sum);
                    }
                }
            }
        }
        return max;
    }


    /**
     * O(N^4)
     *
     * @param matrix
     * @return
     */
    public static int maxSumLessThanK2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = matrix[0][0];


        int[][] help = new int[n + 1][m + 1];
        // help [i][j] => matrix[0][0] +...+matrix[i][j]
        help[1][1] = matrix[0][0];
        for (int j = 2; j <= m; j++) {
            help[1][j] = help[1][j - 1] + matrix[0][j - 1];
            max = Math.max(max, help[0][j]);
        }
        for (int i = 2; i <= n; i++) {
            help[i][1] = help[i - 1][1] + matrix[i - 1][0];
            max = Math.max(max, help[i - 1][0]);
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                help[i][j] = help[i][j - 1] + help[i - 1][j] - help[i - 1][j - 1] + matrix[i - 1][j - 1];
                max = Math.max(max, help[i][j]);
            }
        }

        for (int startX = 0; startX <= n; startX++) {
            for (int startY = 0; startY <= m; startY++) {

                for (int endX = startX; endX <= n; endX++) {
                    for (int endY = startY; endY <= m; endY++) {
                        max = Math.max(max, help[endX][endY] + help[startX][startY] - help[startX][endY] - help[endX][startY]);
                    }
                }

            }
        }
        return max;
    }

    /**
     * 子数组最大累加和
     *
     * @param arr
     * @return
     */
    private static int maxSubArray(int[] arr) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, sum);
            max = Math.max(sum - min, max);
            maxNum = Math.max(maxNum, arr[i]);
        }
        if (maxNum < 0) {
            return 0;
        }
        return max;
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
        int max = 10;
        for (int i = 0; i < loops; i++) {
            int[][] matrix = generateMatrix(3, 3, max);
            matrix = new int[][]{{-6, 0, 7}, {2, 7, -9}, {-4, 1, 2}};
            if (maxSumLessThanK1(matrix) != maxSumLessThanK2(matrix)) {
                maxSumLessThanK2(matrix);
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");

    }

}
