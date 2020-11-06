package InterviewStage2.class13;

/**
 * <p>
 * 给定一个数组arr,在给定一个k值
 * 返回累加和小于等于k，但是离k最近的子数组累加和
 * </p>
 */
@SuppressWarnings("all")
public class ArraySumLessThanK {

    /**
     * 时间复杂度O(N^2)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int mostNearSum(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] help = new int[arr.length];
        help[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i] = help[i - 1] + arr[i];
        }
        int ans = k + Integer.MIN_VALUE - 1;
        for (int i = 0; i < help.length; i++) {
            for (int j = i; j < help.length; j++) {
                int sum = help[j] - help[i];
                if (Math.abs(sum - k) < Math.abs(ans - k)) {
                    ans = sum;
                }
            }
        }
        return ans;
    }


    /**
     * 时间复杂度O(n^3)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int mostNearSum1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = k + Integer.MIN_VALUE - 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int l = i; l < j; l++) {
                    sum += arr[l];
                }
                if (Math.abs(sum - k) < Math.abs(ans - k)) {
                    ans = sum;
                }
            }
        }
        return ans;
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void print(int[] arr, int k) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("  ,k= " + k);
        System.out.print("\n==================\n");
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int maxValue = 30;
        for (int i = 0; i < loops; i++) {
//            int[] arr = generate(maxLength, maxValue);
            int[] arr = {25};
            int k = (int) (Math.random() * 200);
            if (mostNearSum(arr, k) != mostNearSum1(arr, k)) {
                print(arr, k);
            }
        }
        System.out.println("Nice");
    }


}
