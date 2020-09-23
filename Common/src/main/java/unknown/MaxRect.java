package unknown;

/**
 * 矩阵中，边框都是1的正方形最大是多少
 */
@SuppressWarnings("all")
public class MaxRect {

    /**
     * 矩阵中最大的正方形边长
     *
     * @param matrix
     * @return
     */
    public static int maxRect(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] col = getColArr(matrix, n, m);
        int[][] row = getRowArr(matrix, n, m);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int maybe = Math.min(col[i][j], row[i][j]);
                    if (maybe > max) {
                        for (int k = maybe; k > 0; k--) {
                            if (col[i][j + k - 1] >= maybe && row[i + k - 1][j + k - 1] >= maybe) {
                                max = Math.max(maybe, max);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * 预处理矩阵，得到每个位置下方有多少个连续的1
     *
     * @param matrix
     * @return
     */
    private static int[][] getRowArr(int[][] matrix, int n, int m) {
        int[][] res = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    res[i][j] = ++count;
                } else {
                    count = 0;
                }
            }
        }
        return res;
    }


    private static int[][] getColArr(int[][] matrix, int n, int m) {
        int[][] res = new int[n][m];
        for (int i = m - 1; i >= 0; i--) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[j][i] == 1) {
                    res[j][i] = ++count;
                } else {
                    count = 0;
                }
            }
        }
        return res;

    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 0, 1},{1,1,1,1},{0,1,1,0}};
        System.out.println(maxRect(matrix));
        int[][] matrix1 = {{1, 1, 1, 1},{1,0,1,1},{1,1,1,1},{1,1,1,1,}};
        System.out.println(maxRect(matrix1));

    }

}
