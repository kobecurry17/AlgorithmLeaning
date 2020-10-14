package class1.brackets;

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


    public static void main(String[] args) {
        String str1 = "(()()()()";
        String str2 = ")()()()";
        String str3 = "((()()()))";
        System.out.println(str1 + "===>" + effect(str1));
        System.out.println(str2 + "===>" + effect(str2));
        System.out.println(str3 + "===>" + effect(str3));
    }

}
