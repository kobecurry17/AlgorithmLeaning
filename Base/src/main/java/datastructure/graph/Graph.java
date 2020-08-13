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
     * 深度优先遍历
     */
    public void depthFirst(Graph graph, Node node) {
        if (node == null && node.getOut() == 0) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        HashSet<Node> nodeS = new HashSet<>();
        System.out.println(node.getValue());
        while (!stack.isEmpty()) {
            if (node.getOut() == 0) {
                continue;
            }
            boolean flag = false;





        }
    }


    /**
     * 宽度优先遍历
     */
    public void widthFirst(Node node) {
        Queue<Node> queue = new LinkedList<>();


    }

    public static void main(String[] args) {

    }

}
