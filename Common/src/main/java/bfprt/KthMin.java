package bfprt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在一堆数组中找到第k小的数
 */
@SuppressWarnings("all")
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
     * 通过partition找到pivot的左右边界
     *
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


    public static int kthMin4(int[] array, int k) {
        if (k > array.length || k == 0) {
            return -1;
        }
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int L, int R, int k) {
        if (L == R) {
            return arr[L];
        }
        int piovt = getMidanOfMidans(arr, L, R);
        int[] range = partition(arr, L, R, piovt);
        if (k >= range[0] && k <= range[1]) {
            return arr[k];
        } else if (k < range[0]) {
            return bfprt(arr, L, range[0] - 1, k);
        } else {
            return bfprt(arr, range[1] + 1, R, k);
        }
    }

    // arr[L...R]  五个数一组
    // 每个小组内部排序
    // 每个小组中位数领出来，组成marr
    // marr中的中位数，返回
    private static int getMidanOfMidans(int[] arr, int L, int R) {

            int size = R - L + 1;
            int offset = size % 5 == 0 ? 0 : 1;
            int[] mArr = new int[size / 5 + offset];
            for (int team = 0; team < mArr.length; team++) {
                int teamFirst = L + team * 5;
                // L ... L + 4
                // L +5 ... L +9
                // L +10....L+14
                mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
            }
            // marr中，找到中位数
            // marr(0, marr.len - 1,  mArr.length / 2 )
            return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }


    // 得到中位数
    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    // 插入排序
    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
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
            if (kthMin4(arr, k) != kthMin2(arr, k)) {
                System.out.println("Oops!");
            }
        }
    }


}
