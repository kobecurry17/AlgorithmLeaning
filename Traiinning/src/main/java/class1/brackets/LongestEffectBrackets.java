package class1.brackets;

/**
 * <p>
 * 括号有效配对是指：
 * 1)任何一个左括号都能找到和其正确配对的右括号
 * 2)任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串长度
 * </p>
 */
@SuppressWarnings("all")
public class LongestEffectBrackets {


    public static int longestEffectBrackets(String str) {
        char[] arr = str.toCharArray();
        int[] help = new int[str.length()];
        int max = -1;
        for (int i = 1; i < str.length(); i++) {
            int last = i - help[i - 1] - 1;
            if (last >= 0 && arr[i] == ')' && arr[last] == '(') {
                if(last == i - 1){
                    help[i] = 2 +(last > 0 ? help[last - 1] : 0);
                }else{
                    help[i] = 2 + help[i-1] + (last > 0 ? help[last - 1] : 0);
                }
                max = Math.max(help[i], max);
            }
        }
        return max;
    }


    /**
     * 正确答案
     *
     * @param str
     * @return
     */
    public static int right(String str) {
        int max = -1;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 2; j <= str.length(); j++) {
                if (effect(str.substring(i, j))) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    // for test
    public static String generate(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(Math.random() < 0.5 ? '(' : ')');
        }
        return sb.toString();
    }


    public static boolean effect(String str) {
        char[] array = str.toCharArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                count++;
            } else if (array[i] == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 30;
        for (int i = 0; i < loops; i++) {
            String str = generate(maxSize);
            if (right(str) != longestEffectBrackets(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }

}
