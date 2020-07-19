package sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void mergeSort1(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        } else {
            // 中位数
            int M = L + ((R - L) >> 1);
            process(arr, L, M);
            process(arr, M + 1, R);
            merge(arr, L, M, R);
        }
    }

    private static void merge(int[] arr, int L, int M, int R) {

        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int[] help = new int[R - L + 1];
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 不用递归的归并算法
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 合并的大小
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                // 注意边界
                // 注意边界
                // 注意边界
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }


    public static void main(String[] args) {
        int size = 500000;
        int value = 500;
        for (int i = 0; i < size; i++) {
            // 避免长度为 0
            int len = (int) (Math.random() * 40) + 1;
            int[] arr1 = new int[len];
            int[] arr2 = new int[len];
            int[] arr3 = new int[len];
            for (int j = 0; j < len; j++) {
                int num = (int) (Math.random() * value);
                arr1[j] = num;
                arr2[j] = num;
                arr3[j] = num;
            }

            Arrays.sort(arr1);
            mergeSort1(arr2);
            mergeSort2(arr3);

            for (int j = 0; j < len; j++) {
                if (arr1[j] != arr2[j] || arr1[j] != arr3[j]) {
                    throw new RuntimeException("出错啦");
                }
            }
        }
        System.out.println("成功");
    }
}
