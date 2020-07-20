package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] ints = netherlandsFlag(arr, L, R);
        process(arr, L, ints[0] - 1);
        process(arr, ints[1] + 1, R);
    }

    private static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (R == L) {
            return new int[]{L, R};
        }

        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
            Arrays.sort(arr1);
            Arrays.stream(arr2).forEach(e -> System.out.print(e + "|"));
            System.out.println();
            sort(arr2);
            Arrays.stream(arr2).forEach(e -> System.out.print(e + "|"));
            for (int j = 0; j < len; j++) {
                if (arr1[j] != arr2[j]) {
                    throw new RuntimeException("出错啦");
                }
            }
        }
        System.out.println("成功");
    }

}
