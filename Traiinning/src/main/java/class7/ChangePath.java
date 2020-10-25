package class7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 给定两个字符串，几位start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串
 * 所有的字符串都是小写字母
 * 规定:start每次只能改变一个字符，最终的目标是彻底变成to,但是每次变成的新字符串必须在list中存在
 * 请返回所有最短的变换路径
 * </p>
 * 【举例】
 * start = "abc" to = "cab" list ={"cab", "acc", "cbc", "ccc", "cac", "aab", "abb"}
 * abc -> abb -> aab -> cab
 * abc -> abb -> cbb -> cab
 * abc -> cbc -> cac -> cab
 * abc -> cbc -> cbb -> cab
 */
public class ChangePath {

    public static List<LinkedList<String>> minChange(String from, String to, List<String> list) {
        List<LinkedList<String>> ans = new ArrayList<>();
        boolean[] help = new boolean[list.size()];
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(from);
        process(from, ans, to, help, linkedList, list);
        return ans;
    }

    private static void process(String from, List<LinkedList<String>> ans, String to, boolean[] help, LinkedList<String> linkedList, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!help[i]) {
                if (oneStep(list.get(i), from)) {
                    if (to.equals(list.get(i))) {
                        ans.add(new LinkedList<>(linkedList));
                        return;
                    }
                    help[i] = true;
                    linkedList.add(from);
                    process(list.get(i), ans, to, help, linkedList, list);
                    linkedList.removeLast();
                    help[i] = false;
                }
            }
        }
    }

    public static boolean oneStep(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int diff = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (diff > 1) {
                return false;
            }
            if (arr1[i] != arr2[i]) {
                diff++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("cab", "acc", "cbc", "ccc", "cac", "aab", "abb");
        String from = "abc";
        String to = "cab";
        System.out.println(minChange(from, to, list));
    }
}
