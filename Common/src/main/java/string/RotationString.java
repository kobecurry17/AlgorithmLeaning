package string;

/**
 * 设定：字符串"123" 有旋转词“123” “231” “312“
 * 问给定的两个字符串s1和s2是否互为旋转词
 */
@SuppressWarnings("all")
public class RotationString {


    // for test
    public static String generate(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (int) (Math.random() * 2)));
        }
        return sb.toString();
    }

    /**
     * 思路:
     * 在a+a中寻找b，找到就是有，找不到就是没有
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isRotation(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        String aa = a + a;
        return findIndex(aa, b) != -1;
    }

    /**
     * 暴力解
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isRotation2(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            String a1 = a.substring(i, a.length());
            String a2 = a.substring(0, i);
            String target = a1 + a2;
            if (target.equals(b)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 字符串的最长前缀
     *
     * @param s
     * @return
     */
    public static int[] next(String s) {
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return new int[]{-1};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;
        while (i < arr.length) {
            if (arr[i - 1] == arr[cur]) {
                next[i++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                i++;
            }
        }
        return next;
    }

    public static int findIndex(String a, String b) {
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        int[] next = next(b);
        int x = 0;
        int y = 0;
        while (x < arrayA.length && y < arrayB.length) {
            if (arrayA[x] == arrayB[y]) {
                x++;
                y++;
            } else if (next[y] < 0) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == arrayB.length ? x - y : -1;
    }

    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "======>" + arr[i]);
        }
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 5;
        for (int i = 0; i < loops; i++) {
            String a = generate(maxLength);
            String b = generate(maxLength);
            if (isRotation(a, b) != isRotation2(a, b)) {
                System.out.println("a=======" + a);
                System.out.println("b=======" + b);
                System.out.println("Oops!");
            }
        }
    }

}
