package fibonacci;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * 在一个2*N 的空间里铺1*2的瓷砖,有多少种铺法
 */
@SuppressWarnings("all")
public class Tile {

    /**
     * @param n
     * @return
     */
    public static int ans1(int n) {
        return process(n);
    }

    private static int process(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return process(n - 1) + process(n - 2);
    }


    /**
     * 矩阵乘积
     */
    private static int[][] matrixMut(int[][] matrix1, int[][] matrix2) {
        int[][] matrix = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix;
    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public static int ans2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + 1 * res[0][1];
    }


    /**
     * 矩阵的N次幂
     * 时间复杂度O(logN)
     *
     * @param matrix
     * @param p
     * @return
     */
    private static int[][] matrixPower(int[][] matrix, int p) {
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = matrix;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = matrixMut(res, tmp);
            }
            tmp = matrixMut(tmp, tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        int maxLength = 30;
        for (int i = 0; i < maxLength; i++) {
            if (ans1(i) != ans2(i)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }
}
