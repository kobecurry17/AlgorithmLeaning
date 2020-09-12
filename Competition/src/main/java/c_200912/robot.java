package c_200912;

/**
 * "A" 运算：使 x = 2 * x + y；
 * "B" 运算：使 y = 2 * y + x;
 *
 * 输入：s = "AB"
 *
 * 输出：4
 *
 * 解释：
 * 经过一次 A 运算后，x = 2, y = 0。
 * 再经过一次 B 运算，x = 2, y = 2。
 * 最终 x 与 y 之和为 4。
 */
public class robot {

    public static int ans(String s) {
        if (null == s || s.trim().equals("")) {
            return 1;
        }
        int x = 1;
        int y = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'A') {
                x = (x << 1) + y;
            } else {
                y = (y << 1) + x;
            }
        }
        return x + y;
    }


    // for test
    public static String generate(int size) {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += Math.random() < 0.5 ? "A" : "B";
        }
        return s;
    }

    public static void main(String[] args) {
        String generate = generate(0);
        int ans = ans(generate);
        System.out.println(generate);
        System.out.println(ans);
    }

}
