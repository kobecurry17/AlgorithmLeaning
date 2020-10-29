package class9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 给定两个有序数组arr1和arr2，再给定一个整数K
 * 求两个数累加和最大的第K个
 * 俩那个数必须来自arr1和arr2
 * </p>
 */
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
        if (topK < 0) {
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
    public static int doubleArraySumKthMax2(int[] arr1, int[] arr2, int topK) {

        // 傻缓存，记录arr1[i] * arr2[j] 是否使用过
        // 生成结点 结点记录 i j arr1[i] + arr2[j]
        // 使用小根堆 每次弹出最小值 把最小值相邻的数放进小根堆


        return -1;
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
        return arr;
    }

    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "======>" + arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{3, 4, 7, 8, 9};
        print(doubleArraySumKthMax1(arr1, arr2, 7));
    }
}
