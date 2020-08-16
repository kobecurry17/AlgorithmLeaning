package datastructure.graph;

import lombok.Data;

import java.util.ArrayList;

@SuppressWarnings("all")
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

    public ArrayList<Node> nexts;

    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();

    }

    @Override
    public String toString() {
        return value+"";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public ArrayList<Node> getNexts() {
        return nexts;
    }

    public void setNexts(ArrayList<Node> nexts) {
        this.nexts = nexts;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
