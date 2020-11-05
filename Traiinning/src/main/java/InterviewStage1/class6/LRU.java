package InterviewStage1.class6;

import java.security.InvalidKeyException;

/**
 * 经典LRU替换算法
 */
public class LRU {

    // 思路:
    // 由链表+map实现
    // 链表记录每个key出现的先后顺序
    // map用来支持增删改查操作


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
        public SBTNode<K, V> last;
        public SBTNode<K, V> next;

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
        private SBTNode<K, V> tail;
        private SBTNode<K, V> head;
        private int limit = 3;
        private int size = 0;

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
            eliminated();
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex != null && lastIndex.key.compareTo(key) == 0) {
                lastIndex.value = value;
                lastIndex.last.next = lastIndex.next;
                lastIndex.next.last = lastIndex.last;
                lastIndex.last = tail;
                lastIndex.next = null;
                tail = lastIndex;
            } else {
                root = add(root, key, value);
            }
            if (size == 0) {
                head = root;
                tail = root;
            }
            size++;
        }

        /**
         * 淘汰
         */
        private void eliminated() {
            if (size == limit) {
                size--;
                SBTNode<K, V> tmp = head;
                head = head.next;
                head.last = null;
                //remove(tep)
            }
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                SBTNode node = new SBTNode<K, V>(key, value);
                tail.next = node;
                node.last = tail;
                tail = node;
                return tail;
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
