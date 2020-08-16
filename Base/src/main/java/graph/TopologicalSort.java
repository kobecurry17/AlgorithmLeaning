package graph;

import datastructure.graph.GenerateGraph;
import datastructure.graph.Graph;
import datastructure.graph.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的拓扑排序
 * 在有向切无环的图中
 * 依次删除入度为 0 的点,周而复始
 * 删除的顺序即为拓扑排序的顺序
 */
@SuppressWarnings("all")
public class TopologicalSort {

    /**
     * 拓扑排序
     *
     * @param graph
     */
    public static void topologicalSort(Graph graph) {
        if (null == graph || graph.isEmpty()) {
            return;
        }
        Set<Integer> keys = graph.getNodes().keySet();
        Node node = null;
        for (Integer index : keys) {
            if (graph.getNodes().get(index).getIn() == 0) {
                node = graph.getNodes().get(index);
                break;
            }
        }
        // 没有入度为0的结点
        if (null == node) {
            return;
        }
        Node next = null;
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        System.out.println(node.getValue());
        while (!stack.isEmpty()) {
            node = stack.pop();
            for (int i = 0; i < node.nexts.size(); i++) {
                next = node.nexts.get(i);
                next.setIn(next.getIn()-1);
                node.setOut(node.getOut()-1);
                if(next.getIn()==0){
                    System.out.println(next.getValue());
                    stack.add(next);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[10][3];
        arr[0][0] = 5;
        arr[0][1] = 1;
        arr[0][2] = 2;

        arr[1][0] = 5;
        arr[1][1] = 1;
        arr[1][2] = 3;

        arr[2][0] = 5;
        arr[2][1] = 1;
        arr[2][2] = 4;

        arr[3][0] = 2;
        arr[3][1] = 2;
        arr[3][2] = 5;

        arr[4][0] = 3;
        arr[4][1] = 5;
        arr[4][2] = 6;

        arr[5][0] = 1;
        arr[5][1] = 3;
        arr[5][2] = 6;

        arr[6][0] = 1;
        arr[6][1] = 1;
        arr[6][2] = 7;

        arr[7][0] = 1;
        arr[7][1] = 4;
        arr[7][2] = 7;

        arr[8][0] = 1;
        arr[8][1] = 3;
        arr[8][2] = 5;

        arr[9][0] = 1;
        arr[9][1] = 7;
        arr[9][2] = 6;
        Graph graph = GenerateGraph.generate(arr);
        topologicalSort(graph);
    }

}
