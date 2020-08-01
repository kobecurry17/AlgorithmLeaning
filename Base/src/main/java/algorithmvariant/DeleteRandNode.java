package algorithmvariant;


/**
 * 在链表中，给定任一一个结点把自己删除
 */
@SuppressWarnings("all")
public class DeleteRandNode {

    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "->" + next;
        }
    }

    /**
     * 给定任一一个结点，把他删除
     * 对于链表中存在的对象构造方法复杂，将无法做到
     * 对于最后一个结点无法删除
     *
     * @param node
     */
    public static void deleteRand(Node node) {
        if (node == null || node.next == null) {
            System.out.println("空节点和尾结点无法删除");
        }
        Node pre = node;
        while (node.next != null) {
            node.value = node.next.value;
            pre = node;
            node = node.next;

        }
        pre.next = null;
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
        System.out.println(head1);

        deleteRand(head1.next);
        System.out.println(head1);

    }
}
