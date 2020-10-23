package class6;

/**
 * <p>
 * 双向链表结点结构和二叉树节点结构是一样的
 * 如果你把last认为是left,next认为是right
 * 给定一个搜索二叉树的头结点head,请转化成一条有序的双向链表
 * 并返回链表的头结点
 * TODO:有啥用？
 * </p>
 */
public class LinedListCastBinaryTree {


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

//    public static TreeNode cast(ListNode head) {
//        if (null == head) {
//            return null;
//        }
//        TreeNode ans = new TreeNode(head.value);
//        TreeNode cur = ans;
//        TreeNode pre = ans;
//
//    }
