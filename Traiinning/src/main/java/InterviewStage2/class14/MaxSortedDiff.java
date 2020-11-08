package InterviewStage2.class14;

import java.util.Arrays;

/**
 * 给定一个无需数组arr,返回如果排序之后，相邻数之间的最多大差值
 * {3,1,7,9},如果排序后{1,3,7,9}
 * 相邻数之间的最大差值来自3,7 返回4
 */
@SuppressWarnings("all")
public class MaxSortedDiff {


    public static int maxDiff(int[] arr) {
        Arrays.sort(arr);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i] - arr[i - 1], max);
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
}
