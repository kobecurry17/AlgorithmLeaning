package class10;

/**
 * <p>
 * 给定两个数组arrX和arrY,长度都为 N。
 * 代表二维平面上有N个点，第i个点的x坐标和yy坐标分别为arrX[i] 和 arrY[i]
 * 返回一条直线最多能闯过多少个点
 * </p>
 * TODO:看一下视频
 *
 */
public class LineCrossPoint {

    /**
     * 时间复杂度O(N^3)
     *
     * @param arrX
     * @param arrY
     * @return
     */
    public static int crossPoint1(int[] arrX, int[] arrY) {
        //base case
        if (arrX.length != arrY.length) {
            return 0;
        }
        if (arrX.length < 3) {
            return arrX.length;
        }
        int N = arrX.length;
        int max = 2;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int a = (arrY[i] - arrY[j]);
                int b = (arrX[j] - arrX[i]);
                int c = (arrX[i] * arrY[j]) - (arrY[i] * arrX[j]);
                int base = 2;
                for (int k = 0; k < N; k++) {
                    if (k != i && k != j) {
                        if (a * arrX[k] + b * arrY[k] + c == 0) {
                            base++;
                        }
                    }
                }
                max = Math.max(max, base);
            }
        }
        return max;
    }


}
