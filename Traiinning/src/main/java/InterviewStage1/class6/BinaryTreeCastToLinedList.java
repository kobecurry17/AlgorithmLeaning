package InterviewStage1.class6;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 双向链表结点结构和二叉树节点结构是一样的
 * 如果你把last认为是left,next认为是right
 * 给定一个搜索二叉树的头结点head,请转化成一条有序的双向链表
 * 并返回链表的头结点
 * TODO:看视频复习！
 * </p>
 */
public class BinaryTreeCastToLinedList {


    public static ListNode convert(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        add(queue, head);


        return null;
    }

    private static void add(Queue<TreeNode> queue, TreeNode node) {
        if (node == null) {
            return;
        }
        queue.add(node);
        add(queue, node.left);
        add(queue, node.right);
    }


    public static class ListNode {
        int value;
        ListNode last;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


}

