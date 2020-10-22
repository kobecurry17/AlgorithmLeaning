package class5;

import java.util.Arrays;

/**
 * <p>
 * 已知一颗二叉树中没有重复结点，并且给定这课树的中序遍历数组和先序遍历数组
 * 返回后序遍历数组
 * 例如:
 * int[] pre = {1, 2, 4, 5, 3, 6, 7};
 * int[] in = {4, 2, 5, 1, 6, 3, 7};
 * return [4,5,2,6,7,3,1]
 * </p>
 */
@SuppressWarnings("all")
public class BinaryTreeDeserialization {

    /**
     * 定这课树的中序遍历数组和先序遍历数组
     * 返回后序遍历数组
     *
     * @param pre
     * @param in
     * @return
     */
    public static int[] post(int[] pre, int[] in) {
        int[] post = new int[pre.length];
        process(pre, in, post, 0, in.length - 1, post.length - 1);
        return post;
    }

    private static int process(int[] pre, int[] in, int[] post, int l, int r, int position) {
        if (r < l || l > r ) {
            return position+1;
        }
        if (l == r) {
            post[position] = in[l];
        }
        int mid = findFirst(pre, in, l, r);
        post[position] = in[mid];
        position = process(pre, in, post, mid + 1, r, position - 1);
        position = process(pre, in, post, l, mid - 1, position - 1);
        return position;
    }

    private static int findFirst(int[] pre, int[] in, int l, int r) {
        for (int i = 0; i < pre.length; i++) {
            for (int j = l; j <= r; j++) {
                if (in[j] == pre[i]) {
                    return j;
                }
            }
        }
        return -1;
    }


    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "======>" + arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] post = post(pre, in);
        print(post);
    }
}
