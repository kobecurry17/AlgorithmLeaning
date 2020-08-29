package algorithmvariant;

import java.util.HashMap;

/**
 * 深拷贝以下结构
 * public static  class Node{
 * int value;
 * Node next; 下一个结点
 * Node rand; 随机结点
 * }
 * publci static
 */
public class DeepClone {

    public static class Node {
        int value;
        Node next;
        Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node clone1(Node head) {
        HashMap<Node, Node> map = new HashMap();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = cur.next;
            map.get(cur).rand = cur.rand;
            cur = cur.next;
        }
        return map.get(head);
    }

    @SuppressWarnings("all")
    public static Node clone2(Node head) {
        if(head==null){
            return null;
        }
        Node cur = head;
        Node n;
        Node res;
        while (cur != null) {
            n = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = n;
            cur = n;
        }
        cur = head;
        res = head.next;
        while (cur != null) {
            n = cur.next;
            cur.next = n.next;
            cur.next.rand = cur.rand.next;
            cur =cur.next;
            cur.next=cur.next;
        }
        return res;
    }


    public static void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        while (cur != null) {
            System.out.print(cur.rand == null ? "-" : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        print(head);
        res1 = clone1(head);
        res2 = clone2(head);
        print(res1);
        print(res2);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        res1 = clone1(head);
        res2 = clone2(head);
        print(head);
        print(res1);
        print(res2);
        System.out.println("=========================");

    }

}
