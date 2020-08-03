package datastructure.tree;

import java.util.Stack;

/**
 * 二叉树非递归遍历
 */
@SuppressWarnings("all")
public class BinaryTreeUnRecursiveTraversal {
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
     * 先序遍历
     *
     * @param head
     */
    private static void pre(Node head) {
        if (null == head) {
            return;
        }
        Node node;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            node = stack.pop();
            System.out.print(node.value + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    private static void in(Node head) {
        if (null == head) {
            return;
        }
        Node node = head;
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.empty()) {
            node = stack.pop();
            System.out.print(node.value + " ");
            if (node.right != null) {
                node = node.right;
                stack.push(node);
                while (node.left != null) {
                    node = node.left;
                    stack.push(node);
                }
            }
        }
    }

    private static void in2(Node head) {
        System.out.println("in-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param head
     */
    private static void post(Node head) {
        System.out.println("post-order");
        if (head != null) {
            Stack<Node> stack = new Stack();
            stack.push(head);
            Node node = null;
            while (!stack.isEmpty()) {
                node = stack.peek();
                if (node.left != null && head != node.left && head != node.right) {
                    stack.push(node.left);
                } else if (node.right != null && head != node.right) {
                    stack.push(node.right);
                } else {
                    System.out.print(stack.pop().value+" ");
                    head = node;
                }
            }
        }

    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.left.right.left = new Node(9);
        head1.left.right.right = new Node(10);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);

        pre(head1);
        System.out.print("\n====================================\n");
        in(head1);
        System.out.print("\n====================================\n");
        in2(head1);
        System.out.print("\n====================================\n");
        post(head1);


    }
}
