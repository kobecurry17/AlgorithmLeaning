package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 二叉树宽度优先遍历
 */
public class BinaryTreeBreadthFirst {

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
     * 宽度优先遍历
     *
     * @param head
     */
    public static void BreadthFirst(Node head) {
        if (null == head) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(null!=node.left){
                queue.add(node.left);
            }
            if(null!=node.right){
                queue.add(node.right);
            }
            System.out.print(node.value+" ");
        }
    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.left.right.left = new Node(8);
        head1.left.right.right = new Node(9);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        BreadthFirst(head1);
    }

}


