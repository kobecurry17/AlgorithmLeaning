package class2;

/**
 * 在行有序，列也有序的二维数组中，找num,找到返回true,否则返回false
 */
public class FindNumInSortMatrix {

    public static boolean right(int[][] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean findNum(int[][] matrix, int num) {
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == num) {
                return true;
            } else if (matrix[row][col] > num) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 4, 9},
                {3, 7, 10, 13},
                {4, 8, 11, 17},
                {6, 10, 14, 29},
                {9, 11, 15, 33}
        };
        if (right(arr, 3) != findNum(arr, 3)) {
            System.out.println("Oops");
        }
        if (right(arr, 22) != findNum(arr, 22)) {
            System.out.println("Oops");
        }

        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6},// 0
                {10, 12, 13, 15, 16, 17, 18},// 1
                {23, 24, 25, 26, 27, 28, 29},// 2
                {44, 45, 46, 47, 48, 49, 50},// 3
                {65, 66, 67, 68, 69, 70, 71},// 4
                {96, 97, 98, 99, 100, 111, 122},// 5
                {166, 176, 186, 187, 190, 195, 200},// 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        for (int i = 0; i < 400; i++) {
            if (right(arr, i) != findNum(arr, i)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");


    }


}
