package datastruct.manacher;

/**
 * 给定一个字符串，问对这个字符串进行增加字符
 * 最少需要增加几个字符使得字符串变成回文字符串
 */
@SuppressWarnings("all")
public class PalindromeStringNeedWord {

    /**
     * 从左往右，找到第一个回文字符串包含最右的位置，此位置左边为需要增加的字符数
     *
     * @param str
     */
    public void ans1(String str) {

    }


    /**
     * 拓展字符串，每个字符之间补上#
     * "12132" -> "#1#2#1#3#2#"
     *
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
}
