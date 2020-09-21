package datastruct.morris;

import java.util.HashSet;

/**
 * Morris遍历
 * 实现空间复杂度O(1)遍历一颗树
 */
@SuppressWarnings("all")
public class MorrisTraversal {

    /**
     * 二叉树结点
     */
    public static class Node {

        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    /**
     * 生成一颗最大高度为maxLevel,最大值为maxValue的树
     *
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateBT(int maxLevel, int maxValue) {
        HashSet<Integer> set = new HashSet();
        return generate(1, maxLevel, maxValue, set);
    }

    /**
     * 随机生成结点
     *
     * @param level
     * @param maxLevel
     * @param maxValue
     * @param set
     * @return
     */
    private static Node generate(int level, int maxLevel, int maxValue, HashSet<Integer> set) {
        if (level > maxLevel || Math.random() < 0.1) {
            return null;
        }
        int value;
        do {
            value = (int) (Math.random() * maxValue);
        } while (set.contains(value));
        set.add(value);
        Node node = new Node(value);
        node.left = generate(level + 1, maxLevel, maxValue, set);
        node.right = generate(level + 1, maxLevel, maxValue, set);
        return node;
    }

    /**
     * 基础的无任何额外逻辑的morris遍历
     *
     * @param head
     */
    public void morris(Node head) {
        if (null == head) {
            return;
        }
        Node cur = head;
        Node mostRight = head;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight != null && mostRight != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * morris 中序遍历
     *
     * @param head
     */
    public static void morrisMid(Node head) {
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println("");
    }


    /**
     * morris 先序遍历
     *
     * @param head
     */
    public static void morrisIn(Node head) {
        Node cur = head;
        Node mostRight = head;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 后续比较难
     * 兴趣了解
     *
     * @param head
     */
    public void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    /**
     * 判断一颗树是否是搜索二叉树
     *
     * @return
     */
    public static boolean isBST(Node head) {
        if (null == head) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int min = Integer.MIN_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (null != mostRight) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (cur.value < min) {
                return false;
            } else {
                min = cur.value;
            }
            cur = cur.right;
        }
        return true;
    }


    /**
     * 一颗二叉树的最小高度
     *
     * @param head
     * @return
     */
    public static int minHeight(Node head) {
        if (null == head) {
            return 0;
        }
        Node cur = head;
        Node mostRight = null;
        int curHeight = 0;
        int minHeight = Integer.MAX_VALUE;
        int now = 0;
        int min = Integer.MIN_VALUE;
        while (cur != null) {
            now = curHeight;
            mostRight = cur.left;
            if (null != mostRight) {
                while (mostRight.right != null && mostRight.right != cur) {
                    now++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    minHeight = Math.min(now, minHeight);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    now = curHeight;
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(isBST(head));
    }
}
