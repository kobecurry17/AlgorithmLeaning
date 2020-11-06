package InterviewStage2.class12;

import java.util.*;

/**
 * <p>
 * 可整合数组的最大长度
 * </p>
 * <p>
 * 先给出可整合数组的定义:如果一个数组在排序之后，每相邻的两个数查的绝对值都为1，
 * 则，改数组为可整合数组。例如，[5,3,4,6,2]排序后为[2,3,4,5,6]
 * 符合每相邻两个数查的绝对值都为1,
 * 所以这个数组为可整合数组。给定一个整型数组arr，请返回其中最大可整合子数组长度。
 * 例如,[5,5,3,2,6,4,3]
 * 的最大可整合数组为[5,3,2,6,4],所以返回5
 */
public class IntegrationArray {

    /**
     * 时间复杂度
     * O(n ^ 3 * LogN)
     *
     * @param arr
     * @return
     */
    public static int maxIntegrationArray1(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] help = new int[j - i];
                for (int k = 0; k < j - i; k++) {
                    help[k] = arr[i];
                }
                Arrays.sort(help);
                boolean flag = true;
                for (int k = 1; k < help.length; k++) {
                    if (help[k] - help[k - 1] != 1) {
                        flag = false;
                    }
                }
                if (flag) {
                    max = Math.max(max, help.length);
                }
            }
        }
        return max;
    }

    /**
     * 时间复杂度
     * O( N ^ 2 * LogN)
     *
     * @param arr
     * @return
     */
    public static int maxIntegrationArray2(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int[][][] dp = getDP(arr);
        int[][] minArr = dp[0];
        int[][] maxArr = dp[1];
        int max = 1;
        // 生成两张dp表
        // dp minArr  dp[i][j]=> arr[i..j]上最小值
        // dp maxArr  dp[i][j]=> arr[i..j]上最大值

        // 枚举每个位置开始的情况
        // 根据 maxArr[i][j]-minArr[i][j] == j-1 且 没有重复数字 可以推出最大的数组长度

        boolean[][] dp2 = new boolean[arr.length][arr.length];
        dp2[0][0] = true;
        for (int i = 1; i < arr.length; i++) {
            dp2[i - 1][i] = (maxArr[i - 1][i] - minArr[i - 1][i] == 1);
            dp2[i][i] = true;
        }

        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);
                if (maxArr[i][j] - minArr[i][j] == j - i) {
                    max = Math.max(max, j - i + 1);
                }
            }
            set.clear();
        }
        // 返回最终结果
        return max;
    }

    private static int[][][] getDP(int[] arr) {
        int[][][] dp = new int[2][arr.length][arr.length];
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        for (int i = 0; i < arr.length; i++) {
            minHeap = new PriorityQueue(new IntegerComparator(true));
            maxHeap = new PriorityQueue(new IntegerComparator(false));
            for (int j = i; j < arr.length; j++) {
                minHeap.add(arr[j]);
                maxHeap.add(arr[j]);
                dp[0][i][j] = minHeap.peek();
                dp[1][i][j] = maxHeap.peek();
            }
        }
        return dp;
    }


    public static class IntegerComparator implements Comparator<Integer> {

        boolean esc = false;

        public IntegerComparator(boolean desc) {
            this.esc = desc;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (esc) {
                return o1.compareTo(o2);
            } else {
                return o2.compareTo(o1);
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


    /**
     * 时间复杂度O(N^2)
     *
     * @return
     */
    public static int maxIntegrationArray3(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int ans = 1;

        HashSet<Integer> set = new HashSet<>();
        for (int L = 0; L < arr.length - 1; L++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int R = L; R < arr.length; R++) {
                if (set.contains(arr[R])) {
                    set.remove(arr[L]);
                    break;
                }
                set.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min == set.size() - 1) {
                    ans = Math.max(ans, set.size());
                }
            }
            set.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 10;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
            if (maxIntegrationArray2(arr) != maxIntegrationArray3(arr)) {
                print(arr);
            }
        }
        System.out.println("Nice");
    }

}
