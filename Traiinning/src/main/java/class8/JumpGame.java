package class8;

/**
 * 给定一个正整数数组，你从第 0 个数向最后一个数
 * 每个数的值表示你从这个位置可以向右跳跃的最大长度
 * 计算如何以最少的跳跃次数跳到最后一个数
 */
public class JumpGame {

    /**
     * 需要的跳跃次数
     *
     * @param arr
     * @return
     */
    public static int jumpTimes1(int[] arr) {
        return process(arr, 0);
    }

    /**
     * 时间复杂度
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int jumpTimes2(int[] arr) {
        int[] dp = new int[arr.length];
        dp[dp.length - 1] = 1;
        for (int i = dp.length - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= arr[i]; j++) {
                if (i + j >= dp.length) {
                    min = Math.min(min, 1);
                } else {
                    min = Math.min(min, 1 + dp[i + j]);
                }
            }
            dp[i] = min;
        }
        return dp[0];
    }

    public static int jumpTimes3(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int next = arr[0];
        int pre = 0;
        int step = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i > pre) {
                pre = next;
                step++;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }


    private static int process(int[] arr, int i) {
        // 到了最后一个数
        if (i >= arr.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 1; j <= arr[i]; j++) {
            ans = Math.min(1 + process(arr, i + j), ans);
        }
        return ans;
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }


    public static void main(String[] args) {


        int loops = 50_0000;
        int maxLength = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, 10);
            if (jumpTimes2(arr) != jumpTimes3(arr)) {
                System.out.println("Oops!");
                jumpTimes2(arr);
                jumpTimes3(arr);
            }
        }
        System.out.println("Nice!");
    }

}
