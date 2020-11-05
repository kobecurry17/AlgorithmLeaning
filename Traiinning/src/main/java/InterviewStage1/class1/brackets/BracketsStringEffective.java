package InterviewStage1.class1.brackets;

import java.util.Stack;

/**
 * 判断一个括号字符串有效
 */
public class BracketsStringEffective {


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


    /**
     * 判断一个包含小中大括号的字符串是否有效
     *
     * @param str
     * @return
     */
    public static boolean effect2(String str) {
        char[] arr = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') stack.push(')');
            else if (arr[i] == '[') stack.push(']');
            else if (arr[i] == '{') stack.push('}');
            else {
                Character pop = stack.pop();
                if (pop != arr[i]) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str1 = "(()()()()";
        String str2 = ")()()()";
        String str3 = "((()()()))";
        System.out.println(str1 + "===>" + effect(str1));
        System.out.println(str2 + "===>" + effect(str2));
        System.out.println(str3 + "===>" + effect(str3));

        String str4 = "[][()]{{}}";
        String str5 = "[{][}()]{{}}";
        String str6 = "[][()]{(){[()]}}";
        System.out.println(str4 + "===>" + effect2(str4));
        System.out.println(str5 + "===>" + effect2(str5));
        System.out.println(str6 + "===>" + effect2(str6));


    }

}
