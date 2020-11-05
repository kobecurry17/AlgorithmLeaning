package class10;

/**
 * <p>
 * int[] d, d[i]:i号怪兽的能力
 * int[] p, p[i]:p号怪兽的所需要的金钱
 * 开始时你的能力是0,你的目标是从0好怪兽开始，通过所有的怪兽
 * 如果你当前的能力小于i号怪兽的能力，你必须付出p[i]的钱，收买这个怪兽，然后怪兽的能力
 * 会叠加到你的能力上
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过
 * 你也可以选择收买这个怪兽，他的能力直接累加到你的能力上
 * 返回通过所有的怪兽，需要花的最小钱数
 * </p>
 */
@SuppressWarnings("all")
public class FightMonstersGame {

    /**
     * dp [i][j] 表示 到第i个怪物，j能力的情况下，要花费多少钱
     *
     * @param d
     * @param p
     * @return
     */
    public static int minMoney1(int[] d, int[] p) {
        if (null == d || null == p || d.length != p.length || p.length == 0) {
            return 0;
        }
        int n = d.length;
        int abilitySum = 0;
        int maxAbility = 0;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            abilitySum += d[i];
            if (maxAbility < d[i]) {
                maxAbility = d[i];
                maxIndex = i;
            }
            maxAbility = Math.max(maxAbility, d[i]);
        }
        // dp [i][j] 表示 到第i个怪物，j能力的情况下，要花费多少钱
        // 最后返回 Min(dp[0..max(d)][max(d)])
        int[][] dp = new int[n + 1][abilitySum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= abilitySum; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 到达i时，能力低于此前max是无效的
        int max = d[1];
        dp[1][d[0]] = p[0];
        for (int i = 2; i <= n; i++) {
            max = Math.max(max, d[i - 1]);
            for (int j = max; j <= abilitySum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - d[i - 1] > 0 && dp[i - 1][j - d[i - 1]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - d[i - 1]] + p[i - 1]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = maxAbility; j <= abilitySum; j++) {
            res = Math.min(res, dp[maxIndex + 1][j]);
        }
        return res;
    }


    /**
     * dp [i][j] 表示 到第i个怪物，花费j情况下，能力最高多少
     *
     * @param d
     * @param p
     * @return
     */
    public static int minMoney2(int[] d, int[] p) {
        if (null == d || null == p || d.length != p.length || p.length == 0) {
            return 0;
        }
        int n = d.length;
        int paySum = 0;
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            paySum += p[i];
            if (d[i] > max) {
                max = d[i];
                maxIndex = i;
            }
        }
        // dp[i][j] => 到达第i个怪兽,花费j元，获得的最大能力
        int[][] dp = new int[n + 1][paySum + 1];

        dp[1][p[0]] = d[0];
        int maxD = d[0];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= paySum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - p[i - 1] > 0 && dp[i - 1][j - p[i - 1]] >= maxD) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p[i - 1]] + d[i - 1]);
                }
            }
            maxD = Math.max(maxD, d[i - 1]);
        }

        for (int i = 0; i <= paySum; i++) {
            if (dp[n][i] >= max) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }


    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void print(int[] d, int[] p) {
        System.out.print("d=>{");
        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("    p=>{");
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("\n==================\n");
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 10;
        int max = 100;
        for (int i = 0; i < loops; i++) {
            int[] d = generate(maxLength, max);
            int[] p = generate(maxLength, max);
            if (minMoney1(d, p) != minMoney2(d, p)) {
                print(d, p);
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }


}
