package class6;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符串可以成为s1的子串?
 * 比如 s1 = "abcde" s2 = "axbc"
 * 返回1。 s2删掉x就变成了s1的子串
 */
@SuppressWarnings("all")
public class DeleteCharacter {


    /**
     * 思路1:
     * 求出 str2 所有子序列
     * 在str1中使用KMP算法
     * 时间复杂度 O(M2 * N)
     */
    public static int way1(String str1, String str2) {
        int ans = 0;
        List<String> subSequence = subSequence(str2);
        subSequence.sort((String s1, String s2) -> {
            return ((Integer) s2.length()).compareTo(s1.length());
        });
        for (int i = 0; i < subSequence.size(); i++) {
            if (KMP(str1.toCharArray(), subSequence.get(i).toCharArray()) != -1) {
                return str2.length() - subSequence.get(i).length();
            }
        }
        return -1;
    }

    /**
     * KMP 算法
     * 在str1上寻找str2最早出现的位置
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int KMP(char[] str1, char[] str2) {
        if (str2 == null || str2.length == 0) {
            return 0;
        }
        if (str2.length > str1.length) {
            return -1;
        }
        if (str1.length == str2.length) {
            return new String(str1).equals(new String(str2)) ? 0 : -1;
        }
        int[] help = help(str2);
        int cur = 0;
        int index = 0;
        while (index < str2.length && cur < str1.length) {
            if (str1[cur] == str2[index]) {
                cur++;
                index++;
            } else if (help[index] == 0) {
                cur++;
            } else {
                index = help[index];
            }
        }
        return index == str2.length ? cur - str2.length : -1;
    }

    private static int[] help(char[] str) {
        int[] help = new int[str.length];
        help[0] = 0;
        help[1] = 1;
        int pre = 0;
        for (int i = 2; i < help.length; i++) {
            if (str[i - 1] == str[pre]) {
                help[i] = help[i - 1] + 1;
            } else {
                pre = 0;
            }
        }
        return help;
    }

    private static List<String> subSequence(String str2) {
        List<String> ans = new ArrayList<>();
        process(str2.toCharArray(), "", ans, 0);
        return ans;
    }

    private static void process(char[] str2, String path, List<String> ans, int index) {
        if (index == str2.length) {
            ans.add(path);
            return;
        }
        process(str2, path, ans, index + 1);
        process(str2, path + str2[index], ans, index + 1);
    }

    /**
     * 思路2:
     * 求出str1的所有子串，
     */

    public static void main(String[] args) {
        String s = "abcdes";
        System.out.println(way1("aab", "asdsadeb"));

    }
}
