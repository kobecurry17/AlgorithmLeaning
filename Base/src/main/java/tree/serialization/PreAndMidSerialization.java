package tree.serialization;

import java.util.*;

/**
 * 使用先序和中序进行二叉树的反序列化(未完成)
 */
public class PreAndMidSerialization {

    /**
     * 先序遍历
     *
     * @param head
     * @param queue
     */
    public static void pre(Node head, Queue<Integer> queue) {
        if (null == head) {
            return;
        }
        queue.add(head.value);
        pre(head.left, queue);
        pre(head.right, queue);
    }

    /**
     * 中序遍历
     *
     * @param head
     * @param list
     */
    public static void mid(Node head, List<Integer> list) {
        if (null == head) {
            return;
        }
        mid(head.left, list);
        list.add(head.value);
        mid(head.right, list);
    }


    public static void main(String[] args) {
        Node head = generateBST(3, 500);
        Queue<Integer> pre = new LinkedList<>();
        List<Integer> mid = new ArrayList();
        pre(head, pre);
        mid(head, mid);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mid.size(); i++) {
            map.put(mid.get(i), i);
        }
        Node newHead = deserialization(pre, map);
    }

    private static Node deserialization(Queue<Integer> pre, Map<Integer, Integer> mid) {
        if (null == pre || pre.isEmpty()) {
            return null;
        }
        return process(null, pre, mid);
    }

    private static Node process(Node head, Queue<Integer> pre, Map<Integer, Integer> mid) {
        if (pre.isEmpty()) {
            return null;
        }
        Integer poll = pre.poll();
        Node node = new Node(poll);
        Integer now = mid.get(poll);
        Integer index = mid.get(pre.peek());
        if (null == index) {
            return node;
        }
        if (index < now) {
            node.left = process(node, pre, mid);
        } else {
            node.right = process(node, pre, mid);
        }
        return node;
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

        public Node() {
        }
    }

    /**
     * 生成一颗最大高度为maxLevel,最大值为maxValue的树
     *
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateBST(int maxLevel, int maxValue) {
        HashSet<Integer> set = new HashSet();
        return generate(1, maxLevel, maxValue, set);
    }

    /**
     * 随机生成结点
     *
     * @param level
     * @param maxLevel
     * @param maxValue
     * @param set
     * @return
     */
    private static Node generate(int level, int maxLevel, int maxValue, HashSet<Integer> set) {
        if (level > maxLevel || Math.random() < 0.1) {
            return null;
        }
        int value;
        do {
            value = (int) (Math.random() * maxValue);
        } while (set.contains(value));
        set.add(value);
        Node node = new Node(value);
        node.left = generate(level + 1, maxLevel, maxValue, set);
        node.right = generate(level + 1, maxLevel, maxValue, set);
        return node;
    }

}

