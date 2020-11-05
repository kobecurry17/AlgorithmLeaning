package InterviewStage1.class9;

import java.util.*;

/**
 * <p>
 * 给定两个有序数组arr1和arr2，再给定一个整数K
 * 求两个数累加和最大的第K个
 * 俩那个数必须来自arr1和arr2
 * </p>
 */
@SuppressWarnings("all")
public class DoubleArraySumKthMax {


    /**
     * O(MAX(( N + M )* log(N + M), N * M ))
     *
     * @param arr1
     * @param arr2
     * @param topK
     * @return
     */
    public static int[] doubleArraySumKthMax1(int[] arr1, int[] arr2, int topK) {
        if (arr1.length < 1 || arr2.length < 1 || topK < 0) {
            return new int[0];
        }
        topK = Math.min(arr1.length * arr2.length, topK);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                list.add(arr1[i] + arr2[j]);
            }
        }
        list.sort(Integer::compareTo);
        list = list.subList(0, topK);
        int[] res = new int[topK];
        for (int i = 0; i < topK; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * O(LogK)
     *
     * @param arr1
     * @param arr2
     * @param topK
     * @return
     */
    public static int[] doubleArraySumKthMax2(int[] arr1, int[] arr2, int topK) {

        // 傻缓存，记录arr1[i] * arr2[j] 是否使用过
        // 生成结点 结点记录 i j arr1[i] + arr2[j]
        // 使用小根堆 每次弹出最小值 把最小值相邻的数放进小根堆
        // 每次弹出一个数，放到指定位置
        if (arr1.length < 1 || arr2.length < 1 || topK < 0) {
            return new int[0];
        }
        topK = Math.min(arr1.length * arr2.length, topK);
        int[] res = new int[topK];
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        heap.add(new Node(0, 0, arr1[0] + arr2[0]));
        int index = 0;
        boolean[][] bp = new boolean[arr1.length][arr2.length];
        bp[0][0] = true;
        while (index < topK) {
            Node poll = heap.poll();
            if (poll.arr1Index < arr1.length - 1 && !bp[poll.arr1Index + 1][poll.arr2Index]) {
                heap.add(new Node(poll.arr1Index + 1, poll.arr2Index, arr1[poll.arr1Index + 1] + arr2[poll.arr2Index]));
                bp[poll.arr1Index + 1][poll.arr2Index] = true;
            }
            if (poll.arr2Index < arr2.length - 1 && !bp[poll.arr1Index][poll.arr2Index + 1]) {
                heap.add(new Node(poll.arr1Index, poll.arr2Index + 1, arr1[poll.arr1Index] + arr2[poll.arr2Index + 1]));
                bp[poll.arr1Index][poll.arr2Index + 1] = true;
            }
            res[index++] = poll.sum;
        }
        return res;
    }


    public static class Node {
        int arr1Index;
        int arr2Index;
        int sum;

        public Node(int arr1Index, int arr2Index, int sum) {
            this.arr1Index = arr1Index;
            this.arr2Index = arr2Index;
            this.sum = sum;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.sum - o2.sum;
        }
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }


    private static boolean arrEquals(int[] ans1, int[] ans2) {
        if (null == ans1 && null == ans2) {
            return true;
        }
        if (ans1.length != ans2.length) {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < ans1.length; i++) {
            if (ans1[i] != ans2[i]) {
                return false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 10;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr1 = generate(maxSize, maxValue);
            int[] arr2 = generate(maxSize, maxValue);
            int topK = (int) (Math.random() * maxSize * maxSize) + 1;
            int[] res1 = doubleArraySumKthMax1(arr1, arr2, topK);
            int[] res2 = doubleArraySumKthMax2(arr1, arr2, topK);
            if (!arrEquals(res1, res2)) {
                System.out.println("Oops!");
                doubleArraySumKthMax1(arr1, arr2, topK);
                doubleArraySumKthMax2(arr1, arr2, topK);
            }
        }
        System.out.println("Nice");
    }
}
