package other;

import java.util.Arrays;

/**
 * 给定一个黑盒，可以均等概率生成7个数
 * 现由这个方法改成在n上等概率随机生成随机数
 */
public class Random {

    /**
     * 这是一个黑盒
     * 等概率生成1-7
     *
     * @return
     */
    public static int blackBox() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * 等概率生成0和1
     *
     * @return
     */
    public static int randomZeroAndOne() {
        while (true) {
            if (blackBox() == 7) {
                continue;
            }
            return blackBox() <= 3 ? 0 : 1;
        }
    }

    public static int random(int num) {
        int res = Integer.MAX_VALUE;
        while (res>num){
            res = process(num);
        }
        return res;
    }

    private static int process(int num) {
        int i = 1;
        int p = num;
        int res = 0;
        while (p > 0) {
            p >>= 1;
            res += randomZeroAndOne() * i;
            i <<= 1;
        }
        return res;
    }


    public static void main(String[] args) {
        int one = 0;
        int zero = 0;
        int[] arr = new int[11];
        for (int i = 0; i < 100_0000; i++) {
            int random = random(10);
            arr[random]++;
        }

        for (int i : arr) {
            System.out.println(i+" ");
        }
    }



}
