package InterviewStage2.class16;

import java.util.Arrays;

/**
 * <p>
 * 给定两个整数数组A和B
 * A是长度为m、元素从小到大排好序了
 * B是长度为n、元素从小到大排好序了
 * 希望从A和B数组中，找出最大的k个数字
 * </p>
 */
public class FindKthNumFromDoubleSortedArr {

    public static int findKthNum1(int[] arr1, int[] arr2, int k) {
        if (k < 0 || k >= arr1.length + arr2.length) {
            return Integer.MIN_VALUE;
        }
        int[] newArr = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            newArr[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            newArr[arr1.length + i] = arr2[i];
        }
        Arrays.sort(newArr);
        return newArr[k - 1];
    }


    /**
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public static int findKthNum2(int[] arr1, int[] arr2, int k) {
        if (k < 0 || k >= arr1.length + arr2.length) {
            return Integer.MIN_VALUE;
        }
        int[] minArr, maxArr;

        if (arr1.length < arr2.length) {
            minArr = arr1;
            maxArr = arr2;
        } else {
            minArr = arr2;
            maxArr = arr1;
        }
        // 如果 k <= min
        // 在 arr1[0..k] arr2[0..k] 上找上中位数
        if (k <= minArr.length) {
            return upMiddle(minArr, 0, k - 1, maxArr, 0, k - 1);
        }
        // 如果 k > min
        // 在 minArr[0..min] maxArr[k-min..k]上寻找上中位数
        else if (k < maxArr.length) {
            if (maxArr[k - minArr.length - 1] > minArr[minArr.length - 1]) {
                return maxArr[k - minArr.length - 1];
            } else {
                return upMiddle(minArr, 0, minArr.length - 1, maxArr, k - minArr.length, k);
            }
        } else {
            if (maxArr[k - minArr.length - 1] > minArr[minArr.length - 1]) {
                return maxArr[k - minArr.length - 1];
            }
            return upMiddle(minArr, Math.max(0, k - maxArr.length - 1), minArr.length - 1, maxArr, k - minArr.length, maxArr.length - 1);
        }
    }

    private static int upMiddle(int[] minArr, int L1, int R1, int[] maxArr, int L2, int R2) {
        int index = R1 - L1 + 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < index; i++) {
            if (L2 > R2) {
                ans = minArr[L1++];
                continue;
            } else if (L1 > R1) {
                ans = maxArr[L2++];
                continue;
            }
            if (L1 <= R1 && minArr[L1] < maxArr[L2]) {
                ans = minArr[L1++];
            } else {
                ans = maxArr[L2++];
            }
        }
        return ans;
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }

    // for test
    public static void print(int[] arr1, int[] arr2, int k) {
        System.out.print("{");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("===>{");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + ",");
        }
        System.out.print("\b}");
        System.out.print(",k=" + k);
        System.out.print("\n==================\n");
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 10;
        int maxValue = 50;
        for (int i = 0; i < loops; i++) {
            int[] arr1 = generate(maxSize, maxValue);
            int[] arr2 = generate(maxSize, maxValue);
            int k = generate(arr1.length + arr2.length);
            if (findKthNum1(arr1, arr2, k) != findKthNum2(arr1, arr2, k)) {
                print(arr1, arr2, k);
            }
        }
        System.out.println("Nice");
    }

}
