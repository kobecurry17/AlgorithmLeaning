package trymodel;

/**
 * N 皇后问题
 * 每个皇后的横，纵，对角线都不能摆放皇后
 * 问：在N*N的棋盘上摆N皇后一共有多少种摆法
 */
public class NQueen {

    // for testing
    public int generateSize(int max) {
        return (int) (Math.random() * max);
    }

    // TODO 再练习
    public static int nQueen1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(1, record, n);
    }

    /**
     * n 皇后递归解法
     *
     * @param level  当前在第几层
     * @param record 每一层的皇后在什么位置
     * @param n      一共有几层
     * @return
     */
    private static int process(int level, int[] record, int n) {
        // 摆完了
        if (level == n) {
            return 1;
        }
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (isValid(record, level, i)) {
                record[level] = i;
                res += process(level + 1, record, n);
            }
        }
        return res;
    }

    /**
     * 验证level层皇后可以摆在index位置吗？
     *
     * @param record
     * @param level
     * @param index
     * @return
     */
    private static boolean isValid(int[] record, int level, int index) {
        for (int i = 0; i < level; i++) {
            if (record[i] == index || Math.abs(record[i] - index) == Math.abs(level - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(nQueen1(10));

    }

}
