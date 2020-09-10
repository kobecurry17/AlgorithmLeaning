package fibonacci;

@SuppressWarnings("all")
public class Cow {

    /**
     * 成熟奶牛每隔一年生一头母牛，母牛三年成熟，N年后有多少头牛
     * F(n)=F(n-1)+f(n-3)
     *
     * @param year
     * @return
     */
    public static int cowNum1(int year) {
        if (year < 3) {
            return 1;
        }
        return cowNum1(year - 1) + cowNum1(year - 3);
    }

    /**
     * @param year
     * @return
     */
    public static int cowNum2(int year) {
        if (year < 3) {
            return 1;
        }
        if (year == 3) {
            return 2;
        }
        int[][] base = {
                {1, 1, 0}, {0, 0, 1}, {1, 0, 0},
        };
        int[][] res = matrixPower(base, year - 3);
        return 2 * res[0][0] + res[0][1] + res[0][2];
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

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxNum = 30;
        for (int i = 1; i < 30; i++) {
            System.out.println(i+"==" +cowNum1(i));
            int i1 = cowNum1(i);
            int i2 = cowNum2(i);
            if(i1!=i2){
                System.out.println("Oops！i="+i);
            }
        }
    }

}
