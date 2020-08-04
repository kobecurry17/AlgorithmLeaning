package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一颗二叉树头节点和 A,B 两结点，返回 A,B的最低公共祖先
 */
@SuppressWarnings("all")
public class LowestParent {

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

    /**
     * 需要从左右子树得到的内容
     */
    public static class Info {
        private boolean hasA;
        private boolean hasB;
        private Node intersectNode;
    }

    /**
     * 给定一颗二叉树头节点和 A,B 两结点，返回 A,B的最低公共祖先
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public static Node findParent1(Node head, Node a, Node b) {
        if (null == head || null == a || null == b) {
            return null;
        }
        return process(head, a, b).intersectNode;
    }

    /**
     * 给定一颗二叉树头节点和 A,B 两结点，返回 A,B的最低公共祖先
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public static Node findParent2(Node head, Node a, Node b) {
        if (null == head || null == a || null == b) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        pre(head, map);

        Set<Node> set = new HashSet();


        Node parent = a;
        while (parent != null) {
            set.add(map.get(parent));
            parent = map.get(parent);
        }
        parent = b;
        while (parent != null) {
            if (set.contains(parent)) {
                return parent;
            }
            parent = map.get(parent);
        }
        return null;
    }

    /**
     * 先序遍历，把所有结点加入map
     * key为自己，value 为父节点
     *
     * @param head
     * @param map
     */
    private static void pre(Node head, Map<Node, Node> map) {
        if (head != null) {
            if (head.left != null) {
                map.put(head.left, head);

            }
            if (head.right != null) {
                map.put(head.right, head);
            }
            pre(head.left,map);
            pre(head.right,map);
        }

    }

    // for test
    private static Node findRandomNode(Node head) {
        if (Math.random() < 0.1) {
            return new Node((int) (Math.random() * 500));
        }
        Map<Node, Node> map = new HashMap<>();
        pre(head,map);
        if (map.isEmpty()) return null;
        Object[] nodes = map.keySet().toArray();
        int i = (int) (Math.random() * (nodes.length- 1));
        return (Node) nodes[i];

    }

    /**
     * 递归
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    private static Info process(Node head, Node a, Node b) {
        Info info = new Info();
        if (null == head) {
            return info;
        }
        Info left = process(head.left, a, b);
        Info right = process(head.right, a, b);

        boolean hasA = left.hasA || right.hasA;
        boolean hasB = left.hasB || right.hasB;
        if (hasA && hasB) {
            info.hasA = true;
            info.hasB = true;
            info.intersectNode = null == left.intersectNode ? null == right.intersectNode ? head : left.intersectNode : right.intersectNode;
        } else if ((hasA && head.value == b.value) || (hasB && head.value == a.value)) {
            info.hasA = true;
            info.hasB = true;
            info.intersectNode = head;
        } else if (a.value == head.value) {
            info.hasA = true;
        } else if (b.value == head.value) {
            info.hasB = true;
        }
        return info;
    }

    /**
     * 生成一颗最大高度为maxLevel,最大值为maxValue的树
     *
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    /**
     * 随机生成结点
     *
     * @param level
     * @param maxLevel
     * @param maxValue
     * @return
     */
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.1) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxLevel, maxValue);
        node.right = generate(level + 1, maxLevel, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int loopTimes = 50_000000;
        int maxLevel = 5;
        int maxValue = 500;
        for (int i = 0; i < loopTimes; i++) {
            Node head = generateBST(maxLevel, maxValue);
            Node a = findRandomNode(head);
            Node b = null;
            do {
                b = findRandomNode(head);
            } while (b == a);
            if (findParent1(head, a, b) != findParent2(head, a, b)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }
}
