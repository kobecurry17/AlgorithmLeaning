package class9;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <p>
 * 给定一个正整数数组arr,其中所有的值都为整数，一下是最小不可组成和的概念
 * 把arr每个子集内所有的元素加起来会出现很多值，最小的记为min,最大的记为max
 * 在区间[min,max]上，如果有数不可以被arr某个子集相加得到，那么其中最小的数是arr的最小不可组成和
 * 在区间[min,max],如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最小不可组成和
 * 请写函数返回正整数数组arr的最小不可组成和
 * </p>
 * <p>
 * 【举例】
 * <p>
 * arr = [ 3, 2, 5 ].子集{2}=>min ,子集{ 3, 2, 5 } =>max
 * 在区间 [ 2 , 10 ]上，4、6、9不能被任何子序列累加和得到，其中 4 是arr的最小不可组成和
 * <p>
 * arr = [ 1, 2, 4 ].子集{1}=>min ,子集{ 1, 2, 4 } =>max
 * 在区间 [ 1 , 7 ]上，所有数都可以由arr的子序列累加和得到，因此返回 8
 * </p>
 */
@SuppressWarnings("all")
public class CannotComposeSum {


    /**
     * 最小的不可达数
     * 暴力方法
     *
     * @param arr
     * @return
     */
    public static int minCanNOTComposeSum1(int[] arr) {
        int[] prefixSum = new int[arr.length];
        int min = arr[0];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            min = Math.min(arr[i], min);
        }
        int max = prefixSum[prefixSum.length - 1];
        HashSet<Integer> set = new HashSet();
        process(set, arr, 0, 0);
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return max + 1;
    }

    private static void process(HashSet<Integer> set, int[] arr, int cur, int now) {
        if (cur == arr.length) {
            set.add(now);
            return;
        }
        process(set, arr, cur + 1, now);
        process(set, arr, cur + 1, now + arr[cur]);
    }

    /**
     * 子数组能否达成num
     *
     * @param arr
     * @param num
     * @return
     */
    private static boolean exist(int[] arr, int num) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == num) {
                return true;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] == num) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int minCanNOTComposeSum2(int[] arr) {
        int N = arr.length;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max += arr[i];
        }
        int sum = max - min;
        // dp[i][j] 在 arr[0..i]上能不能 得到 min+j
        boolean[][] dp = new boolean[N][sum + 1];
        dp[0][0] = arr[0] == min;
        for (int i = 1; i < N; i++) {
            dp[i][0] = arr[i] == min ? true : dp[i - 1][0];
        }
        // arr[0..i]上不能凑出 min...max
        for (int i = 0; i <= sum; i++) {
            dp[0][i] = arr[0] == min + i ? true : false;
        }


        // 普遍位置
        // dp[i][j] =(arr[i]==j?true:false)||(dp[i-1][j-arr[i]])||dp[i-1][j]
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = (arr[i] == min + j ? true : false) ||
                        (j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : false) ||
                        (dp[i - 1][j]);
            }
        }
        for (int i = 1; i <= sum; i++) {
            if (!dp[N - 1][i]) {
                return i + min;
            }
        }
        return max + 1;
    }

    /**
     * 如果一定有1
     *
     * @param arr
     * @return
     */
    public static int minCanNOTComposeSum3(int[] arr) {
        // 排序
        // 组织我当前的左右边界
        // 根据下一个数和当前边界的情况得到最终结果

        Arrays.sort(arr);
        int right = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > right + 1) {
                return right + 1;
            }
            right = right + arr[i];
        }
        return right + 1;
    }


    public static int[] prefixSum(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return sum;
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
        int loops = 50_0000;
        int maxLength = 10;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
//            int[] arr = {1, 6, 4};
            int[] arr = generate(maxLength, maxValue);

            boolean flag = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 1) {
                    flag = true;
                    break;
                }
            }
//            if (minCanNOTComposeSum1(arr) != minCanNOTComposeSum2(arr)) {
//                print(arr);
//            }
            if (flag) {
                if (minCanNOTComposeSum2(arr) != minCanNOTComposeSum3(arr)) {
                    print(arr);
                }
            }
        }
        System.out.println("Nice");
    }


}
