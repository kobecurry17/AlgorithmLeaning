package class9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 给定一个正整数数组和一个range
 * 如果可以自由选择arr中的数字，想累加得到1~range范围上的所有书，返回ar最少还缺几个数
 * </p>
 * 【举例】
 * arr = { 1 , 2 , 3 , 7 } range = 15
 * 想累加得到1~15范围上的所有数，arr还缺14这个数，所以返回1
 * arr = { 1 , 5 , 7} range = 15
 * 想累加得到1~15范围上的所有数,arr 还缺2,4 所以返回2
 */
public class MergeRange {
    // 最少需要补几个数?
    public static int needMerge(int[] arr, int range) {
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        int r = 1;
        if (arr[0] > 1) {
            list.add(1);
        }
        for (int i = 1; r + (2 << i) - 1 <= arr[0]; i++) {
            list.add((2 << i) - 1);
            r += (2 << i) - 1;
        }
        int index = 0;
        while (r < range) {
            if (index < arr.length && arr[index] <= r) {
                r += arr[index++];
            } else {
                list.add(r + 1);
                r = (r << 1) | 1;
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 7};
//        System.out.println(needMerge(arr, 14));
        int[] arr = new int[]{1, 5, 7};
        System.out.println(needMerge(arr, 39));
    }

}
