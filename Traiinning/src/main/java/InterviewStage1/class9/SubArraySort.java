package InterviewStage1.class9;

/**
 * 给定一个数组
 * 如果只能在一个子数组上排序
 * 返回需要排序的最短子数组长度
 */
@SuppressWarnings("all")
public class SubArraySort {

    /**
     * 给定一个数组,如果只能在一个子数组上排序,返回需要排序的最短子数组长度
     */
    public static int leastSort(int[] arr) {
        int lastLessThenMax = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            } else {
                lastLessThenMax = i;
            }
        }

        int lastGreaterThenMin = 0;
        int min = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= min) {
                min = arr[i];
            } else {
                lastGreaterThenMin = i;
            }
        }
        return lastLessThenMax- lastGreaterThenMin  +1;
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
    public static void print(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("\n==================\n");
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 6, 2, 4, 8, 9};
        System.out.println(leastSort(arr));
    }
}
