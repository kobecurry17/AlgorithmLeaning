package datastructure.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
@SuppressWarnings("all")
public class DepthFirst {


    /**
     * 深度优先遍历
     *
     * @param graph
     */
    public static void traverse(Node node) {
        if (null == node) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.getValue());
        while (!stack.isEmpty()) {
            node = stack.pop();
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    System.out.println(next.getValue());
                    stack.add(node);
                    stack.add(next);
                    set.add(next);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[8][3];
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
        arr[5][1] = 6;
        arr[5][2] = 3;

        arr[6][0] = 1;
        arr[6][1] = 1;
        arr[6][2] = 7;

        arr[7][0] = 1;
        arr[7][1] = 4;
        arr[7][2] = 7;

        Graph generate = GenerateGraph.generate(arr);
        traverse(generate.getNodes().get(1));
    }

}
