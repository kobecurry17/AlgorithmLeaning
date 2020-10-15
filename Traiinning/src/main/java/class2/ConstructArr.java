package class2;

/**
 * <p>
 * 给定一个正整数M，请构造出一个长度为M的数组arr
 * 要求对任意的i,j,k三个位置,如果i<j<k,都有 arr[i] + arr[k] != 2 * arr[k]
 * </p>
 * TODO:再听听讲解
 */
public class ConstructArr {


    // for testing
    public static boolean check(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] + arr[k] == 2 * arr[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 生成长度为size的达标数组
    // 达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
    public static int[] construct(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        // size
        // 一半长达标来
        // 7   :   4
        // 8   :   4
        // [4个奇数] [3个偶]
        int halfSize = (size + 1) / 2;
        int[] base = construct(halfSize);
        // base -> 等长奇数达标来
        // base -> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }


    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "======>" + arr[i]);
        }
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        print(construct(500));
        for (int i = 1; i < loops; i++) {
            int[] construct = construct(i);
            if (!check(construct)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }


}
