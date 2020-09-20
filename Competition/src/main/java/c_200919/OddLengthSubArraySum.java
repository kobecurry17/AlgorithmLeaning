package c_200919;

public class OddLengthSubArraySum {


    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }


    public static int ans1(int[] arr) {
        int[] help = new int[arr.length];
        help[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i] = arr[i] + help[i - 1];
        }
        int max = (arr.length & 1) == 0 ? arr.length - 1 : arr.length;
        int ans = 0;
        for (int i = 1; i <= max; i += 2) {
            ans += help[i - 1];
            for (int j = i; j < arr.length; j++) {
                ans += help[j] - help[j - i];
            }
        }
        return ans;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static void main(String[] args) {
        System.out.println(ans1(new int[]{1, 4, 2, 5, 3}));
    }

}
