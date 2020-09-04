package window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个只包含正数的数组arr，arr中的任意一个子数组sub
 * 一定能够计算出sub的累加和*sub中的最小值是什么
 * 那么所有子数组中，这个值最大是多少？
 */
@SuppressWarnings("all")
public class SubArrSumMutSubMinValue {
    /**
     * 暴力解
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
     * 滑动窗口
     *
     * @param arr
     * @return
     */
    private static int ans2(int[] arr) {
        int L = 0;
        int R = 0;

        LinkedList<Integer> qMin = new LinkedList<Integer>();
        int ans = Integer.MIN_VALUE;
        int min = 0;

        // 当R越界的时候进行答案计算
        while (L < arr.length) {
            while (R < arr.length) {
                if (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                   min =  qMin.pollFirst();
                    break;
                }
                qMin.addLast(R);
                R++;
            }
            int sum = 0;
            for (int i = L; i < min; i++) {
                sum += arr[i];
            }
            ans = Math.max(sum * arr[min], ans);
            if (!qMin.isEmpty()&&qMin.getFirst() == L) {
                qMin.pollFirst();
            }
            L++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 5;
        int maxSize = 4;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
//            int[] arr = new int[]{2, 3, 4};
            int i1 = ans1(arr);
            int i2 = ans2(arr);
            if (i1 != i2) {
                ans2(arr);
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }

}
