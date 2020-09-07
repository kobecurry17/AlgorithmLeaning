package singlestack;

import java.util.IllformedLocaleException;
import java.util.Stack;

/**
 * 给定任意形状的二维图形，问最大的矩阵是多大
 * 例如给定
 * 1-1-1
 * 1-1-1
 * 0-0-1
 * 最大矩阵为 6,即(0,0)->(1,2)
 */
public class MaxMatrix {

    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] generateArr(int height, int width) {
        int[][] arr = new int[width][height];
        for (int i = 0; i < width; i++) {
            arr[i] = generate(2, height);
        }
        return arr;
    }


    /**
     * 矩阵中最大的矩形面积
     *
     * @param arr
     * @return
     */
    public static int maxRectInMatrix(int[][] arr) {
        if (arr.length <= 0 || arr[0].length <= 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int length = 0;
        int maxHeight = 0;
        int[][] help = helpArr(arr);
        for (int i = 0; i < arr.length; i++) {
            length = 0;
            for (int j = 0; j < arr[0].length; j++) {
                if (help[i][j] != 0) {
                    maxHeight = Math.max(help[i][j], maxHeight);
                    max = Math.max(max, length * maxHeight);
                } else {
                    length = 0;
                }
            }
        }
        return max;
    }

    /**
     * 计算每个位置由它往下最大能组成多大的矩形
     *
     * @param arr
     * @return
     */
    public static int[][] helpArr(int[][] arr) {
        int[][] help = new int[arr.length][arr[0].length];
        Stack<Integer> stack = new Stack<>();
        int size;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    stack.push(j);
                } else {
                    size = 0;
                    while (!stack.isEmpty()) {
                        help[i][stack.pop()] = ++size;
                    }
                }
            }
            size = 0;
            while (!stack.isEmpty()) {
                help[i][stack.pop()] = ++size;
            }
        }

        return help;
    }

    // for test
        public static int generate(int maxValue) {
            return (int) (Math.random() * maxValue);
        }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxHeight = 10;
        int maxWidth = 10;
        for (int i = 0; i < loops; i++) {
            int height = generate(maxHeight);
            int width = generate(maxWidth);
            int[][] arr = generateArr(height, width);
            int ans1 = maxRectInMatrix(arr);
            System.out.println(ans1);
        }
    }


}
