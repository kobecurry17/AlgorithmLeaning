package datastructure.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大宽度
 */
public class MaxWidth {

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
     * 通过map实现
     *
     * @param head
     * @return
     */
    public static int findByMap(Node head) {
        if (head == null) {
            return 0;
        }
        HashMap<Node, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前所在层
        Integer curLevel = 1;
        // 当前层宽度
        Integer curWidth = 0;
        // 最大宽度
        Integer maxWidth = 1;
        map.put(head, curLevel);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (map.get(head) != curLevel) {
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 1;
                curLevel++;
            } else {
                curWidth++;
            }
            if (head.left != null) {
                map.put(head.left, curLevel + 1);
                queue.add(head.left);
            }
            if (head.right != null) {
                map.put(head.right, curLevel + 1);
                queue.add(head.right);
            }
        }
        maxWidth=Math.max(maxWidth,curWidth);
        return maxWidth;
    }

    /**
     * 不使用map
     *
     * @param head
     * @return
     */
    private static int findByNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        // 问题的关键在于怎么知道当前层结束了
        // 保存下当前层最后一个结点
        Node end = null;
        Node lastEnd = head;
        Integer maxWidth = 0;
        Integer curWidth = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            curWidth++;
            if (head.left != null) {
                end = head.left;
                queue.add(head.left);
            }
            if (head.right != null) {
                end = head.right;
                queue.add(head.right);
            }
            if (head == lastEnd) {
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
                lastEnd = end;
            }
        }

        return maxWidth;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }


    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (findByMap(head) != findByNoMap(head)) {
                System.out.println("findByMap(head)="+findByMap(head)+",findByNoMap(head))="+findByNoMap(head));
            }
        }
        System.out.println("finish!");

//        Node head1 = new Node(1);
//        head1.left = new Node(2);
//        head1.left.left = new Node(4);
//        head1.left.right = new Node(5);
//        head1.left.right.left = new Node(8);
//        head1.left.right.right = new Node(9);
//        head1.left.left.left= new Node(10);
//        System.out.println("findByMap(head)=" + findByMap(head1) + ",findByNoMap(head))=" + findByNoMap(head1));


    }

}
