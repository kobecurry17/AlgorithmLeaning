package singlestack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.XMLFormatter;

/**
 * 给定一个只包含正数的数组arr，arr中的任意一个子数组sub
 * 一定能够计算出sub的累加和*sub中的最小值是什么
 * 那么所有子数组中，这个值最大是多少？
 * 前提：都是正数
 */
@SuppressWarnings("all")
public class SubArrSumMutSubMinValue {
    /**
     * 暴力解
     * 时间复杂度O(n²)
     *
     * @param arr
     * @return
     */
    public static int ans1(int[] arr) {
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int start = i;
            int end = i;
            for (int j = i; j >= 0; j--) {
                if (arr[j] >= arr[i]) {
                    start = j;
                } else {
                    break;
                }
            }
            for (int j = i; j < arr.length; j++) {
                if (arr[j] >= arr[i]) {
                    end = j;
                } else {
                    break;
                }
            }
            int[] res = copyArr(arr, start, end);
            Arrays.sort(res);
            int sum = 0;
            for (int k = 0; k < res.length; k++) {
                sum += res[k];
            }
            answer = Math.max(answer, sum * res[0]);
        }
        return answer;
    }

    // 从start开始拷贝数组到end
    private static int[] copyArr(int[] arr, int start, int end) {
        int[] res = new int[end - start + 1];
        for (int i = 0; i <= end - start; i++) {
            res[i] = arr[start + i];
        }
        return res;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    /**
     * 时间复杂度O(n²)
     * 加速了常数项
     *
     * @param arr
     * @return
     */
    private static int ans2(int[] arr) {
        int L, R;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            L = i;
            R = i;
            int sum = arr[i];
            while (L > 0 && arr[L - 1] >= arr[i]) {
                L--;
                sum += arr[L];
            }
            while (R < arr.length - 1 && arr[R + 1] >= arr[i]) {
                R++;
                sum += arr[R];
            }
            max = Math.max(max, sum * arr[i]);
        }
        return max;
    }


    /**
     * 时间复杂度O(n²)
     * 加速了常数项
     *
     * @param arr
     * @return
     */
    private static int ans3(int[] arr) {
        int L, R;
        int max = Integer.MIN_VALUE;
        int[][] res = monotonousStack(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = res[i][0]; j <= res[i][1]; j++) {
                sum += arr[j];
            }
            max = Math.max(max, sum * arr[i]);
        }
        return max;
    }

    /**
     * 通过单调栈，寻找每一个元素作为最小值的边界在哪
     * TODO: 多练习+实现重复值的场景
     *
     * @param arr
     * @return
     */
    private static int[][] monotonousStack(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        int last = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer top = stack.pop();
                int leftLessIndex = stack.isEmpty() ? last : stack.peek() + 1;
                res[top][1] = i - 1;
                res[top][0] = leftLessIndex;
                if (stack.isEmpty()) {
                    last = top;
                }
            }
            stack.push(i);
        }
        Integer top = -1;
        last = -1;
        while (!stack.isEmpty()) {
            top = stack.pop();
            int leftLessIndex = stack.isEmpty() ? 0 : stack.peek() + 1;
            res[top][0] = leftLessIndex;
            res[top][1] = arr.length - 1;
            last = top;
        }
        return res;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 5;
        int maxSize = 4;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
//            int[] arr = new int[]{2,3,4};
            int i1 = ans1(arr);
            int i2 = ans2(arr);
            int i3 = ans3(arr);
            if (i1 != i2 || i3 != i2) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }

}
