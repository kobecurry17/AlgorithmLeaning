package matrix;

/**
 * ZigZag打印矩阵
 * <p>
 * 例如：
 * 1 3 5
 * 2 4 6
 * 7 8 9
 * 输出：1 2 3 5 4 7 8 6 9
 */
public class ZigZag {


    /**
     * ZigZag 打印
     *
     * @param matrix
     */
    public static void print(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int N = matrix.length + matrix[0].length - 2;
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        boolean asc = false;
        for (int i = 0; i < N; i++) {
            print(matrix, i1, j1, i2, j2, asc);
            if (j2 < matrix[0].length-1) {
                j2++;
            } else {
                i2++;
            }
            if (i1 < matrix.length-1) {
                i1++;
            } else {
                j1++;
            }
            asc = !asc;
        }
        print(matrix, i1, j1, i2, j2, asc);
    }

    /**
     * asc:打印[i1,j1]->[i2,j2]
     * !asc:打印[i2,j2]->[i1,j1]
     *
     * @param matrix
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     * @param asc
     */
    private static void print(int[][] matrix, int i1, int j1, int i2, int j2, boolean asc) {
        if (asc) {
            for (int i = 0; i <= i1 - i2; i++) {
                System.out.print(matrix[i1 - i][j1 + i] + " ");
            }
        } else {
            for (int i = 0; i <= i1 - i2; i++) {
                System.out.print(matrix[i2 + i][j2 - i] + " ");
            }
        }
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int[][] matrix = {{1, 3, 5,7}, {2, 4, 6,10}, {7, 8, 9,11}};
        print(matrix);
    }
}
