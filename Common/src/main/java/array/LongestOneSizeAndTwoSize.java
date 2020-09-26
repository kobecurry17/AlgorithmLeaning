package array;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，有正数负数和0，问：1和2一样多的最长子数组有多长
 */
public class LongestOneSizeAndTwoSize {


    public static int longestOneSizeAndTwoSize(int[] arr) {
        int[] copy = copyArr(arr);
        int[] help = new int[arr.length];
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        help[0] = copy[0];
        add(map, help[0], 0);
        add(map, 0, -1);
        for (int i = 1; i < arr.length; i++) {
            help[i] = help[i - 1] + copy[i];
            add(map, help[i], i);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < copy.length; i++) {
            PriorityQueue<Integer> heap = map.get(help[i]);
            if (heap.peek() < i) {
                max = Math.max(max, i - heap.peek());
            }
        }
        return max;
    }

    private static int[] copyArr(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                res[i] = 1;
            } else {
                res[i] = -1;
            }
        }
        return res;
    }

    private static void add(HashMap<Integer, PriorityQueue<Integer>> map, int key, int value) {
        PriorityQueue<Integer> heap = map.get(key);
        if (null == heap) {
            map.put(key, new PriorityQueue<Integer>() {{
                add(value);
            }});
        } else {
            heap.add(value);
        }
    }

    /**
     * 暴力解
     *
     * @param arr
     * @return
     */
    public static int right(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private static boolean valid(int[] arr, int i, int j) {
        int one = 0;
        int two = 0;
        for (int k = i; k <= j; k++) {
            if (arr[k] == 1) {
                one++;
            } else {
                two++;
            }
        }
        return one == two;
    }


    // for test
    public static int[] generate(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 2) + 1;
        }
        return arr;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 20;
        for (int i = 0; i < loops; i++) {
            int[] values = generate(maxSize);
            int i1 = right(values);
            int i2 = longestOneSizeAndTwoSize(values);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }

}
