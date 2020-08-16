package graph;

import datastructure.graph.Edge;
import datastructure.graph.GenerateGraph;
import datastructure.graph.Graph;
import datastructure.graph.Node;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 优化堆结构，使用堆返回最小下一个结点返回即可
 */
public class Dijkstra2 {

    public static Map<Node, Integer> dijkstra2(Node head, int size) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        if (null == head || head.getNexts().isEmpty()) {
            return distanceMap;
        }
        Heap heap = new Heap(size);
        heap.insertOrUpdateOrIgnore(head, 0);
        while (!heap.isEmpty()) {
            Record record = heap.pop();
            for (Edge edge : record.node.getEdges()) {
                heap.insertOrUpdateOrIgnore(edge.getTo(), record.getDistance() + edge.getWeight());
            }
            distanceMap.put(record.node, record.distance);
        }
        return distanceMap;
    }


    public static class Heap {
        // 实际的堆结构
        private Node[] nodes;
        // 最小距离的map
        private Map<Node, Integer> distanceMap;
        // 每个点对应的下标
        private Map<Node, Integer> indexMap;
        int size;

        public Heap(int size) {
            this.nodes = new Node[size];
            this.distanceMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        public void insertOrUpdateOrIgnore(Node node, Integer distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(indexMap.get(node));
            }
            if (!isEntry(node)) {
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }

        /**
         * 插入时调整堆
         */
        private void insertHeapify(Integer index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 调整堆
         */
        public void heapify() {
            int index = 0;
            int left = 0 << 1 | 1;
            while (left < size) {
                int smallest = left + 1 < size ? distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ? left : left + 1 : left;
                if (index == smallest) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = left << 1 | 1;
            }
        }

        /**
         * 在堆里？
         *
         * @param node
         * @return
         */
        public boolean inHeap(Node node) {
            return isEntry(node) && indexMap.get(node) != -1;
        }

        /**
         * 是否有效
         * 当一个结点计算过后，index被置为-1
         * 因此当index为-1时认为这个结点无效
         *
         * @param node
         * @return
         */
        public boolean isEntry(Node node) {
            return indexMap.containsKey(node);
        }

        /**
         * 交换 a 位置和 b 位置处的结点
         *
         * @param a
         * @param b
         */
        public void swap(Integer a, Integer b) {
            indexMap.put(nodes[a], b);
            indexMap.put(nodes[b], a);
            Node tmp = nodes[a];
            nodes[a] = nodes[b];
            nodes[b] = tmp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 弹出最小值
         *
         * @return
         */
        public Record pop() {
            Node node = nodes[0];
            swap(0, size - 1);
            int distance = distanceMap.get(node);
            indexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[--size] = null;
            heapify();
            return new Record(node, distance);
        }
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
        System.out.println(dijkstra2(generate.getNodes().get(1), generate.getNodes().size()));
    }
}
