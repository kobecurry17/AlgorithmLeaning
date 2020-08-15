package datastructure.graph;

import org.w3c.dom.NamedNodeMap;

import java.util.HashSet;

/**
 * 特定结构生成图
 */
public class GenerateGraph {


    /**
     * 给定一个矩阵
     * matrix 所有的边
     * N*3 的矩阵
     * [weight, from节点上面的值，to节点上面的值]
     *
     * @param matrix
     * @return
     */
    public Graph generate(int[][] matrix) {
        if (null == matrix) {
            return null;
        }
        HashSet<Integer> set;
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(matrix[i][1]));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(matrix[i][2]));
            }
            Node fromNode = graph.getNodes().get(from);
            Node toNode = graph.getNodes().get(to);
            Edge edge = new Edge(fromNode, toNode, matrix[i][0]);
            graph.edges.add(edge);
            fromNode.setOut(fromNode.getOut() + 1);
            toNode.setIn(toNode.getIn() + 1);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            toNode.edges.add(edge);
        }
        return graph;
    }
}
