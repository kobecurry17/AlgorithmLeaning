package DataStructure;

public class RevertList {

    /**
     * 单向链表反向
     *
     * @param head
     * @return
     */
    static Node LinkedListRevert(Node head) {
        Node pre = null;
        Node next = null;
        while (null != head) {

            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

    static DoublePointNode DoublePointRevert(DoublePointNode head) {

        DoublePointNode pre = null;
        DoublePointNode next = null;

        while (null != head) {
            next = head.getNext();
            head.setNext(pre);
            head.setPre(next);
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoublePointNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1) + len);
        if (size == 0) {
            return null;
        }
        size--;
        DoublePointNode head = new DoublePointNode((int) (Math.random() * (value + 1)));
        DoublePointNode pre = head;
        while (size != 0) {
            DoublePointNode cur = new DoublePointNode((int) (Math.random() * (value + 1)));
            pre.setNext(cur);
            cur.setPre(pre);
            pre = cur;
            size--;
        }
        return head;
    }

    static void testRevertList() {
        Node head = new Node(1, new Node(2, new Node(3, null)));
        System.out.println(head);
        Node node = LinkedListRevert(head);
        System.out.println(node);
    }

    static void testDoublePointRevertList() {
        DoublePointNode head = generateRandomDoubleList(20, 100);
        System.out.println(head);
        System.out.println("==================================");
        DoublePointNode newHead = DoublePointRevert(head);
        System.out.println(newHead);
    }

    public static void main(String[] args) {
        testRevertList();

        testDoublePointRevertList();
    }

}
