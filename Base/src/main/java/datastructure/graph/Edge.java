package datastructure.graph;

import lombok.Data;

@Data

public class Edge {

    /**
     * 起点
     */
    private Node from;
    /**
     * 终点
     */
    private Node to;

    /**
     * 权重
     */
    private int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

