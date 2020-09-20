package c_200920;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
 * 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
 * 注意：子字符串 是字符串中的一个连续字符序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ababccc"
 * 输出：5
 * 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。像 ['a', 'b', 'a', 'b', 'c', 'cc'] 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。
 * 示例 2：
 * <p>
 * 输入：s = "aba"
 * 输出：2
 * 解释：一种最大拆分方法为 ['a', 'ba'] 。
 * 示例 3：
 * <p>
 * 输入：s = "aa"
 * 输出：1
 * 解释：无法进一步拆分字符串。
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅包含小写英文字母
 */
public class UniqueSplit {

    static int max = Integer.MIN_VALUE;

    /**
     * 前缀树实现
     *
     * @param s
     * @return
     */
    public static int maxUniqueSplit1(String s) {
        char[] arr = s.toCharArray();
        TrieTree tree = new TrieTree();
        return process(arr, 0, tree);
    }

    private static int process(char[] arr, int index, TrieTree tree) {
        if (index == arr.length) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = index + 1; i <= arr.length; i++) {
            String str = getString(arr, index, i);
            TrieTree.Node node = tree.findNode(str);
            if (node == null || node.end == 0) {
                tree.insert(str);
                ans = Math.max(ans, 1 + process(arr, i, tree));
                tree.delete(str);
            }
        }
        return ans;
    }

    /**
     * 单set实现
     *
     * @param str
     * @return
     */
    private static int maxUniqueSplit2(String str) {
        Set<String> set = new HashSet<>();
        max = 0;
        f(str, set, 0);
        return max;
    }

    private static void f(String str, Set<String> set, int start) {
        if (start == str.length()) {
            max = Math.max(max, set.size());
        } else {
            for (int i = start; i < str.length(); i++) {
                String substring = str.substring(start, i + 1);
                if (!set.contains(substring)) {
                    set.add(substring);
                    f(str, set, i + 1);
                    set.remove(substring);
                }
            }
        }
    }

    // for test
    public static String generate(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (Math.random() * 5)));
        }
        return sb.toString();
    }


    public static String getString(char[] arr, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = begin; i < end; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 10;
        for (int i = 0; i < loops; i++) {
            String str = generate(maxSize);
            if (maxUniqueSplit1(str) != maxUniqueSplit2(str)) {
                System.out.println("Oops");
            }
        }
        System.out.println("Nice");
    }

    public static class TrieTree {


        private Node head;

        /**
         * 插入一个字符串
         *
         * @param str
         */
        public void insert(String str) {
            if (null == str || str.isEmpty()) {
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
        private static class Node {
            private Map<Character, Node> value;
            private int pass;
            private int end;

            public Map<Character, Node> getValue() {
                return value;
            }

            public void setValue(Map<Character, Node> value) {
                this.value = value;
            }

            public int getPass() {
                return pass;
            }

            public void setPass(int pass) {
                this.pass = pass;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

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
    }
}
