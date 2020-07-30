package algorithmvariant;

import lombok.Data;

import java.util.Stack;

/**
 * 给定一个链表，判断该链表是否为回文结构
 */
public class PalindromeLinedList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * O(N) 额外空间复杂度
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        head = head.next;
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * O(N) 额外空间复杂度
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeLinkedList2(Node head) {
        if (null == head || head.next == null) {
            return true;
        }

        Node fast = head.next;
        Node slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow.next != null) {
            slow = slow.next;
            stack.push(slow);
        }

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop.value != head.next.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * O(1) 额外空间复杂度
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeLinkedList3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head.next;
        Node slow = head.next;
        Node mid;
        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node n1 = slow;
        Node n2 = slow.next;
        Node n3;
        // Reverse
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = head.next;
        boolean res = true;
        while (n1 != null && n3 != null) {
            if (res && n1.value != n3.value) {
                res = false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        n1 = n2.next;
        n2.next = null;
        while (n1 != null) {
            n3 = n1.next;
            n1.next = n2;
            n2 = n1;
            n1 = n3;
        }
        return res;
    }


    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(1);
        Node head1 = new Node(0);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        if (!(isPalindrome1(head1) == isPalindromeLinkedList2(head1) && isPalindrome1(head1) == isPalindromeLinkedList3(head1))) {
            System.out.println("error");
        }


    }
}
