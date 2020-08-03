package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是不是满二叉树
 */
public class FullBinaryTree {
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


    /**
     * 需要从左右孩子得到的信息(优化版)
     */
    private static class Info {
        private int height;
        private int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }

        public Info() {
        }
    }

    /**
     * 暴力判断是否是满二叉树
     *
     * @param head
     * @return
     */
    private static boolean isFull2(Node head) {
        if (null == head) {
            return false;
        }
        int height = 0;
        int nodeSize = 0;
        Node lastEnd = head;
        Node n = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            nodeSize++;
            if (head.left != null) {
                queue.add(head.left);
                n = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                n = head.right;
            }
            if (head == lastEnd) {
                lastEnd = n;
                height++;
            }
        }
        if (nodeSize == Math.pow(2, height) - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 递归判断一颗树是否是满二叉树
     *
     * @param head
     * @return
     */
    public static boolean isFull(Node head) {
        if (null == head) {
            return true;
        }
        Info process = process(head);
        return Math.pow(process.height, 2) - 1 == process.nodes;

    }


    private static Info process(Node head) {
        if (null == head) {
            return new Info(0,0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        Info info =new Info();
        info.height=Math.max(left.height,right.height)+1;
        info.nodes=left.nodes+right.nodes+1;
        return info;
    }

    private static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    private static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right);
    }

    private static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.right), h(head.left)) + 1;
    }


    // for test
    public static Node generateBTS(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.4f) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxValue, maxValue);
        node.right = generate(level + 1, maxValue, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int loopSize = 100_0000;
        int maxValue = 500;
        int maxLevel = 5;
        for (int i = 0; i < loopSize; i++) {
            Node head = generateBTS(maxLevel, maxValue);
            if (isFull(head) != isFull1(head)) {
                System.out.println("Oops");
            }
        }
        System.out.println("nice");
    }


}
