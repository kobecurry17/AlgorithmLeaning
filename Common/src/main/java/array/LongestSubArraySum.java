package array;

public class LongestSubArraySum {

    /**
     * 给定一个正数的数组arr,和sum，问:累加和为sum的最长子数组的长度是什么
     *
     * @param arr
     * @return
     */
    public static int longestSumSubArray(int[] arr, int sum) {
        int L = 0;
        int R = 0;
        int count = 0;
        int max = 0;
        while (R < arr.length) {
            if (count == sum) {
                max = Math.max(max, R - L);
                count -= arr[L++];
                R++;
            } else if (count < sum) {
                count += arr[R++];
            } else {
                count -= arr[L++];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3};
        System.out.println(longestSumSubArray(arr, 5));
    }
}
