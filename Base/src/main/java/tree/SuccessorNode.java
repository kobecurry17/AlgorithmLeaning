package tree;

/**
 * 寻找结点的后继结点
 * 即一棵树中序遍历的下一个结点
 */
public class SuccessorNode {
    /**
     * 二叉树结点
     */
    public static class Node {
        private int value;
        private Node left;
        private Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 寻找后继节点
     *
     * @param n
     * @return
     */
    private static Node findSuccessorNode(Node n) {
        if (null == n) {
            return null;
        }
        if (n.right != null) {
            return mostLeft(n.right);
        } else {
            Node c = n.parent;
            while (c != null && n == c.right) {
                n = c;
                c = c.parent;
            }
            return c;
        }
    }

    /**
     * 寻找最左结点
     *
     * @param n
     * @return
     */
    public static Node mostLeft(Node n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + findSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + findSuccessorNode(test));
    }

}
