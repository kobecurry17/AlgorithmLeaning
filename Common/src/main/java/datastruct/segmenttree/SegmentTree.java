package datastruct.segmenttree;

/**
 * 线段树
 */
@SuppressWarnings("all")
public class SegmentTree {

    public static class StandardSegmentTree {
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public StandardSegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        /**
         * 在初始化阶段，先把sum数组填好
         *
         * @param l  左边界
         * @param r  右边界
         * @param rt 这个范围在sum中的下标
         */
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, (rt << 1) | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
        }

        /**
         * 在[L,R]范围上增加num
         *
         * @param L   真实数组上的范围
         * @param R   真实数组上的范围
         * @param num 所有数字加上一个值
         * @param l   在线段树上的范围
         * @param r   在线段树中的范围
         * @param rt  线段树下标，标记着l~r在线段树哪个下标
         */
        public void add(int L, int R, int num,
                        int l, int r,
                        int rt) {

            if (L <= l && r <= R) {
                sum[rt] += (r - l + 1) * num;
                lazy[rt] += num;
                return;
            }

            int mid = (l + r) >> 1;
            // 把之前的lazy信息下发
            // mid - l +1 左孩子的结点树
            // r - mid +1 又孩子的结点树
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, num, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, num, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        private void pushDown(int rt, int lSzie, int rSize) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * lSzie;
                sum[rt << 1 | 1] = change[rt] * rSize;
                update[rt] = false;
            }
            // 把父节点的lazy信息下发
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * lSzie;
                lazy[(rt << 1) | 1] += lazy[rt];
                sum[(rt << 1) | 1] += lazy[rt] * rSize;
                lazy[rt] = 0;
            }
        }

        /**
         * 查询累加和
         *
         * @param L   真实数组上的范围
         * @param R   真实数组上的范围
         * @param num 所有数字加上一个值
         * @param l   在线段树上的范围
         * @param r   在线段树中的范围
         * @param rt  线段树下标，标记着l~r在线段树哪个下标
         */
        public long query(int L, int R,
                          int l, int r,
                          int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            long ans = 0;
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        /**
         * 在数组上讲[L,R]更新为num
         *
         * @param L
         * @param R
         * @param num
         * @param l
         * @param r
         * @param rt
         */
        public void update(int L, int R, int num,
                           int l, int r,
                           int rt) {
            if (L <= l && r <= R) {
                change[rt] = num;
                update[rt] = true;
                sum[rt] = num * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, num, l, mid, rt << 1);
            }
            if (mid < R) {
                update(L, R, num, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }
    }

    public static int[] generateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = generateRandomArray(len, max);
            StandardSegmentTree seg = new StandardSegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        StandardSegmentTree seg = new StandardSegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }


    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }
}
