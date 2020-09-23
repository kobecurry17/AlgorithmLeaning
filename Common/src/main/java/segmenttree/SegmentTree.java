package segmenttree;

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
            arr = new int[MAXN << 2];
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
            if(l==r){
                sum[rt] = sum[l]+
            }
            int mid = (l + r) >> 1;




        }


    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, maxValue);
            StandardSegmentTree seg = new StandardSegmentTree(arr);
            int S = 1;
            int N = arr.length;
            int root = 1;
            seg.build(S, N, root);

        }
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


}
