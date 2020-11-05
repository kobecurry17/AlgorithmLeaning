package InterviewStage1.class1;

import java.util.Arrays;

/**
 * 给定一个有序数组arr,从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 */
@SuppressWarnings("all")
public class RopeOverWrite {

    /**
     * 暴力解法
     *
     * @param points
     * @param ropLength
     * @return
     */
    public static int ropeOverWrite1(int[] points, int ropLength) {
        int max = -1;
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                if (points[j] - points[i] <= ropLength) {
                    max = Math.max(j - i + 1, max);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    public static int ropeOverWrite2(int[] points, int ropLength) {
        int L = 0;
        int R = 0;
        int max = -1;
        while (R < points.length && L < points.length) {
            if (points[R] - points[L] <= ropLength) {
                max = Math.max(R - L + 1, max);
                R++;
            } else {
                L++;
                R++;
            }
        }
        return max;
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 30;
        int maxValue = 50;
        int maxRope = 100;
        for (int i = 0; i < loops; i++) {
            int rope = generate(maxRope);
            int[] arr = generate(maxSize, maxValue);
            Arrays.sort(arr);
            if (ropeOverWrite1(arr, rope) != ropeOverWrite2(arr, rope)) {
                System.out.println("Oops!");
            }
            System.out.println("Nice");
        }
    }


}
