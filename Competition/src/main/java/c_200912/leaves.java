package c_200912;

import java.util.Arrays;

/**
 * TODO:暂时没有理解答案，日后再说
 * 给定一个数组有r和y组成且数组长度大于3，问：经过最少多少次调整，能让数组左侧和右侧都是r中间是y？
 * <p>
 * 示例 1：
 * 输入：leaves = "rrryyyrryyyrr"
 * 输出：2
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 * <p>
 * 示例 2：
 * 输入：leaves = "ryr"
 * 输出：0
 * 解释：已符合要求，不需要额外操作
 * <p>
 * 提示：
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 */
@SuppressWarnings("all")
public class leaves {

    public static int ans1(String leaves) {
        int n = leaves.length();
        char[] array = leaves.toCharArray();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++)
            sum[i + 1] = sum[i] + (array[i] == 'r' ? 1 : 0);
        int[] min = new int[n + 1];
        int currentMin = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            currentMin = Math.min(currentMin, i - 2 * sum[i]);
            min[i] = currentMin;
        }
        int result = Integer.MAX_VALUE;
        for (int j = n - 1; j > 1; j--)
            result = Math.min(result, n - sum[n] + min[j - 1] - j + 2 * sum[j]);
        return result;
    }

    public static int ans2(String leaves) {
        return 0;
    }


    public static int cost(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int minimumOperations(String leaves) {
        int[] last = new int[4];
        int[] next = new int[4];
        int inf = (int) 1e8;
        Arrays.fill(last, inf);
        last[0] = 0;
        for (char c : leaves.toCharArray()) {
            Arrays.fill(next, inf);
            next[1] = Math.min(last[1] + cost('r', c), last[0] + cost('r', c));
            next[2] = Math.min(last[2] + cost('y', c), last[1] + cost('y', c));
            next[3] = Math.min(last[3] + cost('r', c), last[2] + cost('r', c));
            int[] tmp = last;
            last = next;
            next = tmp;
        }
        return last[3];
    }


    // for test
    public static String generate(int size) {
        int s = (int) (Math.random() * size) + 3;
        char[] chars = new char[s];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Math.random() > 0.5 ? 'y' : 'r';
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int max = 5;
        for (int i = 0; i < loops; i++) {
            String s = generate(max);
            int i1 = minimumOperations(s);
            int i2 = ans1(s);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }

    }


}
