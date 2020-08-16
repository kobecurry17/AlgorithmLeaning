package graph;


import datastructure.graph.Edge;
import datastructure.graph.GenerateGraph;
import datastructure.graph.Graph;
import datastructure.graph.Node;
import lombok.Data;

import java.util.*;

/**
 * 权重无负值的情况下最短路径算法
 */
@SuppressWarnings("all")
public class Dijkstra1 {

    /**
     * 在一张权重图中，得到一个点到各点的最短距离
     *
     * @param from
     */
    public static Map<Node, Integer> dijkstra(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        if (null == from || from.getNexts().isEmpty()) {
            return distanceMap;
        }
        Set<Node> selectSet = new HashSet<>();
        distanceMap.put(from, 0);
        Record minNode = new Record(from,0);
        while (!minNode.isNull()) {
            for (Edge edge : minNode.node.edges) {
                if (edge.getFrom() == minNode.node) {
                    Integer distance = distanceMap.get(edge.getTo());
                    int distance2 = edge.getWeight() + minNode.distance;
                    if (null == distance || distance2 < distance) {
                        distanceMap.put(edge.getTo(), distance2);
                    }
                }
            }
            selectSet.add(minNode.node);
            minNode = getMinDistanceAndIgnore(distanceMap, selectSet);
        }
        return distanceMap;
    }

    private static Record getMinDistanceAndIgnore(Map<Node, Integer> distanceMap, Set<Node> selectSet) {
        Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectSet.contains(entry.getKey()) && entry.getValue() < min) {
                minNode = entry.getKey();
                min = entry.getValue();
            }
        }
        return new Record(minNode, min);
    }

    /**
     * 边记录实体
     */
    @Data
    private static class Record {
        private Node node;
        private Integer distance;

        public Record(Node node, Integer distance) {
            this.node = node;
            this.distance = distance;
        }

        boolean isNull() {
            return null == node;
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[11][3];
        arr[0][0] = 2;
        arr[0][1] = 1;
        arr[0][2] = 2;

        arr[1][0] = 3;
        arr[1][1] = 2;
        arr[1][2] = 3;

        arr[2][0] = 10;
        arr[2][1] = 1;
        arr[2][2] = 4;

        arr[3][0] = 5;
        arr[3][1] = 1;
        arr[3][2] = 6;

        arr[4][0] = 1;
        arr[4][1] = 6;
        arr[4][2] = 4;

        arr[5][0] = 4;
        arr[5][1] = 2;
        arr[5][2] = 4;

        arr[6][0] = 1;
        arr[6][1] = 4;
        arr[6][2] = 3;

        arr[7][0] = 1;
        arr[7][1] = 4;
        arr[7][2] = 6;

        arr[8][0] = 2;
        arr[8][1] = 4;
        arr[8][2] = 5;

        arr[9][0] = 1;
        arr[9][1] = 5;
        arr[9][2] = 3;

        arr[10][0] = 1;
        arr[10][1] = 6;
        arr[10][2] = 5;
        Graph generate = GenerateGraph.generate(arr);
        Map<Node, Integer> dijkstra = dijkstra(generate.getNodes().get(1));
        System.out.println(dijkstra);
    }


}
