package unionset;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集(要求时间复杂度O(1))
 * 实现 inSameSet( A , B )
 * 实现 union( A , B )
 */
public class UnionSet<T> {
    /**
     * 对象集合映射表
     */
    private Map<T, Node> unionMap;
    /**
     * 集合大小
     */
    private Map<Node, Integer> sizeMap;


    /**
     * a 和 b 是否在同一个集合中
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSameSet(T a, T b) {
        Node node1 = unionMap.get(a);
        Node node2 = unionMap.get(b);
        if (null == node1 || null == node2) {
            return false;
        }
        return node1 == node2;
    }


    /**
     * 将 a 所在的集合与 b 所在的集合合并
     *
     * @param a
     * @param b
     */
    public void union(T a, T b) {
        Node node1 = unionMap.get(a);
        Node node2 = unionMap.get(b);

        if (null == node1 && null == node2) {
            node1 = new Node(a);
            unionMap.put(a, node1);
            unionMap.put(b, node1);
            sizeMap.put(node1, 2);
        } else if (node1 == null || node2 == null) {
            node1 = null == node1 ? node2 : node1;
            unionMap.put(a,node1);
            unionMap.put(b,node1);
            sizeMap.put(node1,sizeMap.get(node1)+1);
        } else {
            int size1 = sizeMap.get(node1);
            int size2 = sizeMap.get(node1);
            if(size1)
        }

    }

    public UnionSet() {
        unionMap = new HashMap<>();
        sizeMap = new HashMap<>();
        parentMap = new HashMap<>();
    }


    private static class Node<T> {
        public T t;

        public Node(T t) {
            this.t = t;
        }
    }
}
