package class3;

import java.util.Arrays;

/**
 * <p>
 * 长度为N的数组arr,一定可以组成N^2个数值对
 * 例如 arr = [ 3, 1, 2]
 * 数值对就有 (3, 3) (3, 1) (3, 2) (1, 3) (1, 1) (1, 2) (2, 3) (2, 1) (2, 2)
 * 也就是任一两个数都有数值对，而且自己和自己也算数值对，
 * 数值对怎么排序？规定，第一位数据从小到大，第一位数据一样的，第二位数组也从小到大。
 * 所以上面数值对排序结果为
 * (1, 1) (1, 2) (1, 3) (2, 1) (2, 2) (2, 3) (3, 1) (3, 2) (3, 3)
 * 给定一个数组，和整数k，返回第k小的数值对
 * </p>
 */
@SuppressWarnings("all")
public class KthMinNumPair {

    /**
     * 返回第K小的数值对
     * O(NLogN)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] kthMinPair(int[] arr, int k) {
        if (k < 1 || k > Math.pow(arr.length, 2)) {
            return null;
        }
        int[] newArr = copyArr(arr);
        Arrays.sort(newArr);
        return new int[]{newArr[(k - 1) / newArr.length], newArr[(k - 1) % arr.length]};
    }


    /**
     * 返回第K小的数值对
     * O(N)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] kthMinPair2(int[] arr, int k) {
        if (k < 1 || k > Math.pow(arr.length, 2)) {
            return null;
        }
        int k1 = (k - 1) / arr.length + 1;
        int k2 = (k - 1) % arr.length + 1;
        return new int[]{findKthMin(copyArr(arr), k1), findKthMin(copyArr(arr), k2)};
    }

    /**
     * 在数组上寻找第K小的数
     * O(N)复杂度
     *
     * @param arr
     * @param k
     * @return
     */
    private static int findKthMin(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (true) {
            pivot = arr[(int) (Math.random() * (r - l)) + l];
            range = partition(arr, pivot, l, r);
            if (k > range[1]) {
                l = range[1];
            } else if (k < range[0] + 2) {
                r = range[0] + 1;
            } else {
                break;
            }
        }
        return pivot;
    }

    private static int[] partition(int[] arr, int pivot, int l, int r) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur != more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less, more};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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

    private static int[] copyArr(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 30;
        int maxValue = 300;
        for (int i = 0; i < loops; i++) {
            int arr[] = generate(maxSize, maxValue);
            int k = generate(maxValue);
            int[] ans1 = kthMinPair(arr, k);
            int[] ans2 = kthMinPair2(arr, k);
            if (!arrEquals(ans1, ans2)) {
                System.out.println("Oops");
            }
        }


        System.out.println("Nice");
    }

    private static boolean arrEquals(int[] ans1, int[] ans2) {
        if (null == ans1 && null == ans2) {
            return true;
        }
        try {
            return ans1[0] == ans2[0] && ans1[1] == ans2[1];
        } catch (Exception e) {
            return false;
        }
    }

}
