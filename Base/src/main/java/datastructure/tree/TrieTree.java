package datastructure.tree;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 * 支持插入，删除，查询结果个数，查询以XXX为前缀的个数
 */
public class TrieTree {

    private Node head;

    /**
     * 插入一个字符串
     *
     * @param str
     */
    public void insert(String str) {
        if (StringUtils.isBlank(str)) {
            return;
        }
        if (head == null) {
            head = new Node();
        }
        char[] chars = str.toCharArray();
        Node node = head;
        for (int i = 0; i < chars.length; i++) {
            node = node.put(chars[i], i == chars.length - 1);
        }
    }

    /**
     * 删除字符串，字符串可能不存在
     *
     * @param str
     */
    public void delete(String str) {
        if (null == str) {
            return;
        }
        Node node = findNode(str);
        if (null == node || 0 == node.getEnd()) {
            return;
        }
        char[] chars = str.toCharArray();
        node = head;
        try {
            for (int i = 0; i < chars.length; i++) {
                if (node.get(chars[i]) != null && node.get(chars[i]).getPass() == 1) {
                    node.remove(chars[i]);
                    return;
                } else {
                    node = node.get(chars[i]);
                    if (null == node)
                        return;
                    node.setPass(node.getPass() - 1);

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            node = null;
        }
        if (null != node) {
            node.setEnd(node.getEnd() - 1);
        }
    }

    /**
     * 以 *** 为前缀的字符串有多少个
     *
     * @param str
     * @return
     */
    public int prefixNumber(String str) {
        Node node = findNode(str);
        return null == node ? 0 : node.getPass();
    }

    /**
     * 字符串共出现了多少次？
     *
     * @param str
     * @return
     */
    public int search(String str) {
        Node node = findNode(str);
        return null == node ? 0 : node.getEnd();
    }

    public Node findNode(String str) {
        Node node = head;
        if (null != str) {
            char[] chars = str.toCharArray();
            try {
                for (int i = 0; i < chars.length; i++) {
                    if (null == node) {
                        throw new NoSuchFieldException();
                    }
                    node = node.get(chars[i]);

                }
            } catch (NoSuchFieldException e) {
                node = null;
            }
        } else {
            node = null;
        }
        return node;
    }


    /**
     * 前缀树结点
     */
    @Data
    private static class Node {
        private Map<Character, Node> value;
        private int pass;
        private int end;

        public Node() {
        }

        public Node(int pass, int end) {
            this.pass = pass;
            this.end = end;
            value = new HashMap<>();
        }

        /**
         * 更新结点pass和end信息
         *
         * @param end
         */
        public void update(boolean end) {
            pass++;
            if (end)
                this.end++;
        }

        public Node(int pass, int end, Map<Character, Node> map) {
            this(pass, end);
            value = map;
        }

        public Node put(Character c, boolean end) {
            Node node;
            if (null == value) {
                value = new HashMap<>();
                node = new Node(1, end ? 1 : 0);
                value.put(c, node);
            } else {
                node = value.get(c);
                if (node == null) {
                    node = new Node(1, end ? 1 : 0);
                    value.put(c, node);
                } else {
                    value.get(c).update(end);
                }
            }
            return node;
        }

        public Node get(Character c) {
            if (null == value) {
                return null;
            }
            return value.get(c);
        }

        public Node remove(Character c) {
            if (null == value) {
                return null;
            }
            return value.remove(c);
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }


    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
//        String[] arr = new String[]{"aa", "aab", "aac", "aad", "aa", "aab", "aac", "aad", "ff", "ee"};
//        TrieTree trie1 = new TrieTree();
//        Right right = new Right();
//        trie1.insert(arr[0]);
//        trie1.insert(arr[1]);
//        trie1.insert(arr[2]);
//        trie1.insert(arr[3]);
//        trie1.insert(arr[4]);
//        right.insert(arr[0]);
//        right.insert(arr[1]);
//        right.insert(arr[2]);
//        right.insert(arr[3]);
//        right.insert(arr[4]);
//        trie1.delete(arr[3]);
//        right.delete(arr[3]);
//        trie1.delete(arr[4]);
//        right.delete(arr[4]);
//        for (int j = 0; j < arr.length; j++) {
////            int ans1 = trie1.search(arr[j]);
////            int ans2 = right.search(arr[j]);
////            if (ans1 != ans2) {
////                System.out.println("Oops!");
////            }
//            int ans11 = trie1.prefixNumber(arr[j]);
//            int ans22 = right.prefixNumber(arr[j]);
//            if (ans11 != ans22) {
//                System.out.println("Oops!");
//            }
//        }

        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree trie1 = new TrieTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    right.delete(arr[j]);
//                    System.out.println(trie1.search(arr[j]));
//                    System.out.println(right.search(arr[j]));
//                    System.out.println(trie1.prefixNumber(arr[j]));
//                    System.out.println(right.prefixNumber(arr[j]));
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = right.search(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
