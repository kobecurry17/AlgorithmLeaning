package InterviewStage2.class15;

import java.util.HashMap;

/**
 * 给定一颗二叉树的头结点head,和一个数K
 * 路径的定义:
 * 可以从任何一个点开始,但是只能往下走,
 * 往下走可以走到任何结点停止
 * 返回路径累加和为K的所有路径中，最常的路径最多有多少个节点
 */
@SuppressWarnings("all")
public class BinaryTreePathSumEqualsK {

    static int aaa;

    /**
     * 时间复杂度(n ^ 2)
     *
     * @param head
     * @param k
     * @return
     */
    public static int maxNodeSize(Node head, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        return process(map, head, 0, k, 1);
    }

    private static int process(HashMap<Integer, Integer> map, Node node, int allSum, int target, int level) {
        if (null == node) {
            return 0;
        }
        int ans = 0;
        allSum += node.value;
        int key = allSum - target;
        if (map.containsKey(key)) {
            ans = level - map.get(key);
        }
        if (!map.containsKey(allSum)) {
            map.put(allSum, level);
        }
        ans = Math.max(ans,  Math.max(process(map, node.right, allSum, target, level + 1),process(map, node.left, allSum, target, level + 1)));
        if (map.get(allSum) == level) {
            map.remove(allSum);
        }
        return ans;
    }


    public static int longest(Node head, int K) {
        aaa = 0;
        // key ： 前缀和
        // value : 该前缀和，最早出现在哪一层
        // sumMap：只维持，从头节点出发到当前节点，这条路径上的前缀和
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        process(head, 0, 0, K, sumMap);
        return aaa;
    }

    // 节点X在level层，从头节点到X的父节点形成的累加和是preSum，
    // 从头节点到X的路径上，每一个前缀和都存在sumMap里(key)，记录的是该前缀和最早出现的层数(value)
    // 求出必须以X节点结尾的、累加和是K的所有路径中，含有最多的节点是多少？
    // 并看看能不能更新全局的ans
    public static void process(Node X, int level, int preSum, int K, HashMap<Integer, Integer> sumMap) {
        if (X != null) {
            // 从头节点出发，到当前X节点，总的前缀和是多少，allSum
            int allSum = preSum + X.value;
            if (sumMap.containsKey(allSum - K)) {
                aaa = Math.max(aaa, level - sumMap.get(allSum - K));
            }
            if (!sumMap.containsKey(allSum)) {
                sumMap.put(allSum, level);
            }
            process(X.left, level + 1, allSum, K, sumMap);
            process(X.right, level + 1, allSum, K, sumMap);
            if (sumMap.get(allSum) == level) {
                sumMap.remove(allSum);
            }
        }
    }

    /**
     * 二叉树结点
     */
    public static class Node {

        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        int K = 0;
        Node head = new Node(3);
        head.left = new Node(-2);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.left.left.left = new Node(3);
        head.left.left.right = new Node(5);
        head.left.right.left = new Node(2);
        head.left.right.right = new Node(5);

        head.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(-7);
        head.right.left.left = new Node(-5);
        head.right.left.right = new Node(-3);
        head.right.right.left = new Node(1);
        head.right.right.right = new Node(5);

        System.out.println(maxNodeSize(head, 0));
        System.out.println(longest(head, 0));
    }
}
