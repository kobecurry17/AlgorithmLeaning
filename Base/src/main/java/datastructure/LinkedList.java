package datastructure;

import lombok.Data;

@Data
public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    // 输入链表头结点，奇数返回中点，偶数返回上中点
    public Node<Integer> fastSlowNode1(Node head) {
        if (size < 3) {
            return head.next;
        }
        Node fast = head.next;
        Node slow = head.next;
        int index = 1;
        while ((index += 2) <= size) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 输入链表头结点，奇数返回中点，偶数返回上中点
    public int fastSlowNode1(int[] arr) {
        return arr[(arr.length - 1) / 2];
    }


    // 输入链表头结点，奇数返回中点，偶数返回下中点
    public Node<Integer> fastSlowNode2(Node head) {
        if (size == 2) {
            return head.next;
        }
        if (size == 3) {
            return head.next.next;
        }
        Node fast = head;
        Node slow = head.next;
        int index = 0;
        while ((index += 2) <= size) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 输入链表头结点，奇数返回中点，偶数返回下中点
    public int fastSlowNode2(int[] arr) {
        if(arr.length<3){
            return arr[arr.length-1];
        }
        return arr[(arr.length/2+1)];
    }


    // test
    public Node<Integer> generateLinkedList(int arr[]) {
        Node<Integer> node = new Node();
        head = node;
        for (int i = 0; i < arr.length; i++) {
            node.next = new Node(arr[i]);
            node = node.next;
            tail = node;
            size++;
        }
        return head;
    }


    @Data
    public static class Node<T> {
        private T t;
        private Node next;

        public Node() {
        }

        public Node(T t) {
            this.t = t;
        }
    }

    public static void main(String[] args) {
        int value = 500;
        int maxSize = 50;
        int loopSize = 50_0000;

        for (int i = 0; i < loopSize; i++) {
            int size = (int) (Math.random() * maxSize) + 1;
            int[] arr = new int[size];
            LinkedList linkedList = new LinkedList();
            for (int j = 0; j < size; j++) {
                arr[j] = (int) (Math.random() * value);
            }
            if (!linkedList.fastSlowNode1(linkedList.generateLinkedList(arr)).getT().equals(linkedList.fastSlowNode1(arr))) {
                throw new RuntimeException("error");
            }
        }
        System.out.println("Nice1");

        for (int i = 0; i < loopSize; i++) {
            int size = (int) (Math.random() * maxSize) + 1;
            int[] arr = new int[size];
            LinkedList linkedList = new LinkedList();
            for (int j = 0; j < size; j++) {
                arr[j] = (int) (Math.random() * value);
            }
            if (!linkedList.fastSlowNode2(linkedList.generateLinkedList(arr)).getT().equals(linkedList.fastSlowNode2(arr))) {
                throw new RuntimeException("error");
            }
        }
        System.out.println("Nice2");


    }

}
