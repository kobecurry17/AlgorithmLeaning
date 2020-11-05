package InterviewStage1.class6;

/**
 * 求完全二叉树结点的个数
 * 要求时间复杂度低于O(N)
 */
public class CompleteTreeSize {


    public static int CompleteTreeSize(Node head) {
        return process(head);
    }

    private static int process(Node head) {
        if (null == head) {
            return 0;
        }
        // 自己算1
        int size = 1;
        int height = completeTreeHeight(head);
        int rightHeight = completeTreeHeight(head.right);
        size += rightHeight == 0 ? 0 : Math.pow(2, rightHeight) - 1;
        // 左子树满树
        if (rightHeight == height - 1) {
            size += process(head.right);
        }
        // 右子树满树
        else {
            size += process(head.left);
        }
        return size;
    }


    /**
     * 完全二叉树数的高度
     *
     * @param head
     * @return
     */
    private static int completeTreeHeight(Node head) {
        if (null == head) {
            return 0;
        }
        Node cur = head;
        int height = 0;
        while (cur != null) {
            height++;
            cur = cur.left;
        }
        return height;
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
        Node head = new Node(1);
        System.out.println(CompleteTreeSize(head));
        head.left = new Node(-2);
        System.out.println(CompleteTreeSize(head));
        head.right = new Node(-7);
        System.out.println(CompleteTreeSize(head));
        head.left.left = new Node(3);
        System.out.println(CompleteTreeSize(head));
        head.left.right = new Node(5);
        System.out.println(CompleteTreeSize(head));
        head.right.left = new Node(-2);
        System.out.println(CompleteTreeSize(head));
        head.right.right = new Node(4);
        System.out.println(CompleteTreeSize(head));
        head.left.left.left = new Node(-2);
        System.out.println(CompleteTreeSize(head));
        head.left.left.right = new Node(10);
        System.out.println(CompleteTreeSize(head));
    }
}
