package c_200912;

import java.util.Arrays;

/**
 * 扣在秋日市集选择了一家早餐摊位
 * 一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks中记录了每种饮料的价格。
 * 选择一份主食和一款饮料，且花费不超过 x 元。返回共有多少种购买方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 */
@SuppressWarnings("all")
public class Breakfast {

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static int rand(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    /**
     * 一个大坑
     * 当res溢出的时候，答案就错了
     *
     * @param staple
     * @param drinks
     * @param k
     * @return
     */
    public static int ans1(int[] staple, int[] drinks, int k) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int L = 0;
        int R = drinks.length - 1;
        int res = 0;
        while (R >= 0 && L < staple.length) {
            if (staple[L] + drinks[R] > k) {
                R--;
            } else {
                res += R + 1;
                L++;
            }
        }
        int mod = (int) 1e+9;
        return (res % mod);
    }

    /**
     * 时间复杂度O(N*logN)
     *
     * @param staple
     * @param drinks
     * @param k
     * @return
     */
    public static int ans2(int[] staple, int[] drinks, int k) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int L = 0;
        int R = drinks.length - 1;
        long res = 0;
        while (R >= 0 && L < staple.length) {
            if (staple[L] + drinks[R] > k) {
                R--;
            } else {
                res += R + 1;
                L++;
            }
        }
        int mod = (int) 1e+9;
        return (int) (res % mod);
    }


    /**
     * 参赛选手答案
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public static int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int r = drinks.length - 1;
        long ans = 0;
        for (int i = 0; i < staple.length; i++) {
            while (r >= 0 && drinks[r] + staple[i] > x) {
                r--;
            }
            ans += r + 1;
        }
        int mod = (int) 1e9 + 7;
        return (int) (ans % mod);
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int max = 10;
        int maxK = 100;
        int maxValue = 100;
        for (int i = 0; i < loops; i++) {
            int[] staple = generate(max, maxValue);
            int[] drinks = generate(max, maxValue);
            int k = (int) (Math.random() * maxK);
            int i1 = ans1(staple, drinks, k);
            int i2 = breakfastNumber(staple, drinks, k);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }
    }


}
