package c_200919;

import java.util.HashMap;
import java.util.Objects;

public class C {

    public static int minSubarray(int[] nums, int p) {
        if (nums.length == 1) {
            return nums[0] % p == 0 ? 0 : -1;
        }
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum != 0 && sum % p == 0) {
            return 0;
        }
        HashMap<Node, Integer> cache = new HashMap<>();
        return process(nums, 0, 0, p, cache);
    }

    private static int process(int[] nums, int index, int sum, int p, HashMap<Node, Integer> cache) {
//        Node key = new Node(sum, p);
//        if (cache.containsKey(key)) {
//            return cache.get(key);
//        }
        int ans;
        if (index == nums.length) {
            ans = (sum % p != 0 || sum == 0 ? -1 : 0);
//            cache.put(key, ans);
            return ans;
        }
        int no = process(nums, index + 1, sum, p, cache);
        int yes = process(nums, index + 1, sum + nums[index], p, cache);
        if (no == -1 && yes == -1) {
            ans = -1;
        } else if (no == -1 || yes == -1) {
            ans = (no != -1 ? no + 1 : yes);
        } else {
            ans = Math.min(no + 1, yes);
        }
//        cache.put(key, ans);
        return ans;
    }


    public static class Node {
        public int sum;
        public int p;

        public Node(int sum, int p) {
            this.sum = sum;
            this.p = p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return sum == node.sum &&
                    p == node.p;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, p);
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2};
        int p = 6;

        System.out.println(minSubarray(arr, p));
    }
}
