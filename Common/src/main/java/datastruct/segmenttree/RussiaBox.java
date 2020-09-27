package datastruct.segmenttree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 线段树实例
 * 想象一个标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 * 下面是这个游戏的简化版:
 * 1) 只会下落正方形积木
 * 2) [a,b]->代表一个边长为b的正方形积木，积木边缘沿着X = a这条线从上方掉落
 * 3) 认为整个X轴都可能接住积木,也就是说没有左右边界
 * 4) 没有整体边界所以不会消除积木
 * 给定一个二维数组matrix,可以代表N个积木依次掉落
 * 返回每一次掉落后的最大高度
 */
@SuppressWarnings("all")
public class RussiaBox {


    public static class SegmentTree {
        public int[] max;
        public int[] change;
        public boolean[] update;

        public SegmentTree(int size) {
            int n = size + 1;
            max = new int[n];
            change = new int[n << 2];
            update = new boolean[n << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void update(int L, int R, int num,
                           int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] += num;
                max[rt] += +num;
                return;
            }
            int mid = l + (r - l) << 1;
            pushDown(rt);
            if (L <= mid) {
                update(L, R, num, l, mid, rt << 1);
            }
            if (mid <= R) {
                update(L, R, num, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = l + (r - l) >> 1;
            int max = Integer.MIN_VALUE;
            if (L <= mid) {
                max = Math.max(max, query(L, R, l, mid, rt << 1));
            }
            if (mid <= R) {
                max = Math.max(max, query(L, R, mid + 1, r, rt << 1 | 1));
            }
            return max;
        }

        private void pushDown(int rt) {
            if (update[rt]) {
                update[rt] = false;
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                change[rt] = 0;
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
            }
        }


        // positions
        // [2,7] -> 2 , 8
        // [3, 10] -> 3, 12
        //
        //
        public HashMap<Integer, Integer> index(int[][] positions) {
            TreeSet<Integer> pos = new TreeSet<>();
            for (int[] arr : positions) {
                pos.add(arr[0]);
                pos.add(arr[0] + arr[1] - 1);
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (Integer index : pos) {
                map.put(index, ++count);
            }
            return map;
        }

        /**
         * 掉落的方块
         * 我猜不是最优解
         * 时间复杂度O(N*logN)
         *
         * @param positions
         * @return
         */
        public List<Integer> fallingSquares(int[][] positions) {
            HashMap<Integer, Integer> map = index(positions);
            // 100   -> 1    306 ->   2   403 -> 3
            // [100,403]   1~3
            int N = map.size(); // 1 ~ 	N
            SegmentTree segmentTree = new SegmentTree(N);
            int max = 0;
            List<Integer> res = new ArrayList<>();
            // 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
            for (int[] arr : positions) {
                int L = map.get(arr[0]);
                int R = map.get(arr[0] + arr[1] - 1);
                int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
                max = Math.max(max, height);
                res.add(max);
                segmentTree.update(L, R, height, 1, N, 1);
            }
            return res;
        }
    }
}