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
     * 需要从左右孩子得到的信息
     */
    private static class Info {
        private boolean isFull;
        private int height;
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
            return false;
        }

        return process(head).isFull;

    }


    private static Info process(Node head) {
        Info info = new Info();
        if (null == head) {
            info.isFull = true;
            info.height = 0;
            return info;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        if (null == left && null == right) {
            info.isFull = true;
            info.height = 1;
        } else if (left.isFull && right.isFull && left.height == right.height) {
            info.height = left.height + 1;
            info.isFull = true;
        } else {
            info.isFull = false;
        }
        return info;
    }


    // for test
    public static Node generateBTS(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.1f) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxValue, maxValue);
        node.right = generate(level + 1, maxValue, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int loopSize = 50_0000;
        int maxValue = 500;
        int maxLevel = 5;
        for (int i = 0; i < loopSize; i++) {
            Node head = generateBTS(maxLevel, maxValue);
            if (isFull(head) != isFull2(head)) {
                System.out.println("Oops");
            }
        }
        System.out.println("nice");
    }


}
