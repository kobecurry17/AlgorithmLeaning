package window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个整型数组arr和一个整数num
 * 某个arr中的子数组sub,如果想达标，必须满足：
 * sub中最大值-sub中最小值<=num
 * 返回arr中达标子数组数量
 */
@SuppressWarnings("all")
public class ReachTheStandard {

    /**
     * 达标子数组数量
     * 暴力解法
     *
     * @param arr
     * @param num
     * @return
     */
    public static int ans1(int[] arr, int num) {
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int[] res = copyArr(arr, i, j);
                Arrays.sort(res);
                if (res[res.length - 1] - res[0] <= num) {
                    answer++;
                }
            }
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

    /**
     * 最大值窗口和最小值窗口加速
     *
     * @param arr
     * @param num
     * @return
     */
    private static int ans2(int[] arr, int num) {
        int res = 0;
        LinkedList<Integer> maxWindow;
        LinkedList<Integer> minWindow;

        for (int L = 0; L < arr.length; L++) {
            maxWindow = new LinkedList<>();
            minWindow = new LinkedList<>();
            for (int R = L; R < arr.length; R++) {
                if (maxWindow.isEmpty() || maxWindow.peekFirst() < L || arr[maxWindow.peekFirst()] < arr[R]) {
                    while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                        maxWindow.pollLast();
                    }
                    maxWindow.addLast(R);
                    if (maxWindow.peekFirst() < L) {
                        maxWindow.pollFirst();
                    }
                }
                if (minWindow.isEmpty() || minWindow.peekFirst() < L || arr[minWindow.peekFirst()] > arr[R]) {
                    while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                        minWindow.pollLast();
                    }
                    minWindow.addLast(R);
                    if (minWindow.peekFirst() < L) {
                        minWindow.pollFirst();
                    }
                }
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] <= num) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 纯窗口
     *
     * @param arr
     * @param num
     * @return
     */
    private static int ans3(int[] arr, int num) {
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();

        int L = 0;
        int R = 0;
        int res = 0;

        while (L < arr.length) {
            while (R < arr.length) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);

                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);
                if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) {
                    break;
                }
                R++;
            }
            res += R - L;

            if (qMax.getFirst() == L) {
                qMax.pollFirst();
            }

            if (qMin.getFirst() == L) {
                qMin.pollFirst();
            }
            L++;

        }

        return res;
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

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 10;
        int maxSize = 4;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
            int num = generate(maxValue);
            int i1 = ans1(arr, num);
            int i2 = ans2(arr, num);
            int i3 = ans3(arr, num);
//            ans3(new int[]{4,6,5,8}, 2);
            if (i1 != i3) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }


}
