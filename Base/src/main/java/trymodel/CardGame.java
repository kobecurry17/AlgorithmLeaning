package trymodel;

import datastructure.MaxHeap1;

/**
 * 给定i一个整型数组arr,代表数值不同的纸牌排成一条线
 * 玩家A和玩家B一次拿走每张纸牌
 * 规定外加A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数
 */
public class CardGame {

    /**
     * 获胜者的分数
     *
     * @param arr
     * @return
     */
    public static int winnerScore(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int f = f(arr, 0, arr.length - 1);

        int s = s(arr, 0, arr.length - 1);

        return Math.max(f, s);
    }

    /**
     * 在 L...R 上后手
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.max(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    /**
     * 在 L...R 上先手
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.min(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int winnerScore2(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] F = new int[N + 1][N + 1];
        int[][] S = new int[N + 1][N + 1];
        //F[i][i] = arr[i]
        //S[i][i] = 0
        for (int i = 0; i < N; i++) {
            F[i][i] = arr[i];
        }


        for (int i = N; i >=0; i--) {
            for (int j = 0; j < ; j++) {

            }
        }


        if (L == R) {
            return arr[L];
        }
        return Math.min(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));





        return Math.max(S[0][N], F[0][N]);


        int f = f(arr, 0, arr.length - 1);

        int s = s(arr, 0, arr.length - 1);

        return Math.max(f, s);

        return
    }


    // for testing
    public static int[] generate(int size, int max) {
        if (size <= 0) {
            return null;
        }
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
//        for (int i = 0; i < loops; i++) {
        int[] arr = {5, 2, 1, 23, 10};
        if (true) {
            System.out.println(winnerScore(arr));
//            }
        }

    }

}
