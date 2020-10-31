package class9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个正整数数组
 * 返回能否将这个数组分成4个相等的部分，切分点的值不看
 */
public class SplitFourPart {


    /**
     * 时间复杂度O(N^2)
     *
     * @param arr
     * @return
     */
    public static boolean isSplitFourPart1(int arr[]) {
        if (arr.length < 7) {
            return false;
        }
        // 求出arr的累加和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        HashMap<Integer, ArrayList<Integer>> map = buildMap(sum);
        for (int i = 0; i < arr.length; i++) {
            int[] sec = findNext(sum, sum[i], i + 1, 2, map);
            for (int j = 0; j < sec.length; j++) {
                int[] third = findNext(sum, sum[i], sec[j] + 1, 3, map);
                for (int k = 0; k < third.length; k++) {
                    int[] fourth = findNext(sum, sum[i], third[k] + 1, 4, map);
                    for (int l = 0; l < fourth.length; l++) {
                        if (fourth[l] == sum.length - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static HashMap<Integer, ArrayList<Integer>> buildMap(int[] sum) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            ArrayList<Integer> list = map.getOrDefault(sum[i], new ArrayList<>());
            list.add(i);
            map.put(sum[i], list);
        }
        return map;
    }

    /**
     * @param sum    前缀和数组
     * @param target 目标值
     * @param from   起点
     * @param part   第几部分
     * @param map    缓存
     * @return
     */
    private static int[] findNext(int[] sum, int target, int from, int part, HashMap<Integer, ArrayList<Integer>> map) {
        if (from >= sum.length) {
            return new int[0];
        }
        int key = target + sum[from];
        if (map.containsKey(key)) {
            ArrayList<Integer> list = map.get(key);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > from) {
                    int[] res = new int[list.size() - i];
                    for (int j = 0; j < res.length; j++) {
                        res[j] = list.get(i + j);
                    }
                    return res;
                }
            }
        }
        return new int[0];
    }

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lsum = arr[0];
        int r = arr.length - 2;
        int rsum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lsum == rsum) {
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
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


        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, 30);
            if (isSplitFourPart1(arr)!=canSplits1(arr)) {
                print(arr);
            }
        }
    }
}
