package InterviewStage2.class13;

/**
 * <p>
 * 最长递增链的长度
 * </p>
 * 给定一个二维数组matrix,可以从任何位置出发
 * 每一步可以向上、下、左、右是四个方向，返回最大递增链的长度
 */
public class LongestIncreaseLength {


    public static int longestLength(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] help = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, process(matrix, help, i, j, null));
            }
        }
        return max;
    }

    private static int process(int[][] matrix, int[][] help, int i, int j, Integer last) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || (null != last && matrix[i][j] != last + 1)) {
            return 0;
        }
        if (help[i][j] > 0) {
            return help[i][j];
        }
        int p1 = process(matrix, help, i + 1, j, matrix[i][j]);
        int p2 = process(matrix, help, i - 1, j, matrix[i][j]);
        int p3 = process(matrix, help, i, j + 1, matrix[i][j]);
        int p4 = process(matrix, help, i, j - 1, matrix[i][j]);
        help[i][j] = 1 + Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return help[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 4, 3}, {3, 1, 2}, {2, 1, 3}};
        System.out.println(longestLength(matrix));
    }

}
