package unionset;

import java.util.*;

/**
 * 并查集(要求时间复杂度O(1))
 * 实现 inSameSet( A , B )
 * 实现 union( A , B )
 */
@SuppressWarnings("all")
public class UnionSet<T> {
    /**
     * 对象集合映射表
     */
    private Map<T, Node> nodes;
    /**
     * 集合大小
     */
    private Map<Node, Integer> sizeMap;
    // 加入parent 使isUnion效率提升为O(1)
    private Map<Node, Node> parentMap;


    /**
     * a 和 b 是否在同一个集合中
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSameSet(T a, T b) {
        Node node1 = nodes.get(a);
        Node node2 = nodes.get(b);
        if (null == node1 || null == node2) {
            return false;
        }
        return findParentNode(node1) == findParentNode(node2);
    }

    /**
     * 寻找一个结点的父节点，不存在则返回 Null
     *
     * @param node
     * @return
     */
    private Node findParentNode(Node node) {
        Node parent = null;
        while (node != null && node != parent) {
            parent = node;
            node = parentMap.get(node);
        }
        return parent;
    }


    /**
     * 将 a 所在的集合与 b 所在的集合合并
     *
     * @param a
     * @param b
     */
    public void union2(T a, T b) {
        if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
            return;
        }
        Node A = nodes.get(a);
        Node B = nodes.get(b);
        Node parentNode1 = findParentNode(A);
        Node parentNode2 = findParentNode(B);
        if (parentNode1 != parentNode2) {
            Integer size1 = sizeMap.get(parentNode1);
            Integer size2 = sizeMap.get(parentNode2);
            if (size1 < size2) {
                parentMap.put(parentNode2, parentNode1);
                sizeMap.put(parentNode2, size1 + size2);
            } else {
                parentMap.put(parentNode1, parentNode2);
                sizeMap.put(parentNode1, size1 + size2);
            }
        }
    }


    public UnionSet(T[] list) {
        nodes = new HashMap<>();
        sizeMap = new HashMap<>();
        parentMap = new HashMap<>();
        for (T t : list) {
            Node node = new Node(t);
            nodes.put(t, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }


    private static class Node<T> {
        public T t;

        public Node(T t) {
            this.t = t;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 2, 5, 7, 8, 4, 10};
        UnionSet<Integer> unionSet = new UnionSet(arr);
        System.out.println("unionSet.isSameSet(1,3)" + unionSet.isSameSet(1, 3));
        System.out.println("unionSet.isSameSet(1,2)" + unionSet.isSameSet(1, 2));
        System.out.println("unionSet.isSameSet(1,3)" + unionSet.isSameSet(1, 3));
        System.out.println("unionSet.isSameSet(1,5)" + unionSet.isSameSet(1, 5));
        System.out.println("unionSet.isSameSet(1,7)" + unionSet.isSameSet(1, 7));
        System.out.println("unionSet.isSameSet(1,4)" + unionSet.isSameSet(1, 4));
        unionSet.union2(3, 5);
        unionSet.union2(2, 7);
        unionSet.union2(1, 5);
        System.out.println("unionSet.isSameSet(1,3)" + unionSet.isSameSet(1, 3));
        System.out.println("unionSet.isSameSet(1,2)" + unionSet.isSameSet(1, 2));
        System.out.println("unionSet.isSameSet(1,3)" + unionSet.isSameSet(1, 3));
        System.out.println("unionSet.isSameSet(1,5)" + unionSet.isSameSet(1, 5));
        System.out.println("unionSet.isSameSet(1,7)" + unionSet.isSameSet(1, 7));
        System.out.println("unionSet.isSameSet(1,4)" + unionSet.isSameSet(1, 4));

    }

}
