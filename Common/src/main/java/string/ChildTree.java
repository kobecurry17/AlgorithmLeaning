package string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两颗树 A 和 B 判断 B是否是A的子树
 */
@SuppressWarnings("all")
public class ChildTree {

    /**
     * 暴力方法
     *
     * @param big
     * @param small
     * @return
     */
    public static boolean containsTree1(Node big, Node small) {
        if (null == small) {
            return true;
        }
        if (null == big) {
            return false;
        }
        if (isSamtValueStrictire(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) && containsTree1(big.right, small);
    }

    private static boolean isSamtValueStrictire(Node a, Node b) {
        if (null == a && null == b) {
            return true;
        }
        if (null == a || null == b) {
            return false;
        }
        return a.value == b.value && isSamtValueStrictire(a.left, b.left) && isSamtValueStrictire(a.right, b.right);
    }

    private static boolean containsTree2(Node big, Node small) {
        if (null == small) {
            return true;
        }
        if (null == big) {
            return false;
        }
        List<String> A = new ArrayList<>();
        List<String> B = new ArrayList<>();
        pre(big, A);
        pre(small, B);
        return findFirstIndex(A, B) != -1;

    }

    /**
     * b在a上第一次出现的下标
     * 不存在则返回-1
     *
     * @param a
     * @param b
     * @return
     */
    private static int findFirstIndex(List<String> a, List<String> b) {
        int[] next = next(b);
        int x = 0;
        int y = 0;
        while (x < a.size() && y < b.size()) {
            if (isEqual(a.get(x), b.get(y))) {
                x++;
                y++;
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == next.length ? x - y : -1;
    }


    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    private static int[] next(List<String> arr) {
        if (arr.size() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[arr.size()];
        next[0] = -1;
        int cur = 0;
        int i = 1;
        while (i < arr.size()) {
            if (isEqual(arr.get(i), arr.get(cur))) {
                i++;
                cur++;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                i++;
            }
        }
        return next;
    }

    /**
     * 返回先序遍历结果
     *
     * @param head
     * @param list
     * @return
     */
    private static void pre(Node head, List<String> list) {
        if (null == head) {
            list.add(null);
        } else {
            list.add(String.valueOf(head.value));
            pre(head.left, list);
            pre(head.right, list);
        }
    }


    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // for test
    public static Node generateBTS(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.1f) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxValue, maxValue);
        node.right = generate(level + 1, maxValue, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int bigLevel = 7;
        int smallLevel = 3;
        int maxValue = 10;
        for (int i = 0; i < loops; i++) {
            Node a = generateBTS(bigLevel, maxValue);
            Node b = generateBTS(smallLevel, maxValue);
            if (containsTree1(a, b) != containsTree2(a, b)) {
                containsTree1(a, b);
                containsTree2(a, b);
                System.out.println("Oops!");
            }
        }
    }

}
