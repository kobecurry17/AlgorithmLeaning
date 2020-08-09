package tree.serialization;

import tree.CompleteBinaryTree;
import tree.LowestParent;

import java.util.*;

/**
 * 仅使用先序遍历进行反序列化
 */
public class PreSerialization {

    /**
     * 序列化
     *
     * @param head
     * @return
     */
    public static Queue<Integer> serialization(Node head) {
        if (null == head) {
            return null;
        }
        Queue<Integer> list = new LinkedList<>();
        process(head, list);
        return list;
    }

    /**
     * 反序列化
     *
     * @param queue
     * @return
     */
    public static Node deserialization(Queue<Integer> queue) {
        if (null == queue || queue.size() == 0) {
            return null;
        }
        Stack<Integer> stack=new Stack<>();
        while (!queue.isEmpty()){
            stack.add(queue.poll());
        }
        return process2(null, stack);
    }

    private static Node process2(Node node, Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        Integer value = stack.pop();
        if (null == value) {
            return null;
        }
        node = new Node(value);
        node.right = process2(node, stack);
        node.left = process2(node, stack);
        return node;
    }


    private static void process(Node head, Queue<Integer> list) {
        if (null == head) {
            list.add(null);
            return;
        }
        process(head.left, list);
        process(head.right, list);
        list.add(head.value);
    }


    public static void main(String[] args) {

        int loops = 50_0000;
        int max = 15;
        int maxValue = 500;
        for (int i = 0; i < loops; i++) {
            Node head = generateBST((int) (max * Math.random()), (int) (maxValue * Math.random()));
            Queue<Integer> serialization1 = serialization(head);
            Queue<Integer> serialization2 = serialization(deserialization(serialization(head)));
            if(serialization1==serialization2){
                continue;
            }
            while ((serialization1!=serialization2&&!serialization1.isEmpty() && !serialization2.isEmpty())) {
                Integer poll1 = serialization1.poll();
                Integer poll2 = serialization2.poll();
                if (poll1 != poll2 && !poll1.equals(poll2)) {
                    System.out.println("Oops");
                }
            }
        }
        System.out.println("Nice");
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
        return generate(1, maxLevel, maxValue);
    }

    /**
     * 随机生成结点
     *
     * @param level
     * @param maxLevel
     * @param maxValue
     * @return
     */
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.1) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxLevel, maxValue);
        node.right = generate(level + 1, maxLevel, maxValue);
        return node;
    }
}
