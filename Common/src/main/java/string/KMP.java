package string;


/**
 * 在字符串A中寻找字符串B最早开始的下标
 * 时间复杂度O(logN)
 */
public class KMP {

    /**
     * 找到b字符串在a字符串中第一次出现的下标，如果不存在返回-1
     *
     * @param a
     * @param b
     * @return
     */
    public static int findFirstIndex(String a, String b) {
        if (null == b || b.trim().isEmpty() || b.length() > a.length()) {
            return -1;
        }
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        int[] next = next(arrayB);
        int x = 0;
        int y = 0;
        while (x < arrayA.length && y < arrayB.length) {
            if (arrayA[x] == arrayB[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == arrayB.length ? x - y : -1;
    }

    /**
     * 暴力解
     *
     * @param a
     * @param b
     * @return
     */
    public static int ans1(String a, String b) {
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        for (int i = 0; i < arrayA.length - arrayB.length + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arrayB.length; j++) {
                if (arrayA[i + j] != arrayB[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 字符串的最长回文前缀
     * <p>
     * 例如：   "aabaacaabaacte"
     * next[] = {-1,0,1,0,1,2,0,1,2,3,4,5,6,0}
     * <p>
     * 例如：   "abasabatabasabakz"
     * next[] = {-1,0,0,a,0,1,2,3,0,1,2,3,4,5,6,7,0}
     * 时间复杂度O(n)
     * </p>
     * 用案例2理解
     * 当位置到z的时候
     * 看k的前缀长度是多少，是7，如果k和t相等,那么z=7+1=8
     * 因为k=t不等所以看next[next[cur-1]]是多少
     * t是3,接着看s和k是否相等，不等所以看next[next[next[cur-1]]]
     * s是1 看b和k是否相等，不等，所以看next[next[next[next[cur-1]]]]
     * a是-1所以z=0
     *
     * @return
     */
    public static int[] next(char[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        if (arr.length == 2) {
            return new int[]{-1, 0};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        int cur = 0;
        int i = 2;
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


    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "======>" + arr[i]);
        }
    }

    // for test
    public static boolean equalArray(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }


    // for testing
    public static String generate(int maxSize) {
        int size = (int) (Math.random() * maxSize) + 1;
        char[] arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (char) ('a' + (int) (Math.random() * 26));
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        int loops = 100_0000;
        int maxBLength = 50;
        int maxALength = 5000;
        for (int i = 0; i < loops; i++) {
            String a = generate(maxALength);
            String b = generate(maxBLength);
            if (ans1(a, b) != findFirstIndex(a, b)) {
                System.out.println(a);
                System.out.println(b);
                ans1(a, b);
                findFirstIndex(a, b);
            }
        }
    }
}
