package dp;

import java.util.*;

/**
 * 字符串的全排列
 */
public class PrintAllArrangement {

    /**
     * 字符串的全排列
     *
     * @param s
     * @return
     */
    public static List<String> allArrangement(String s) {
        List<String> res = new ArrayList<>();
        if (null != s && !s.trim().equals("")) {
            char[] array = s.toCharArray();
            process(array, 0, res, "");
        }
        return res;
    }


    /**
     * 字符串的全排列，剔除字面值相同的字符串
     *
     * @param s
     * @return
     */
    public static List<String> allArrangementWithNoRepeat(String s) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        if (null != s && !s.trim().equals("")) {
            char[] array = s.toCharArray();
            process2(array, 0, res, set, "");
        }
        return res;
    }

    private static void process2(char[] array, int index, List<String> list, Set<String> set, String path) {
        for (int i = 0; i < array.length; i++) {
            char[] arr = copyExcept(array, i);
            String no = path;
            process2(arr, index + 1,list, set, no);

            String yes = path + array[i];
            if (!set.contains(yes)) {
                list.add(yes);
                set.add(yes);
            }
            process2(arr, index + 1, list, set, yes);
        }
    }

    /**
     * 拷贝一个字符数组，剔除指定位置
     *
     * @param array
     * @param index
     * @return
     */
    public static char[] copyExcept(char[] array, int index) {
        if (index > array.length) {
            throw new RuntimeException("不能越界哦");
        }
        char[] res = new char[array.length - 1];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                res[k++] = array[i];
            }
        }
        return res;
    }


    private static void process(char[] array, int index, List<String> list, String path) {
        for (int i = 0; i < array.length; i++) {
            char[] arr = copyExcept(array, i);
            String no = path;
            process(arr, index + 1, list, no);

            String yes = path + array[i];
            list.add(yes);
            process(arr, index + 1, list, yes);
        }
    }

    public static void main(String[] args) {
//        List<String> str = allArrangement("abdse");
//        System.out.println(str);
        List<String> str2 = allArrangementWithNoRepeat("abc");
        System.out.println(str2);
    }
}
