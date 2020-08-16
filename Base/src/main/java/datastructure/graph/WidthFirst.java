package datastructure.graph;

import java.util.*;

/**
 * 图的广度优先遍历
 */
@SuppressWarnings("all")
public class WidthFirst {

    public static void  traverse(Graph graph){
        if(graph.isEmpty()){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(graph.getNodes().get(1));
        Set<Node> set = new HashSet<>();
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.getValue());
            for (int i = 0; i < poll.nexts.size(); i++) {
                Node next = poll.nexts.get(i);
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
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
        arr[5][1] = 3;
        arr[5][2] = 6;

        arr[6][0] = 1;
        arr[6][1] = 1;
        arr[6][2] = 7;

        arr[7][0] = 1;
        arr[7][1] = 4;
        arr[7][2] = 7;

        Graph generate = GenerateGraph.generate(arr);
        traverse(generate);
    }

}
