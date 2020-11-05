package InterviewStage1.class8;

/**
 * 一个数组的异或和是指数组中所有的数异或在一起的结果
 * 给定一个数组arr，求最大子数组异或和
 */
public class SubArrayMaxEOR {

    /**
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int maxEor1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int num = 0;
            for (int j = i; j < arr.length; j++) {
                num = num ^ arr[j];
                max = Math.max(num, max);
            }
        }
        return max;
    }

    /**
     * O(N)
     *
     * @param arr
     * @return
     */
    public static int maxEor2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        NumTrie numTrie = new NumTrie();
        int max = Integer.MIN_VALUE;
        int eor = 0;
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
            max = Math.max(numTrie.maxEor(eor), max);
            numTrie.add(eor);
        }
        return max;
    }


    public static class NumTrie {
        private static Node root;

        public NumTrie() {
            root = new Node();
        }

        public int maxEor(int num) {
            Node cur = root;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 目前走的路
                int path = (num >> move) & 1;
                // 实际走的路
                int best = move == 31 ? path : (path ^ 1);
                best = cur.next[best] == null ? best ^ 1 : best;
                ans = ans | ((best ^ path) << move);
                cur = cur.next[best];
            }
            return ans;
        }

        public void add(int eor) {
            Node cur = root;
            for (int move = 31; move >= 0; move--) {
                int path = (eor >> move) & 1;
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
            }
        }
    }

    public static class Node {
        Node[] next = new Node[2];
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 5;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, 30);
//            int[] arr = {20, 4};
            if (maxEor2(arr) != maxEor1(arr)) {
                System.out.println("Oops!");
                maxEor2(arr);
            }
        }
        System.out.println("Nice!");
    }

}
