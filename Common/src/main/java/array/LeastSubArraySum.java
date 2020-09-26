package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 给定一个数组arr，有正数有负数有0，问：累加和为sum的最短子数组多长
 * 优化思路：
 *  根据题意，这题用前缀和数组进行预处理，但时间复杂度认为O(n2),不是太理想
 *  因为累加和的复杂度已经是O(N) 所以这一步不可能优化，那么可能的优化就在于如何找到长度最小的累加和为sum的子数组
 *  将找到的步骤进行优化就可以提高效率，降低时间复杂度
 */
@SuppressWarnings("all")
public class LeastSubArraySum {


    /**
     * 给定一个数组arr，有正数有负数有0，问：累加和为sum的最短子数组多长
     * O(N2)
     *
     * @param arr
     * @param sum
     * @return
     */
    public static int leastSubArraySum1(int[] arr, int sum) {
        int help[] = new int[arr.length];
        help[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i] = arr[i] + help[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (help[i] == sum) {
                min = Math.min(i + 1, min);
            }
            for (int j = 1; j < i + 1; j++) {
                if (help[i] - help[i - j] == sum) {
                    min = Math.min(j, min);
                    break;
                }
            }
        }
        return min;
    }

    /**
     * 给定一个数组arr，有正数有负数有0，问：累加和为sum的最短子数组多长
     * O(N2)
     *
     * @param arr
     * @param sum
     * @return
     */
    public static int leastSubArraySum2(int[] arr, int sum) {
        int help[] = new int[arr.length];
        help[0] = arr[0];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<Integer>() {{
            add(-1);
        }});
        help[0] = arr[0];
        add(map,help[0],0);
        for (int i = 1; i < arr.length; i++) {
            help[i] = arr[i] + help[i - 1];
            add(map, help[i], i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int rest = help[i] - sum;
            List<Integer> value = map.get(rest);
            if (null != value) {
                for (int j = value.size() - 1; j >= 0; j--) {
                    if (value.get(j) < i) {
                        min = Math.min(min, i - value.get(j));
                        break;
                    }
                }
            }
        }
        return min;
    }

    private static void add(HashMap<Integer, List<Integer>> map, int key, int value) {
        List<Integer> list = map.get(key);
        if (null == list) {
            map.put(key, new ArrayList<Integer>() {{
                add(value);
            }});
        } else {
            list.add(value);
        }
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for testing
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 10;
        int maxSize = 10;
        int maxSum = 30;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
            int sum = generate(maxSum);
//            int[] arr = {8, 8, 1, 7, 6, 5, 3, 7, 7, 6};
//            int sum = 16;
            int i1 = leastSubArraySum1(arr, sum);
            int i2 = leastSubArraySum2(arr, sum);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");


    }

}
