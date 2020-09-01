package window;

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


    private static int minTime1(int[] arr, int a, int b) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process(arr, a, b, 0, 0);
    }

    private static int process(int[] arr, int a, int b, int timeLine, int index) {
        if (index == arr.length) {
            return timeLine;
        }
        int yes = process(arr, a, b, timeLine > arr[index] ? timeLine + a : arr[index] + a, index + 1);
        int no = process(arr, a, b, timeLine > arr[index] ? timeLine + b : arr[index] + b, index + 1);
        return Math.max(timeLine, Math.min(yes, no));
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxWeight = 30;
        int maxValue = 20;
        int maxSize = 10;
        int maxBags = 50;
        for (int i = 0; i < loops; i++) {
//            int[] arr = generate((int) (Math.random() * maxSize), maxValue);
//            int a = generate(maxValue);
//            int b = generate(maxValue);
            int[] arr = {3,6,2};
            int a = 3;
            int b= 2;
            int ans1 = minTime1(arr, a, b);

            int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
            int a1 = 3;
            int b1 = 10;
            System.out.println(minTime1(test,a1,b1));

        }
    }


}
