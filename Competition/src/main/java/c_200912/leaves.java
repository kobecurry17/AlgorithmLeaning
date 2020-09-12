package c_200912;

import java.util.Arrays;

public class leaves {

    public static int ans1(String s) {
        if (null == s || s.length() < 3) {
            return -1;
        }
        char[] arr = s.toCharArray();
        int[] yArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        if (arr[0] == 'y') {
            yArr[0] = 1;
            rArr[0] = 0;
        } else {
            yArr[0] = 0;
            rArr[0] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 'y') {
                yArr[i] = yArr[i - 1] + 1;
                rArr[i] = rArr[i - 1];
            } else {
                yArr[i] = yArr[i - 1];
                rArr[i] = rArr[i - 1] + 1;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int L = 1; L < arr.length; L++) {
            for (int R = L + 1; R < arr.length; R++) {
                ans = Math.min(L-yArr[L - 1] + arr.length-R-(yArr[arr.length - 1] - yArr[R]) + rArr[R - 1] - rArr[L - 1], ans);
            }
        }
        return ans;
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
        int s = (int) (Math.random() * size);
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
//            String s = generate(max);
            String s = "ryy";
            int i1 = minimumOperations(s);
            int i2 = ans1(s);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }

    }


}
