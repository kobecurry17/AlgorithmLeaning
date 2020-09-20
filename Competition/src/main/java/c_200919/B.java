package c_200919;

import java.util.Arrays;

public class B {

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(nums);
        int[] help = new int[nums.length];
        for (int i = 0; i < requests.length; i++) {
            for (int j = requests[i][0]; j <= requests[i][1]; j++) {
                help[j]++;
            }
        }
        Arrays.sort(help);
        long ans = 0;
        for (int i = 0; i < help.length; i++) {
            ans += help[i] * nums[i];
        }
        return (int) (ans % (1e9 + 7));
    }


    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,10};
        int[][] req = {
                {
                        0, 2
                }, {
                1, 3
        }, {
                1, 1
        },
        };
        B b = new B();
        System.out.println(b.maxSumRangeQuery(a, req));
    }

}
