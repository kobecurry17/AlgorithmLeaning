package InterviewStage1.class3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如果给你一个二维数组，每一个值表示这一块底薪的高度
 * 求整块地形能装下多少水
 * <p>
 * 应用场景：把二维矩阵想象成一张地图，计算这个城市的降水量，或者选择下水道
 * </p>
 * <p>
 * 例如:
 * arr = {
 * {1, 1, 0, 1, 1},
 * {1, 0, 0, 1, 1},
 * {1, 1, 1, 0, 1,},
 * {1, 0, 0, 0, 1,},
 * {1, 1, 1, 1, 1,}
 * }
 * 返回 4
 */
public class WaterContainerII {


    public static int waterContainer(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        boolean[][] entered = new boolean[row][col];

        for (int i = 0; i < col; i++) {
            entered[i][0] = true;
            heap.add(new Node(matrix[i][0], i, 0));

            entered[i][row - 1] = true;
            heap.add(new Node(matrix[i][row - 1], i, row - 1));
        }
        for (int i = 0; i < row; i++) {
            entered[0][i] = true;
            heap.add(new Node(matrix[0][i], 0, i));

            entered[col - 1][i] = true;
            heap.add(new Node(matrix[col - 1][i], col - 1, i));
        }
        int max = Integer.MIN_VALUE;
        int water = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            if (cur.x > 0 && !entered[cur.x - 1][cur.y]) {
                water += Math.max(0, max - matrix[cur.x - 1][cur.y]);
                entered[cur.x - 1][cur.y] = true;
                heap.add(new Node(matrix[cur.x - 1][cur.y], cur.x - 1, cur.y));
            }

            if (cur.y > 0 && !entered[cur.x][cur.y - 1]) {
                water += Math.max(0, max - matrix[cur.x][cur.y - 1]);
                entered[cur.x][cur.y - 1] = true;
                heap.add(new Node(matrix[cur.x][cur.y - 1], cur.x, cur.y - 1));
            }

            if (cur.x + 1 < col && !entered[cur.x + 1][cur.y]) {
                water += Math.max(0, max - matrix[cur.x + 1][cur.y]);
                entered[cur.x + 1][cur.y] = true;
                heap.add(new Node(matrix[cur.x + 1][cur.y], cur.x + 1, cur.y));
            }

            if (cur.y + 1 < row && !entered[cur.x][cur.y + 1]) {
                water += Math.max(0, max - matrix[cur.x][cur.y + 1]);
                entered[cur.x][cur.y + 1] = true;
                heap.add(new Node(matrix[cur.x][cur.y + 1], cur.x, cur.y + 1));
            }
        }

        return water;
    }

    public static class Node {
        private Integer value, x, y;

        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value.compareTo(o2.value);
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1,},
                {1, 0, 0, 0, 1,},
                {1, 1, 1, 1, 1,}
        };
        System.out.println(waterContainer(arr));
        arr = new int[][]{
                {4, 2, 4, 3, 4},
                {4, 4, 4, 1, 4},
                {4, 3, 1, 2, 4,},
                {4, 3, 3, 1, 4,},
                {4, 4, 4, 4, 4,}
        };
        System.out.println(waterContainer(arr));
    }
}
