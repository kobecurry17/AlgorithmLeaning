package island;

/**
 * 在一个矩阵里，上下左右都是1可以视作相连
 * 问：一个有几个岛？
 */
public class IslandSize {

    public static int ans1(int[][] matrix) {
        int[][] backup = copyMatrix(matrix);
        int ans = 0;
        for (int i = 0; i < backup.length; i++) {
            for (int j = 0; j < backup[0].length; j++) {
                ans += process(backup, i, j);
            }
        }
        return ans;
    }

    private static int process(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) {
            return 0;
        }
        matrix[i][j] = 0;
        process(matrix, i - 1, j);
        process(matrix, i + 1, j);
        process(matrix, i, j + 1);
        process(matrix, i, j - 1);
        return 1;
    }

    /**
     * 数组拷贝
     *
     * @param arr
     * @return
     */
    public static int[][] copyMatrix(int[][] arr) {
        int[][] ans = new int[arr.length][arr[0].length];
        for (int i = 0; i != ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = arr[i][j];
            }
        }
        return ans;
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
    public static int[][] generateMatrix(int col, int row, int max) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            arr[i] = generate(col, max);
        }
        return arr;
    }


    /**
     * 问题升级：
     * 如果岛的面积很大，缓存塞不下，怎么办？
     * 并查集解法
     *
     * @param matrix
     * @return
     */
    public static int ans2(int[][] matrix){

    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int row = 15;
        int col = 20;
        int max = 2;
        for (int i = 0; i < loops; i++) {
            int[][] matrix = generateMatrix(col, row, max);
            int i1 = ans1(matrix);
        }
    }


}
