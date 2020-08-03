package datastructure.tree;

/**
 * 给定一个头结点，判断一颗树是不是完全二叉树
 */
public class CompleteBinaryTree {


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
     * 左右子树返回的信息
     */
    private static class Info {
        private boolean isFull;
        private int height;
        private int nodeSize;
    }

    public static boolean isCompleteBinaryTree(Node head) {
        Info process = process(head);
        if (null == process) {
            return false;
        }
        return process.isFull;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info info = new Info();
        Info left = process(head.left);
        Info right = process(head.right);
        // 左右子树都为空,叶子结点
        if (left == null && right == null) {
            info.nodeSize = 1;
            info.height = 1;
            info.isFull = true;
        }
        // 右树为空
        else if (left != null && right == null) {
            if (left.height == 1) {
                info.height = left.height + 1;
                info.isFull = true;
                info.nodeSize = left.nodeSize + 1;
            } else {
                left.isFull = false;
            }
        }
        // 左树为空
        else if (left == null && right != null) {
            info.isFull = false;
        }
        // 左右子树都不为空
        else {

            if (left.isFull && right.isFull) {
                if ((right.height == 1 || right.nodeSize == Math.pow(2, right.height) - 1) && left.height >= right.height) {
                    info.nodeSize = left.nodeSize + right.nodeSize + 1;
                    info.isFull = true;
                    info.height = left.height + 1;
                } else {
                    info.isFull = false;
                }
            } else {
                info.isFull = false;
            }
        }
        return info;
    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(9);
        head1.left.left.right = new Node(10);

        System.out.println(isCompleteBinaryTree(head1));


        head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.right.left = new Node(9);
        head1.left.right.right = new Node(10);

        System.out.println(isCompleteBinaryTree(head1));
    }
}
