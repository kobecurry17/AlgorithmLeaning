package datastruct.manacher;

/**
 * Manacher
 * 判断字符串中最长回文字符串的长度
 */
@SuppressWarnings("all")
public class LongestPalindromeString {


    /**
     * 字符串的最长回文字符串的长度
     *
     * @param str
     * @return
     */
    public static int longestPalindromeString1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] arr = manacherString(str);
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < arr.length && arr[L] == arr[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    /**
     * 拓展字符串，每个字符之间补上#
     *"12132" -> "#1#2#1#3#2#"
     * @param str
     * @return
     */
    private static char[] manacherString(String str) {
        char[] array = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : array[index++];
        }
        return res;
    }

    private static int manacher(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] arr = manacherString(str);

    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int possibilities = 5;
        for (int i = 0; i < loops; i++) {
            String randomString = getRandomString(possibilities, maxLength);
            if (right(randomString) != longestPalindromeString1(randomString)) {
                System.out.println("Oops!");
            }
        }
    }
}
