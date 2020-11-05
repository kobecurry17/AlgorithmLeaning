package InterviewStage1.class3;

/**
 * <p>
 * 给定一个数组arr,已知其中所有的值都是非负的，将这个数组看做一个容器，请返回容器能装多少水
 * 比如，arr = {3, 1, 2, 5, 2, 4}
 * 根据滑出的直方图就是容器形状，该容器可以装下5格水
 * 再比如,arr = {4, 5, 1, 3, 2},该容器可以装下2格水
 * </p>
 */
public class WaterContainer {

    /**
     * 可以装下多少水？
     *
     * @param arr
     * @return
     */
    public static int waterSize(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = 0;
        while (L < R) {
            for (int i = L + 1; i <= R; i++) {
                if (arr[i] >= arr[L] || arr[i] >= arr[R]) {
                    ans += addWater(arr, L, i);
                    L = i;
                }
            }
        }
        return ans;
    }

    private static int addWater(int[] arr, int L, int R) {
        int top = Math.min(arr[L], arr[R]);
        int ans = 0;
        for (int i = L + 1; i < R; i++) {
            ans += (top - arr[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 5, 2, 1, 2, 5, 7};
        System.out.println(waterSize(arr));
        arr = new int[]{3, 1, 2, 5, 2, 4};
        System.out.println(waterSize(arr));
        arr = new int[]{4, 5, 1, 3, 2};
        System.out.println(waterSize(arr));
    }
}
