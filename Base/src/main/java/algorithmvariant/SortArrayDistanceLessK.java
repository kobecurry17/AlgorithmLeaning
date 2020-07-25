package algorithmvariant;

import datastructure.MaxHeap1;
import datastructure.MaxHeap2;

import java.util.Arrays;

/**
 * 已知一个几乎有序的数组。
 * 几乎有序是指，如果把数组排号顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的
 * 请选择一个合适的排序策略，对这个数组进行排序
 */
public class SortArrayDistanceLessK {

    public static void sortedArrayDistanceLessK(int[] arr, int K) {
        int[] help = new int[K];
        int index = 0;
        MaxHeap2.MyHeap myMaxHeap = new MaxHeap2.MyHeap<Integer>((x, y) -> {
            return x.compareTo(y);
        });
        for (int i = 0; i < arr.length; i++) {
            if (myMaxHeap.size()>K) {
                arr[index++] = (int) myMaxHeap.pop();
            }
            myMaxHeap.push(arr[i]);
        }
        while (!myMaxHeap.isEmpty()) {
            arr[index++] = (int) myMaxHeap.pop();
        }
    }


    /**
     * 生成一个数组，且每个元素与排序之后的位置的距离不超过K
     *
     * @param size
     * @param K
     * @return
     */
    public static int[] randomArrayNoMoveMoreK(int size, int K) {
        int[] arr = new int[size];
        int value = 500;
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (value * Math.random());
        }
        // 先排序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 将 i 和 next 置为 true
     *
     * @param flags
     * @param i
     * @param next
     */
    private static void mark(boolean[] flags, int i, int next) {
        flags[i] = true;
        flags[next] = true;
    }

    /**
     * 数组交换
     *
     * @param arr
     * @param index
     * @param largest
     */
    private static void swap(int[] arr, int index, int largest) {
        int num = arr[index];
        arr[index] = arr[largest];
        arr[largest] = num;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int loop = 50_0000;
        int len = 50;
        int MaxK = 20;
        for (int i = 0; i < loop; i++) {
            int size = (int) (len * Math.random() + 20);
            int K = (int) (MaxK * Math.random());
            int[] arr1 = randomArrayNoMoveMoreK(size, K);
            int[] arr2 = copyArray(arr1);
            sortedArrayDistanceLessK(arr1, K);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                printArray(arr1);
                printArray(arr2);
                throw new RuntimeException("Error");
            }
        }
        System.out.println("Nice");
    }
}
