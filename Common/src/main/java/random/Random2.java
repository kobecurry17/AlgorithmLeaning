package random;

import java.util.Random;

/**
 * 给定一个f方法，可以等概率返回13-50，问：只利用他怎么得到等概率返回的10-100
 */
public class Random2 {

    /**
     * 通过基础方法得到等概率0和1的随机方法
     *
     * @return
     */
    public static int randomZeroOrOne() {
        int mid = 13 + 18;
        int rand = 0;
        do {
            rand = rand();
        } while (rand == mid);
        return rand < mid ? 0 : 1;
    }

    /**
     * 等概率返回13-50
     *
     * @return
     */
    public static int rand() {
        Random random = new Random();
        return random.nextInt(38) + 13;
    }

    /**
     * 随机返回10~100
     *
     * @return
     */
    public static int randomTenToOneOEighth() {
        int res = Integer.MAX_VALUE;
        while (res > 90) {
            res = 0;
            for (int i = 0; i < 7; i++) {
                res += randomZeroOrOne() * Math.pow(2, i);
            }
        }
        return res + 10;
    }

    // for test
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "=======" + arr[i]);
        }
    }


    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int arr[] = new int[150];
        for (int i = 0; i < 10000000; i++) {
            arr[randomTenToOneOEighth()]++;
        }
        printArr(arr);
    }
}
