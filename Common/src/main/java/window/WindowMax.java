package window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr
 * 返回每一次滑出状况的最大值
 * 例如,arr = [4,3,5,4,3,3,6,7],W = 3
 * 返回 [5,5,5,4,6,7]
 */
public class WindowMax {


    public static int[] windowMax1(int[] arr, int W) {
        if (arr.length <= W) {
            Arrays.sort(arr);
            return new int[]{arr[0]};
        }
        int[] ans = new int[arr.length - W + 1];
        for (int i = 0; i < arr.length - W + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < W; j++) {
                max = Math.max(max, arr[i + j]);
            }
            ans[i] = max;
        }
        return ans;
    }

    /**
     * 需要重复练习
     * @param arr
     * @param W
     * @return
     */
    public static int[] windowMax2(int[] arr, int W) {
        if (arr.length <= W) {
            Arrays.sort(arr);
            return new int[]{arr[0]};
        }
        LinkedList<Integer> list = new LinkedList<>();
        int index = 0;
        int[] ans = new int[arr.length - W + 1];
        for (int R = 0; R < arr.length; R++) {
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[R]) {
                list.pollLast();
            }
            list.addLast(R);
            if (list.peekFirst() == R - W) {
                list.pollFirst();
            }
            if (R >= W - 1) {
                ans[index++] = arr[list.peekFirst()];
            }
        }
        return ans;
    }



    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize)+1;
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

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int maxValue = 30;
        int maxWindow = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, maxValue);
            int w = generate(maxWindow)+1;
            int[] ans1 = windowMax1(arr, w);
            int[] ans2 = windowMax2(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }


}
