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
        if (level > n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < level; j++) {
                {
                    if (!isValid(record,level,j)) {
                        return 0;
                    } else {
                        record[i] = j;
                        res += process(level + 1, record, n);
                        record[j] = 0;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(nQueen1(10));

    }

}
