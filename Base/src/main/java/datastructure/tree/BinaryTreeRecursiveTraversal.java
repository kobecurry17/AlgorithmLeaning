package datastructure.tree;

/**
 * 二叉树递归遍历
 */
@SuppressWarnings("all")
public class BinaryTreeRecursiveTraversal {
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
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    private static void mid(Node head) {
        if (null == head) {
            return;
        }
        mid(head.left);
        System.out.print(head.value + " ");
        mid(head.right);
    }

    /**
     * 后序遍历
     *
     * @param head
     */
    private static void post(Node head) {
        if (null == head) {
            return;
        }
        post(head.left);
        post(head.right);
        System.out.print(head.value + " ");
    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.left.right.left=new Node(9);
        head1.left.right.right=new Node(10);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);

        pre(head1);
        System.out.print("\n====================================\n");
        mid(head1);
        System.out.print("\n====================================\n");
        post(head1);
    }


}


