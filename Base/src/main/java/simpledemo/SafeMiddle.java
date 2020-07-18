package simpledemo;

import java.math.BigDecimal;

/**
 * 安全的求平均数
 * 应该使用 a + (b - a) / 2.0; a+a->b 的 距离 /2 否则有溢出的风险
 */
public class SafeMiddle {

    public static void main(String[] args) {

        //        judgeEqual();

                  testTimeConsuming();
    }

    static double calculate1(int a, int b) {
        return (a + b) / 2.0;
    }


    static double calculate2(int a, int b) {
        return a + (b - a) / 2.0;
    }

    static double calculate3(int a, int b) {
        return a + ((b - a) >> 1);
    }


    /**
     * 测试是否相等方案
     */
    static void judgeEqual() {
        int testSize = 50000;
        int limit = Integer.MAX_VALUE;
        for (int i = 0; i < testSize; i++) {
            int a = (int) (Math.random() * limit) - 100;
            int b = (int) (Math.random() * limit) - 100;
            if (new BigDecimal(calculate3(a, b)).equals(new BigDecimal(calculate2(a, b)))) {
                System.out.println(String.format("%d---%d + calculate3:%s + calculate3:%s", a, b, new BigDecimal(calculate3(a, b)), new BigDecimal(calculate2(a, b))));
            }
        }
    }

    /**
     * 测试耗时情况
     */
    static void testTimeConsuming() {
        int testSize = 5000000;
        int limit = Integer.MAX_VALUE;
        int[] a= new int[testSize];
        int[] b= new int[testSize];

        for (int i = 0; i < testSize; i++) {
            a[i]=(int) (Math.random() * limit);
            b[i]=(int) (Math.random() * limit);
        }
        long s1 = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            calculate2(a[i],b[i]);
        }
        long s2 = System.nanoTime();

        long s3 = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            calculate3(a[i],b[i]);
        }
        long s4 = System.nanoTime();

        System.out.println(String.format("time1+%s",s2-s1));
        System.out.println(String.format("time2+%s",s4-s3));

    }

}
