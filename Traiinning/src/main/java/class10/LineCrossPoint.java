package class10;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 给定两个数组arrX和arrY,长度都为 N。
 * 代表二维平面上有N个点，第i个点的x坐标和yy坐标分别为arrX[i] 和 arrY[i]
 * 返回一条直线最多能闯过多少个点
 * </p>
 */
public class LineCrossPoint {

    /**
     * 时间复杂度O(N^3)
     * 有错
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

    public static class Point {
        int x;
        int y;
    }


    public static Point[] cast(int[] arrX, int[] arrY) {
        Point[] points = new Point[arrX.length];
        for (int i = 0; i < arrX.length; i++) {
            points[i] = new Point();
            points[i].x = arrX[i];
            points[i].y = arrY[i];
        }
        return points;
    }

    public static int crossPoint2(Point[] points) {
        if (null == points) {
            return 0;
        }
        if (points.length < 3) {
            return points.length;
        }
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int samePoint = 1;
            int sameX = 1;
            int sameY = 1;
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                if (points[j].y == points[i].y && points[j].x == points[i].x) {
                    samePoint++;
                    continue;
                }
                if (points[j].y == points[i].y) {
                    sameY++;
                    continue;
                }
                if (points[j].x == points[i].x) {
                    sameX++;
                    continue;
                }
                Double slope = slope(points[i], points[j]);
                Integer times = map.getOrDefault(slope, 1);
                map.put(slope, times + 1);
            }
            max = Math.max(sameX, sameY);
            max = Math.max(samePoint, max);
            for (Double key : map.keySet()) {
                max = Math.max(max, map.get(key));
            }
        }
        return max;
    }

    private static Double slope(Point a, Point b) {
        return Math.abs((a.y - b.y) / (double) (a.x - b.x));
    }


    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) - max / 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int size = 5;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            int[] arrX = generate(size, maxValue);
            int[] arrY = generate(size, maxValue);
//            if (crossPoint1(arrX, arrY) != crossPoint2(cast(arrX, arrY))) {
//                crossPoint1(arrX, arrY);
            crossPoint2(cast(arrX, arrY));
//                System.out.println("Oops!");
        }
    }
}