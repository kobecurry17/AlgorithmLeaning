package datastructure;

import lombok.Data;

@Data
public class Node {

    private int value;
    private Node next;

    public Node() {
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
