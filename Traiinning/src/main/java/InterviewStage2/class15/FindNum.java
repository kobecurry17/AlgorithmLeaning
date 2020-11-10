package InterviewStage2.class15;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * <p>
 * 给定一个数组arr
 * 已知除了一种数只出现一次之外，其他数都出现了k次，如何使用O(1)的空间找到这个数
 * int[] arr int K k>1
 * </p>
 */
@SuppressWarnings("all")
public class FindNum {


    /**
     * 暴力方法
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findOnceNum1(Integer[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) % k != 0) {
                return key;
            }
        }
        return Integer.MIN_VALUE;
    }


    /**
     * 找到arr中只出现一次的那个数
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findOnceNum2(Integer[] arr, int k) {
        int[] bit = new int[32];
        for (int i = 0; i < arr.length; i++) {
            getNumFromK(arr[i], bit, k);
        }
        int res = getNumAppareOnce(bit, k);
        return res;
    }

    /**
     * 根据arr生成的N进制数，对每位数字进行k取余,这样剩下的就一定是多余的那个数字
     * 【例如】
     * int[] arr = {1,1,1,3,3,3,2}
     * int[] bit ={2,3,3,0,0,0,0,0,0....0,0,0,0}
     * 对每一位进行取余后加上之前的累加和
     *
     * @param bit
     * @param k
     * @return
     */
    private static int getNumAppareOnce(int[] bit, int k) {
        int res = 0;
        for (int i = bit.length - 1; i >= 0; i--) {
            res = res * k + bit[i] % k;
        }
        return res;
    }

    private static void getNumFromK(int num, int[] bit, int k) {
        int i = 0;
        while (num > 0) {
            bit[i++] += num % k;
            num /= k;
        }
    }

    // for test
    public static Integer[] generate(int size, int maxValue) {
        Integer[] arr = new Integer[size * 3 + 1];
        for (int i = 0; i < size; i++) {
            arr[i * 3] = (int) (Math.random() * maxValue) + 1;
            arr[i * 3 + 1] = arr[i * 3];
            arr[i * 3 + 2] = arr[i * 3];
        }
        arr[arr.length - 1] = (int) (Math.random() * maxValue) + 1;
        Arrays.sort(arr, new MyComparator());
        return arr;
    }

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return ((int) Math.random() * 3) - 1;
        }
    }

    // for test
    public static void print(Integer[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("\n==================\n");
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxValue = 50;
        int maxSize = 10;
        int k = 3;
        for (int i = 0; i < loops; i++) {
            Integer[] arr = generate(maxSize, maxValue);
            int i1 = findOnceNum1(arr, k);
            int i2 = findOnceNum2(arr, k);
            if (i1 != i2) {
                print(arr);
            }
        }
        System.out.println("Nice");
    }


}
