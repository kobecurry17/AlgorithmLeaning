package datastruct.bst;

/**
 * SizeBalanceTree
 * 任意一个叔叔结点的结点数不少于任何一个侄子结点
 */
@SuppressWarnings("all")
public class SizeBalanceTreeMap1 {
    /**
     * SizeBalanceTree节点实体
     *
     * @param <K>
     * @param <V>
     */
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public int size;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    /**
     * SizeBalanceTree结构的Map
     *
     * @param <K>
     * @param <V>
     */
    public static class SizeBalanceMap<K extends Comparable<K>, V> {

        private SBTNode<K, V> root;


        public V get(K key) {
            if (null == key) {
                throw new NullPointerException("key不能为空！");
            }
            SBTNode<K, V> last = findLastIndex(key);
            if (null != last || last.key.compareTo(key) == 0) {
                return last.value;
            } else {
                return null;
            }
        }

        /**
         * 如果key存在，改value
         * 如果key不存在，新增节点
         *
         * @param key
         * @param value
         */
        public void put(K key, V value) {

        }


        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> pre = root;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (cur.key.compareTo(key) == 0) {
                    break;
                } else if (cur.key.compareTo(key) < 0) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return pre;
        }


    }


}
