package bfprt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在一堆数组中找到第k小的数
 */
public class KthMin {


    /**
     * 在一堆数组中找到第k小的数
     * 暴力解 时间复杂度O(N*logN)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int kthMin1(int[] arr, int k) {
        if (k > arr.length || k == 0) {
            return -1;
        }
        Arrays.sort(arr);
        return arr[k - 1];
    }

    /**
     * 利用大根堆
     * 实现时间复杂度O(N*logk)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int kthMin2(int[] arr, int k) {
        if (k > arr.length || k == 0) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (heap.peek() > arr[i]) {
                heap.poll();
                heap.add(arr[i]);
            }
        }
        return heap.peek();
    }

    /**
     * 比较器
     */
    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
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

    /**
     * 快排的思想
     * 时间复杂度O(N)->O(N*logN)不稳定
     *
     * @param arr
     * @param k
     * @return
     */
    public static int kthMin3(int[] arr, int k) {
        if (k > arr.length || k == 0) {
            return -1;
        }
        // 从1~N 上第k小的数
        return process(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * @param arr
     * @param L
     * @param R
     * @param index
     * @return
     */
    private static int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[(int) (Math.random() * (R - L + 1)) + L];
        int[] range = partition(arr, L, R, pivot);
        if (range[0] <= index && range[1] >= index) {
            return arr[range[0]];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    /**
     * @param arr   数组
     * @param L     左边界
     * @param R     右边界
     * @param pivot 基准数
     * @return
     */
    public static int[] partition(int[] arr, int L, int R, int pivot) {

        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }


    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 数组拷贝
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int maxValue = 500;
        int maxK = 30;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, maxValue);
            int k = generate(maxK);
            if (kthMin3(arr, k) != kthMin2(arr, k)) {
                System.out.println("Oops!");
            }
        }
    }


}
