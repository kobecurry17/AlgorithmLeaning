package class1.brackets;

/**
 * 如果一个字符串无效，返回至少填几个字符串能让其整体有效
 */
public class LeastWordMakeBracketsEffective {


    public static int leastWork(String str) {
        char[] arr = str.toCharArray();
        int count = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                count++;
            } else if (arr[i] == ')') {
                count--;
                if (count < 0) {
                    count = 0;
                    ans++;
                }
            }
        }
        return ans + count;
    }


    public static void main(String[] args) {
        String str1 = "(()())))";
        String str2 = "))(()())))";
        String str3 = "(()))()())";
        String str4 = "()()())()())))";
        System.out.println("str1=" + str1 + ",====>" + leastWork(str1));
        System.out.println("str2=" + str2 + ",====>" + leastWork(str2));
        System.out.println("str3=" + str3 + ",====>" + leastWork(str3));
        System.out.println("str4=" + str4 + ",====>" + leastWork(str4));
    }

}
