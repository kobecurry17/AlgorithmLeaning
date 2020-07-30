package algorithmvariant;

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
    @SuppressWarnings("all")
    public static boolean isPalindromeLinkedList3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return true;
        }
        Node fast = head.next;
        Node slow = head.next;
        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node n1 = slow;
        Node n2 = slow.next;
        Node mid = slow; // 中点
        Node n3;
        n1.next = null;
        n1 = null;
        // Reverse
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n2 = n1;
        n3 = head.next;
        boolean res = true;
        while (n1 != null && n3 != null) {
            if (res && n1.value != n3.value) {
                res = false;
            }
            n1 = n1.next;
            n3 = n3.next;
        }

        n1 = n2.next;
        n2.next = null;
        while (n1 != null) {
            n3 = n1.next;
            n1.next = n2;
            n2 = n1;
            n1 = n3;
        }
        mid.next = n1;
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
            throw new RuntimeException("error");
        }
        if (!(isPalindrome1(head1) == isPalindromeLinkedList2(head1) && isPalindrome1(head1) == isPalindromeLinkedList3(head1))) {
            throw new RuntimeException("error");
        }

        Node node13 = new Node(1);
        Node node14 = new Node(1);
        node13.next=node14;
        Node head2= new Node(0);
        head2.next=node13;
        if (!(isPalindrome1(head2) == isPalindromeLinkedList2(head2) && isPalindrome1(head2) == isPalindromeLinkedList3(head2))) {
            throw new RuntimeException("error");
        }
        if (!(isPalindrome1(head2) == isPalindromeLinkedList2(head2) && isPalindrome1(head2) == isPalindromeLinkedList3(head2))) {
            throw new RuntimeException("error");
        }


        Node node21 = new Node(1);
        Node node22 = new Node(3);
        node21.next=node22;
        Node head3= new Node(0);
        head3.next=node21;
        if (!(isPalindrome1(head3) == isPalindromeLinkedList2(head3) && isPalindrome1(head3) == isPalindromeLinkedList3(head3))) {
            throw new RuntimeException("error");
        }
        if (!(isPalindrome1(head3) == isPalindromeLinkedList2(head3) && isPalindrome1(head3) == isPalindromeLinkedList3(head3))) {
            throw new RuntimeException("error");
        }

        Node node31 = new Node(1);
        Node node32 = new Node(2);
        Node node33 = new Node(3);
        Node node34 = new Node(4);
        Node node35 = new Node(5);
        node31.next=node32;
        node32.next=node33;
        node33.next=node34;
        node34.next=node35;
        Node head5= new Node(0);
        head5.next=node31;
        if (!(isPalindrome1(head5) == isPalindromeLinkedList2(head5) && isPalindrome1(head5) == isPalindromeLinkedList3(head5))) {
            throw new RuntimeException("error");
        }
        if (!(isPalindrome1(head5) == isPalindromeLinkedList2(head5) && isPalindrome1(head5) == isPalindromeLinkedList3(head5))) {
            throw new RuntimeException("error");
        }


        Node node41 = new Node(1);
        Node node42 = new Node(2);
        Node node43 = new Node(3);
        Node node44 = new Node(2);
        Node node45 = new Node(1);
        node41.next=node42;
        node42.next=node43;
        node43.next=node44;
        node44.next=node45;
        Node head6= new Node(0);
        head6.next=node41;
        if (!(isPalindrome1(head6) == isPalindromeLinkedList2(head6) && isPalindrome1(head6) == isPalindromeLinkedList3(head6))) {
            throw new RuntimeException("error");
        }
        if (!(isPalindrome1(head6) == isPalindromeLinkedList2(head6) && isPalindrome1(head6) == isPalindromeLinkedList3(head6))) {
            throw new RuntimeException("error");
        }
    }
}
