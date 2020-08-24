package trymodel;

import org.omg.CORBA.BAD_CONTEXT;

/**
 * 背包问题
 * <p>
 * 给定两个长度都为N的数组 weights 和 values
 * 给定一个正数bags,表示一个载重为bag的袋子
 * 你装的物品不能比bags大
 * 返回你能装下的最大价值是多少？
 * </p>
 */
public class Knapsack {


    public static int maxValue1(int[] weights, int[] values, int bags) {
        if (weights.length == 0 || values.length == 0 || bags <= 0) {
            return 0;
        }
        return process(weights, values, 0, bags, 0);
    }

    public static int dpWays1(int[] weights, int[] values, int bags) {
        int N = weights.length;
        int[][] dp = new int[N + 1][bags + 1];

        for (int index = N; index > 0; index--) {
            for (int rest = bags; rest > 0; rest--) {
                if (index >= N || rest <= 0) {
                    dp[index][rest] = 0;
                }
                int yes = 0;
                if (weights[index] <= rest) {
                    yes = dp[values[index]][rest -weights[index]];
                }
                int no =
            }


            if (weights[index] <= rest) {
                yes = values[index] + process(weights, values, value + values[index], rest - weights[index], index + 1);
            }
            int no = process(weights, values, value, rest, index + 1);
            return Math.max(yes, no);


            return dp[0][bags];
        }

        public static int maxValue2 ( int[] weights, int[] values, int bags){
            if (weights.length == 0 || values.length == 0 || bags <= 0) {
                return 0;
            }
            return process(weights, values, 0, bags);
        }

        private static int process ( int[] weights, int[] values, int value, int rest, int index){
            if (index >= weights.length || rest <= 0) {
                return 0;
            }
            int yes = 0;
            if (weights[index] <= rest) {
                yes = values[index] + process(weights, values, value + values[index], rest - weights[index], index + 1);
            }
            int no = process(weights, values, value, rest, index + 1);
            return Math.max(yes, no);
        }

        // for test
        public static int[] generate ( int size, int maxValue){
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            }
            return arr;
        }

        // 只剩下rest的空间了，
        // index...货物自由选择，但是不要超过rest的空间
        // 返回能够获得的最大价值
        public static int process ( int[] w, int[] v, int index, int rest){
            if (rest < 0) { // base case 1
                return -1;
            }
            // rest >=0
            if (index == w.length) { // base case 2
                return 0;
            }
            // 有货也有空间
            int p1 = process(w, v, index + 1, rest);
            int p2 = -1;
            int p2Next = process(w, v, index + 1, rest - w[index]);
            if (p2Next != -1) {
                p2 = v[index] + p2Next;
            }
            return Math.max(p1, p2);
        }

        public static void main (String[]args){
            int loops = 50_0000;
            int maxWeight = 30;
            int maxValue = 300;
            int maxSize = 10;
            int maxBags = 50;
            for (int i = 0; i < loops; i++) {
                int size = (int) (Math.random() * maxSize);
                int[] values = generate(size, maxValue);
                int[] weights = generate(size, maxWeight);
                int bags = (int) (Math.random() * maxBags);
                if (maxValue1(weights, values, bags) != maxValue2(weights, values, bags)) {
                    System.out.println("Oops");
                }
            }
            System.out.println("Nice");
        }


    }
