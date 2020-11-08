package InterviewStage2.class14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无需数组arr,返回如果排序之后，相邻数之间的最多大差值
 * {3,1,7,9},如果排序后{1,3,7,9}
 * 相邻数之间的最大差值来自3,7 返回4
 */
@SuppressWarnings("all")
public class ____MaxSortedDiff {


    public static int maxDiff1(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int[] arr2 = copyArr(arr);
        Arrays.sort(arr2);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arr2.length; i++) {
            max = Math.max(arr2[i] - arr2[i - 1], max);
        }
        return max;
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

    /**
     * 对arr进行预处理
     * 得到最大值和最小值
     * 将数组分成arr.length+1份
     * 那么相邻数差值最大的一定是相邻不空桶顶部与底部的差值
     *
     * @param arr
     * @return
     */
    public static int maxDiff2(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        if (arr.length == 2) {
            return Math.abs(arr[0] - arr[1]);
        }


        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;


        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        int bucketSize = (int) Math.ceil((max - min) / ((1.0) * arr.length));
        if (bucketSize == 0) {
            return 0;
        }
        // 把所有数分为 x+1份
        // x = max-min / size
        // 那么最终的结果一定是某桶的底到相邻桶顶

        // help [i][j]  help[i][0] 表示在第i号桶里的最小值
        // help [i][j]  help[i][1] 表示在第i号桶里的最大值
        List<List<Integer>> help = new ArrayList<>(arr.length + 1);
        for (int i = 0; i < arr.length + 1; i++) {
            help.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / bucketSize;
            List<Integer> list = help.get(index);
            if (list.isEmpty()) {
                list.add(arr[i]);
                list.add(arr[i]);
            } else {
                list.set(0, Math.min(list.get(0), arr[i]));
                list.set(1, Math.max(list.get(1), arr[i]));
            }
        }
        int index = 0;
        Integer last = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        while (index < help.size()) {
            List<Integer> list = help.get(index++);
            if (list.isEmpty()) {
                continue;
            } else {
                if (last == Integer.MIN_VALUE) {
                    last = list.get(1);
                } else {
                    ans = Math.max(list.get(0) - last, ans);
                    last = list.get(1);
                }
            }
        }
        return ans;
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

    private static int[] copyArr(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 5;
        int maxValue = 15;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, 10);
            if (maxDiff1(arr) != maxDiff2(arr)) {
                print(arr);
                maxDiff2(arr);
                maxDiff1(arr);
            }
        }
        System.out.println("Nice");
    }
}
