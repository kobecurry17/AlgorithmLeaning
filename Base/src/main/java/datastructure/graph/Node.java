package datastructure.graph;

import lombok.Data;

@Data
public class Node {

    /**
     * 结点名
     */
    private int value;

    /**
     * 入度(指向他的结点个数)
     */
    private int in;

    /**
     * 出度(他指向别人的个数)
     */
    private int out;

    public Node(int value) {
        this.value = value;
    }
}
