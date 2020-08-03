package datastructure;

/**
 * 给定两个可能有环也可能无环的单链表，头结点head1和head2.
 * 请实现一个函数，如果两个链表相交返回第一个结点
 * 如果不相交返回null
 */
@SuppressWarnings("all")
public class MaybeCycleLinkedList {


    /**
     * 如何判断链表有环：
     * F 初始在head.next.next
     * S 初是在hea.next
     * F一次走两步，S一次走一步，当他们第一次相遇的时候，他们就是有环。
     * 此时F回到head.next 一次走一步，S也一次走一步 再次相遇即为环的起点。
     */
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 寻找两个链表的第一次相遇
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (null == head1 || null == head2) {
            return null;
        }
        Node n1 = isCycle(head1);
        Node n2 = isCycle(head2);
        // 只存在两个同时为循环链表和两个同时不为循环链表
        if (null == n1 && null == n2) {
            return noLoop(head1, head2, null);
        } else if (null != n1 && null != n2) {
            if (n1 == n2) {
                return noLoop(head1, head2, n1);
            } else {
                return doubleLoop(head1, head2, n1, n2);
            }
        }
        return null;
    }

    private static Node doubleLoop(Node head1, Node head2, Node n1, Node n2) {
        Node node = n1.next;
        while (node != n1) {
            if (node == n2) {
                return n1;
            }
            node = node.next;
        }
        return null;
    }


    /**
     * 寻找共同起点：终点为res
     *
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2, Node res) {
        Node n1 = head1;
        Node n2 = head2;

        // 优化为 n　此处只需要记录差值
        int n = 0;
        while (n1 != res) {
            n++;
            n1 = n1.next;
        }
        while (n2 != res) {
            n--;
            n2 = n2.next;
        }
        if (n1 != n2) {
            return null;
        }
        n1 = n > 0 ? head1 : head2;
        n2 = n1 == head1 ? head2 : head1;
        n = Math.abs(n);

        while (n > 0) {
            n--;
            n1 = n1.next;
        }
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    private static Node isCycle(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node fast = head.next;
        Node slow = head;
        boolean flag = false;
        while (fast != null && fast.next != null && !flag) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                flag = true;
            }
        }
        Node res = null;
        fast = head;
        slow=slow.next;
        if (flag) {
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            res = fast;
        }
        return res;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
