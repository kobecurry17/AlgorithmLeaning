package matrix;

/**
 * 转圈打印矩阵
 * <p>
 * 例如:
 * {1,3,4,5,6,7}
 * {2,1,3,5,7,8}
 * {1,2,3,1,2,1}
 * 输出
 * 1 3 4 5 6 7 8 1 2 1 3 2 1 2 1 3 5 7
 */
public class RotationPrint {


    public static void rotationPrint(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return;
        }
        int round = (Math.min(matrix.length, matrix[0].length) + 1) / 2;
        for (int i = 0; i < round; i++) {
            print(matrix, i, i, matrix.length - 1 - i, matrix[0].length - 1 - i);
        }
    }

    /**
     * 打印矩形对角线为[i1,j2]->[i2,j2]的矩形边框
     *
     * @param matrix
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     */
    private static void print(int[][] matrix, int i1, int j1, int i2, int j2) {
        for (int i = i1; i <= i2; i++) {
            System.out.print(matrix[i][j1] + " ");
        }
        for (int i = j1+1; i <= j2; i++) {
            System.out.print(matrix[i2][i] + " ");
        }
        if (j2 > j1) {
            for (int i = i2-1; i >= i1; i--) {
                System.out.print(matrix[i][j2] + " ");
            }
        }
        if (i2 > i1) {
            for (int i = j2-1; i > j1; i--) {
                System.out.print(matrix[i1][i] + " ");
            }
        }

    }


    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 3, 4, 5, 6, 7},
                        {1, 2, 3, 1, 2, 1},
                        {2, 1, 3, 5, 7, 8},
                        {7, 1, 3, 6, 5, 0},
                        {3, 5, 2, 1, 2, 6},
                        {10, 6, 3, 2, 4, 2},
                        {2, 8, 8, 9, 3, 1},
                };
        rotationPrint(matrix);
    }


}
