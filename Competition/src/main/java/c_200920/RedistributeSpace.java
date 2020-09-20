package c_200920;

import java.util.ArrayList;
import java.util.List;

/**
 * 重新分配空格
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。
 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能最大化该数目。
 * 如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 * 返回 重新排列空格后的字符串 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：text = "  this   is  a sentence "
 * 输出："this   is   a   sentence"
 * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
 * 示例 2：
 * <p>
 * 输入：text = " practice   makes   perfect"
 * 输出："practice   makes   perfect "
 * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
 * 示例 3：
 * <p>
 * 输入：text = "hello   world"
 * 输出："hello   world"
 * 示例 4：
 * <p>
 * 输入：text = "  walks  udp package   into  bar a"
 * 输出："walks  udp  package  into  bar  a "
 * 示例 5：
 * <p>
 * 输入：text = "a"
 * 输出："a"
 */
@SuppressWarnings("all")
public class RedistributeSpace {

    public static String reorderSpaces(String text) {
        int spaceSize = 0;
        List<String> list = new ArrayList<>();
        char[] array = text.toCharArray();
        int L = 0;
        int R = 0;
        while (R < array.length) {
            L = R;
            if (array[R] == ' ') {
                spaceSize++;
                R++;
            } else {
                L = R;
                while (R < array.length && array[R] != ' ') {
                    R++;
                }
                list.add(getString(array, L, R));
            }
        }
        return getResult(spaceSize, list);
    }


    public static String getSpace(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private static String getResult(int spaceSize, List<String> list) {
        if (list.size() == 1) {
            return list.get(0) + getSpace(spaceSize);
        }
        int rest = spaceSize % (list.size() - 1);
        int space = spaceSize / (list.size() - 1);
        String ans = "";
        for (int i = 0; i < list.size() - 1; i++) {
            ans = ans + list.get(i) + getSpace(space);
        }
        ans = ans + list.get(list.size() - 1) + getSpace(rest);
        return ans;
    }

    public static String getString(char[] arr, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = begin; i < end; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorderSpaces("tes   "));
        System.out.println(reorderSpaces("this   is   a   sentence"));
    }
}
