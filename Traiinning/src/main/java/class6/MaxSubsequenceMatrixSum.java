package class6;

/**
 * 最大子矩阵累加和
 */
public class MaxSubsequenceMatrixSum {


    /**
     * 子矩阵最大累加和
     * O(N4)暴力解
     *
     * @param matrix
     * @return
     */
    public static int right(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < matrix.length - i; k++) {
                    for (int l = 0; l < matrix[0].length - j; l++) {
                        max = Math.max(max, calculate(matrix, i, j, k, l));
                    }
                }
            }
        }
        return max;
    }

    private static int calculate(int[][] matrix, int x, int y, int length, int width) {
        int sum = 0;
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= width; j++) {
                sum += matrix[x + i][y + j];
            }
        }
        return sum;
    }

    // for testing
    public static int[][] generate(int length, int width) {
        int[][] arr = new int[length][width];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * 10) - 5;
            }
        }
        return arr;
    }

    private static int maxSum(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix[0].length ; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                max = Math.max(max, process(matrix, i, j));
            }
        }
        return max;
    }


    private static int process(int[][] matrix, int top, int bottom) {
        int max = Integer.MIN_VALUE;
        int[] help = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = top; j <= bottom ; j++) {
                help[i] += matrix[i][j];
            }
        }
        max = Math.max(max, maxSum(help));
        return max;
    }

    /**
     * 求一个数组上最大的累加和
     *
     * @param help
     * @return
     */
    private static int maxSum(int[] help) {
        int min = 0;
        int maxNum = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < help.length; i++) {
            sum += help[i];
            min = Math.min(min, sum);
            max = Math.max(max, sum - min);
            maxNum = Math.max(maxNum, help[i]);
        }
        max = Math.max(max, sum - min);
        if (max <= 0) {
            return maxNum;
        }
        return max;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        for (int i = 0; i < loops; i++) {
            int width = (int) (Math.random() * 30) + 1;
            int length = (int) (Math.random() * 20) + 1;
            int[][] matrix = generate(length, width);
            if (right(matrix) != maxSum(matrix)) {
                System.out.println("Oops!");
                right(matrix);
                maxSum(matrix);
            }
        }
        System.out.println("nice");
    }


}
