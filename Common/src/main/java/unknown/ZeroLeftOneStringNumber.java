package unknown;

/**
 * 给定一个数N，想象只有0和1组成，组成的长度为N的字符串
 * 如果某个字符串任何1左边都有0紧挨着，认为这个字符串达标，返回有多少个达标的字符串
 */
public class ZeroLeftOneStringNumber {

    /**
     * 方法1
     *
     * @param n
     * @return
     */
    public static int ans1(int n) {
        return process(0, n);
    }

    private static int process(int i, int n) {
        if (i == n) {
            return 1;
        }
        if (i == n - 1) {
            return 2;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    /**
     * 转成斐波那契数列
     *
     * @param n
     * @return
     */
    public static int ans2(int n) {
        return 0;
    }

    public static void main(String[] args) {
        int loops = 50;

        for (int i = 0; i < loops; i++) {
            System.out.println(ans1(i));
        }
    }

}
