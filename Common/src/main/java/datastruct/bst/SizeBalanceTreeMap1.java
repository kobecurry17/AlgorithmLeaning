package datastruct.bst;

import java.security.InvalidKeyException;

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
        public void put(K key, V value) throws InvalidKeyException {
            if (null == key) {
                throw new InvalidKeyException("key must be not null");
            }
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex != null && lastIndex.key.compareTo(key) == 0) {
                lastIndex.value = value;
            } else {
                root = add(root, key, value);
            }
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<K, V>(key, value);
            } else {
                cur.size++;
                if (key.compareTo(key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    cur.right = add(cur.right, key, value);
                }
                return maintain(cur);
            }
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left != null ? cur.left.size : 0;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightSize = cur.right != null ? cur.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotation(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.left = leftRotation(cur.left);
                cur = rightRotation(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = rightRotation(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.right = rightRotation(cur.right);
                cur = leftRotation(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
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

        // 右旋
        public SBTNode<K, V> rightRotation(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size);
            return leftNode;
        }

        // 左旋
        public SBTNode<K, V> leftRotation(SBTNode<K, V> cur) {
            SBTNode<K, V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size);
            return rightNode;
        }


    }

}
