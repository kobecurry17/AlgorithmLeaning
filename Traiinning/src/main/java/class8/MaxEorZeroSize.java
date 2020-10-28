package class8;

/**
 * 对数组进行任一划分，怎么是划分后的疑惑和为 0 的数组数量最多
 * TODO:复习
 */
public class MaxEorZeroSize {

    /**
     * 对数组进行任一划分，怎么是划分后的疑惑和为 0 的数组数量最多
     *
     * @param arr
     * @return
     */
    public static int maxEorZeroSize(int[] arr) {
        return 0;
    }


    public static int[][] prefixDP(int[] arr) {
        int[] help = new int[arr.length + 1];
        help[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = help[i - 1] ^ arr[i];
        }

        return null;
    }


}
