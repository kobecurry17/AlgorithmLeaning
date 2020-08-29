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
        return process(0, record, n);
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

    /**
     * 对常数项进行优化
     *
     * @param n
     * @return
     */
    private static int nQueen2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        if (n < 1) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit 例如当N = 8 时limit= 0000....0000 11111111
     * @param col
     * @param left
     * @param right
     * @return
     */
    private static int process2(int limit, int col, int left, int right) {
        if (col == limit) {
            return 1;
        }
        int res = 0;
        int allow = limit & (~(col | left | right));
        // 第一个可以放的位置
        int mostRightOne = 0;
        while (0 != allow) {
            mostRightOne = (~allow + 1) & allow;
            allow = allow - mostRightOne;
            res += process2(
                    limit,
                    col | mostRightOne,
                    (left | mostRightOne) << 1,
                    (right | mostRightOne) >>> 1
            );
        }
        return res;
    }

    // for testing
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            int N = generate(maxValue);
            int i1 = nQueen1(N);
            int i2 = nQueen2(N);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }
    }
}
