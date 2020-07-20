package sort;

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
        if (R - L < 2) {
            return;
        }
        int i = (int) (Math.random() * (R - L));
        process(arr, L, i);
        process(arr, i + 1, R);
        partition(arr, L, R,i);
    }

    private static void partition(int[] arr, int L, int R) {
        int lIndex = L;
        int rIndex = R;
        if(arr[L])


    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
