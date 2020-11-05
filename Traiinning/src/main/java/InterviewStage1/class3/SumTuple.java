package InterviewStage1.class3;

import java.util.Arrays;

/**
 * 给定一个有序数组arr,给定一个整数aim
 * 1)返回累加和为aim的，所有不同二元组
 * 2)返回累加和为aim的，所有不同三元组
 */
public class SumTuple {

    /**
     * 返回累加和为aim的，所有不同二元组
     *
     * @param arr
     * @param aim
     * @return
     */
    public static void printTwoTuple(int[] arr, int aim) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < aim) {
                left++;
            } else if (arr[left] + arr[right] > aim) {
                right--;
            } else {
                System.out.println(arr[left] + "+" + arr[right] + "=" + aim);
                left++;
                right--;
            }
        }
    }

    /**
     * @param arr
     * @param aim
     */
    public static void printThreeTuple(int[] arr, int aim) {
        if (arr == null || arr.length < 3) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            printRest(arr, aim, i);
        }

    }

    private static void printRest(int[] arr, int aim, int index) {
        int rest = aim - arr[index];
        int left = index + 1;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < rest) {
                left++;
            } else if (arr[left] + arr[right] > rest) {
                right--;
            } else {
                if (arr[left] != arr[left - 1]) {
                    System.out.println(String.format("%d+%d+%d=%d", arr[index], arr[left], arr[right], aim));
                }
                left++;
                right--;
            }
        }
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 9};
        printTwoTuple(arr, 10);
        System.out.println("==========================");
        printThreeTuple(arr, 13);
    }

}
