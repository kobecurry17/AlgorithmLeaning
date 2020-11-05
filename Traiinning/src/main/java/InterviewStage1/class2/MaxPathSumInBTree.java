package InterviewStage1.class2;

/**
 * <p>
 * 给定一个二叉树的头结点head，路径的规定有一下三种不同的规定:
 * 1)路径必须是头结点出发，到叶节点位置，返回最大路径和
 * 2)路径可以从任何节点出发，但必须往下走到任何结点，返回最大路径和
 * 3)路径可以从任何结点出发，到任何结点，返回最大路径和
 * </p>
 */
public class MaxPathSumInBTree {


    /**
     * <p>
     * 1)路径必须是头结点出发，到叶节点位置，返回最大路径和
     * </p>
     *
     * @param head
     * @return
     */
    public static int maxPathSumFromHead2Leaf(Node head) {
        return process(head);
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    private static int process(Node head) {
        if (null == head) {
            return 0;
        }
        if (head.isLeaf()) {
            return head.value;
        }
        int left = process(head.left);
        int right = process(head.right);
        return head.value + Math.max(left, right);
    }

    /**
     * 2)路径可以从任何节点出发，但必须往下走到任何结点，返回最大路径和
     */
    public static int maxPathSumFromAnyButGoingDown(Node head) {
        Info info = anyProcess(head);
        return info.max;
    }

    private static Info anyProcess(Node node) {
        Info info = new Info();
        if (null != node) {
            Info left = anyProcess(node.left);
            Info right = anyProcess(node.right);
            info.value = Math.max(left.value, right.value);
            info.value = (info.value + node.value > 0) ? info.value + node.value : node.value > 0 ? node.value : 0;
            info.max = Math.max(Math.max(left.max, right.max), info.value);
        }
        return info;
    }

    /**
     * 每次递归返回的值
     */
    private static class Info {
        int max;
        int value;
    }

    /**
     * 3)路径可以从任何结点出发，到任何结点，返回最大路径和
     *
     * @param head
     * @return
     */
    public static int maxPathSumFromAnyToAny(Node head) {
        return anyToAntProcess(head).max;
    }

    private static Info anyToAntProcess(Node node) {
        Info info = new Info();
        if (null != node) {
            Info left = anyToAntProcess(node.left);
            Info right = anyToAntProcess(node.right);
            int max = node.value + (left.value > 0 ? left.value : 0) + (right.value > 0 ? right.value : 0);
            info.max = Math.max(max, Math.max(left.max, right.max));
            info.value = node.value + Math.max(left.value, right.value);
            if (info.value < 0) info.value = 0;
        }
        return info;
    }

    private static class Node {
        public Node(Integer value, Node left, Node right, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(Integer value) {
            this.value = value;
        }

        public Integer value;
        public Node left;
        public Node right;
        public Node parent;

        public boolean isLeaf() {
            return left == null && right == null;
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-2);
        head.left.parent = head;
        head.right = new Node(-7);
        head.right.parent = head;
        head.left.left = new Node(3);
        head.left.left.parent = head.left;
        head.left.right = new Node(5);
        head.left.right.parent = head.left;
        head.right.left = new Node(-2);
        head.right.left.parent = head.right;
        head.right.right = new Node(4);
        head.right.right.parent = head.right;
        head.left.left.left = new Node(-2);
        head.left.left.left.parent = head.left.left;
        head.left.left.right = new Node(10);
        head.left.left.right.parent = head.left.left;
        System.out.println(maxPathSumFromHead2Leaf(head));
        System.out.println(maxPathSumFromAnyButGoingDown(head));
        System.out.println(maxPathSumFromAnyToAny(head));
    }


}



