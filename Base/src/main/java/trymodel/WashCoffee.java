package trymodel;

import java.util.Arrays;

/**
 * <p>
 * 洗咖啡杯问题
 * </p>
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台咖啡机，一次只能刷一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡机也可以自己挥发干净，时间耗费b,咖啡杯可以自行挥发
 * 返回所有咖啡杯变干净的最早完成时间
 * 三个入参: int[] arr, int a,int b
 */
public class WashCoffee {

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    /**
     * 动态规划
     *
     * @param arr
     * @param a
     * @param b
     * @return
     */
    private static int minTime2(int[] arr, int a, int b) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return ans2(arr, a, b);
    }

    /**
     * 洗杯子最少时间
     *
     * @param arr
     * @param a
     * @param b
     * @return
     */
    private static int ans2(int[] arr, int a, int b) {
        int washLineSize = 0;
        int timeLine = arr[arr.length - 1] + b;
        for (int i = 0; i < arr.length; i++) {
            washLineSize = Math.max(washLineSize, arr[i]) + a;
        }
        int[][][] dp = new int[arr.length + 1][washLineSize + 1][timeLine + 1];



        for (int i = 0; i < dp[0].length; i++) {
            for (int j = 0; j < dp[0][0].length; j++) {
                dp[arr.length][i][j] = j;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                for (int k = dp[0][0].length - 1; k >= 0; k--) {
                    int end = Math.max(arr[i], j) + a;
                    int yes = Integer.MAX_VALUE;
                    if (Math.max(end, timeLine) <= timeLine  && end <= washLineSize) {
                        yes = dp[i + 1][end][Math.min(end, timeLine)];
                    }

                    int no = Integer.MAX_VALUE;
                    if (k - arr[i] >= b) {
                        no = dp[i + 1][j][k];
                    } else if (arr[i] + b < dp[0][0].length) {
                        no = dp[i + 1][j][arr[i] + b];
                    }
                    dp[i][j][k] = Math.min(yes, no);
                }
            }
        }
        return dp[0][0][0];
    }


    /**
     * 洗杯子最少时间
     *
     * @param arr
     * @param a
     * @param b
     * @return
     */
    private static int minTime1(int[] arr, int a, int b) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process(arr, a, b, 0, 0, 0);
    }

    private static int process(int[] arr, int a, int b, int timeLine, int washLine, int index) {
        if (index == arr.length) {
            return timeLine;
        }
        int end = Math.max(arr[index], washLine) + a;
        int yes = process(arr, a, b, Math.max(end, timeLine), end, index + 1);
        int no = Integer.MAX_VALUE;
        if (timeLine > arr[index] && timeLine - arr[index] >= b) {
            no = process(arr, a, b, timeLine, washLine, index + 1);
        } else {
            no = process(arr, a, b, arr[index] + b, washLine, index + 1);
        }
        return Math.min(yes, no);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 20;
        int maxSize = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate((int) (Math.random() * maxSize), maxValue);
            int a = generate(maxValue);
            int b = generate(maxValue);
//            arr=new int[]{2};
//            a = 1;
//            b = 2;
            int ans1 = minTime1(arr, a, b);
            int ans2 = minTime2(arr, a, b);
            if (ans1 != ans2) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }


}
