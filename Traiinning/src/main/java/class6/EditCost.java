package class6;

import sun.font.FontRunIterator;

/**
 * <p>
 * 编辑代价
 * </p>
 * 给定两个字符串str1和str2，再给定三个整数ic,dc和rc
 * 分别代表插入、删除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
 * 【举例】
 * <p>
 * str1 = "abc", str2 = "adc", ic = 5, dc = 3, rc = 2
 * 从"abc" 编辑成"adc",把'b'替换成'd' 是代价最小的。所有返回2
 * </p>
 * <p>
 * str1 = "abc", str2 = "adc", ic = 5, dc = 3, rc = 100
 * 从"abc" 编辑成"adc",先删除'b',然后插入'd'是代价最小的。所有返回2
 * </p>
 * <p>
 * str1 = "abc", str2 = "abc", ic = 5, dc = 3, rc = 100
 * str1 == str2 不用编辑,返回0
 * </p>
 */
public class EditCost {

    /**
     * 时间复杂度O(N * M)
     *
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == str2 || (str1 != null && str2 != null && str1.equals(str2))) {
            return 0;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int N = arr1.length + 1;
        int M = arr2.length + 1;
        int dp[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            dp[i][0] = i * ic;
        }
        for (int i = 0; i < M; i++) {
            dp[0][i] = i * ic;
        }
        // 假设 str1 = "abcdef"
        // 假设 str2 = "csdasw"
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                // 普遍位置
                // abc->csd
                // 1) ab ->cs + c->d
                // 2) abc->cs + d
                // 3) ab ->csd - c
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j - 1] + ic, dp[i][j]);
                dp[i][j] = Math.min(dp[i - 1][j] + dc, dp[i][j]);
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));

    }
}
