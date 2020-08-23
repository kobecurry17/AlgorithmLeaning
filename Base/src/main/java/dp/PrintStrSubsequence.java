package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印字符串的子序列
 */
public class PrintStrSubsequence {


    /**
     * 字符串的所有子序列
     * 例如 'abc' -->'a','b','c','ab','ac','bc','abc'
     *
     * @param s
     */
    public static List<String> subsequence(String s) {
        List<String> res = new ArrayList<>();
        if (null != s && !"".equals(s)) {
            process(s, res, 0, "");
        }
        return res;
    }

    /**
     * 不重复的字符串子序列
     *
     * @param s
     * @return
     */
    public static List<String> subsequenceNoRepeat(String s) {
        List<String> res = new ArrayList<>();
        if (null != s && !"".equals(s)) {
            char[] arr = s.toCharArray();
            HashSet<String> set = new HashSet<>();
            process2(arr, res, 0, set, "");
        }
        return res;
    }

    private static void process2(char[] arr, List<String> res, int index, HashSet<String> set, String path) {
        if (index == arr.length) {
            return;
        }
        String no = path;
        process2(arr, res, index + 1, set, no);

        String yes = path + arr[index];
        if (!set.contains(yes)) {
            res.add(yes);
            set.add(yes);
        }
        process2(arr, res, index + 1, set, yes);
    }

    private static void process(String s, List<String> res, int index, String path) {
        if (index == s.length()) {
            return;
        }
        // 不选择当前字符
        process(s, res, index + 1, path);
        // 选择当前字符
        path = path + s.charAt(index);
        res.add(path);
        process(s, res, index + 1, path);
    }

    public static void main(String[] args) {
        List<String> abc = subsequence("abc");
        System.out.println(abc);
        List<String> aabc = subsequenceNoRepeat("aabc");
        System.out.println(aabc);
    }
}
