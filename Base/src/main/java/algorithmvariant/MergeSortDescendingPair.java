package algorithmvariant;

import java.util.Arrays;

/**
 * 归并排序的变种，降序对
 * 在数组中，每个数右边比他小的数则可以组成一对降序对
 * 如：数组 【 1，4，8，2，4，3】 的降序对为： [4,2][4,3][8,2][8,4][8,3][4,3] 共 6 对
 */
public class MergeSortDescendingPair {

    /**
     * 数组中降序对的个数
     *
     * @param arr
     * @return
     */
    public static int DescendingPairSize(int[] arr) {
        if (null != arr && arr.length > 1) {
            return process(arr, 0, arr.length - 1);
        }
        return 0;
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        } else {
            int M = L + ((R - L) >> 1);
            return process(arr, L, M) +
                    process(arr, M + 1, R) +
                    merge(arr, L, M, R);
        }
    }

    /**
     * 合并数据
     *
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    private static int merge(int[] arr, int L, int M, int R) {

        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int size = 0;
        int[] help = new int[R - L + 1];
        while (p1 <= M && p2 <= R) {
            if (arr[p1] <= arr[p2]) {
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
                size += M - p1 + 1;
            }
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
        return size;
    }

    /**
     * 对数器
     *
     * @param arr 数组
     * @return 将许对的个数
     */
    public static int getDescendingPairSize(int[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    size++;
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int size = 500000;
        int value = 500;
        for (int i = 0; i < size; i++) {
            // 避免长度为 0
            int len = (int) (Math.random() * 40) + 1;
            int[] arr1 = new int[len];
            int[] arr2 = new int[len];
            for (int j = 0; j < len; j++) {
                int num = (int) (Math.random() * value);
                arr1[j] = num;
                arr2[j] = num;
            }
            int res1 = DescendingPairSize(arr1);
            int res2 = getDescendingPairSize(arr2);
            if (res1 != res2) {
                Arrays.stream(arr2).forEach(e -> System.out.print(e + " |"));
                System.out.println();
                System.out.println(String.format("%d----%d", res1, res2));
                throw new RuntimeException("出错啦");
            }
        }
        System.out.println("成功");
    }
}
