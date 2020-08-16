package datastructure.graph;

import lombok.Data;

import java.util.*;

@Data

public class Graph {

    /**
     * 点集
     */
    public HashMap<Integer, Node> nodes;
    /**
     * 变集
     */
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    /**
     * 宽度优先遍历
     */
    public void widthFirst(Node node) {
        Queue<Node> queue = new LinkedList<>();


    }

    public boolean isEmpty(){
        return null==nodes||nodes.size()==0;
    }
}
