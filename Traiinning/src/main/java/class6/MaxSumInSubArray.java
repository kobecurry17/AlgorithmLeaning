package class6;

/**
 * 给定一个数组arr，返回子数组的最大累加和
 */
@SuppressWarnings("all")
public class MaxSumInSubArray {

    /**
     * 子数组的最大累加和
     *
     * @param arr
     * @return
     */
    public static int maxSumInSubArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }


    /**
     * 求一个数组上最大的累加和
     *
     * @param help
     * @return
     */
    private static int maxSum(int[] help) {
        int min = 0;
        int maxNum = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < help.length; i++) {
            sum += help[i];
            min = Math.min(min, sum);
            max = Math.max(max, sum - min);
            maxNum = Math.max(maxNum, help[i]);
        }
        max = Math.max(max, sum - min);
        if (max <= 0) {
            return maxNum;
        }
        return max;
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = -100 + (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int maxValue = 300;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, maxValue);
            if (maxSum(arr) != maxSumInSubArray(arr)) {
                System.out.println("Oops!");
            }
        }
    }
}
