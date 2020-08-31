package dp;

/**
 * 最长公共子序列问题
 */
public class LCS {

    public static int longestCommonSubsequence1(String text1, String text2) {
        if (null == text1 || null == text2 || text1.trim().isEmpty() || text2.trim().isEmpty()) {
            return 0;
        }
        return process(text1.toCharArray(), text2.toCharArray(), 0, 0);
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        if (null == text1 || null == text2 || text1.trim().isEmpty() || text2.trim().isEmpty()) {
            return 0;
        }
        return ans2(text1.toCharArray(), text2.toCharArray(), 0, 0);
    }

    public static int process(char[] arr1, char[] arr2, int aIndex, int bIndex) {
        if (aIndex >= arr1.length || bIndex >= arr2.length) {
            return 0;
        }
        int noA = process(arr1, arr2, aIndex + 1, bIndex);
        int noB = process(arr1, arr2, aIndex, bIndex + 1);
        int yes = -1;
        if (arr1[aIndex] == arr2[bIndex]) {
            yes = 1 + process(arr1, arr2, aIndex + 1, bIndex + 1);
        }
        return Math.max(noA, Math.max(noB, yes));
    }

    public static int ans2(char[] arr1, char[] arr2, int aIndex, int bIndex) {
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = arr1.length - 1; i >= 0; i--) {
            for (int j = arr2.length - 1; j >= 0; j--) {
                int noA = dp[i + 1][j];
                int noB = dp[i][j + 1];
                int yes = -1;
                if (arr1[i] == arr2[j]) {
                    yes = 1 + dp[i + 1][j + 1];
                }
                dp[i][j] = Math.max(noA, Math.max(noB, yes));
            }
        }
        return dp[0][0];
    }

    // for test
    public static String generate(int size) {
        char[] arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (char) ('a' + ((int) (Math.random() * 26)));
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        int loops = 50_000;
        int maxSize = 10;
        for (int i = 0; i < loops; i++) {
            int size = (int) (Math.random() * maxSize);
            String str1 = generate(size);
            String str2 = generate(size);
            if (longestCommonSubsequence1(str1, str2) != longestCommonSubsequence2(str1, str2)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }
}
