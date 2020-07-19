package datastructure;

import lombok.Data;



@Data
public class DoublePointNode {
    private int value;
    private DoublePointNode next;
    private DoublePointNode pre;

    public DoublePointNode() {
    }

    public DoublePointNode(int value) {
        this.value = value;
    }

    public DoublePointNode(int value, DoublePointNode next, DoublePointNode pre) {
        this.value = value;
        this.next = next;
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "DoublePointNode  value=" + value +
                ", next=" + next +
                '}';
    }
}
