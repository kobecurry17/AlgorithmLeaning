package algorithmvariant;

import datastructure.tree.BinaryTreeUnRecursiveTraversal;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边想上方对折一次。
 * 压出折痕后展开。此时折痕是凹下去的，即折痕凸起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折两次，压出折痕后展开，此时有三条折痕，从上到下依次是：
 * 下折痕，下折痕和上折痕
 * <p>
 * 题目：给定一个输入参数N，代表纸条从下边向上方连续对折N次。请从下到上打印
 * 所有折痕的方向。
 * 例如:
 * N=1时，打印：down
 * N=2时，打印：down down up
 */
public class PaperFolding {

    /**
     * 二叉树结点
     */
    public static class Node {

        private String value;
        private Node left;
        private Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    /**
     * 打印函数
     *
     * @param n
     */
    public static void print(int n) {
        if (n < 1) {
            return;
        }
        Node head = new Node("down ");
        generatorTree(head, n-1);

        pre(head);

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
        pre(head.left);
        System.out.print(head.value);
        pre(head.right);
    }

    /**
     * 生成对应的树
     */
    public static void generatorTree(Node head, int level) {
        if (level < 1) {
            return;
        }
        head.left = new Node("down ");
        head.right = new Node("up ");
        generatorTree(head.left, level - 1);
        generatorTree(head.right, level - 1);
    }

    public static void main(String[] args) {
        print(1);
        System.out.println("\n=======================================");
        print(2);
        System.out.println("\n=======================================");
        print(3);
        System.out.println("\n=======================================");
    }

}
