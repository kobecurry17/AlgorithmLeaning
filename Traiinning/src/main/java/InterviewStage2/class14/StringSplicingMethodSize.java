package InterviewStage2.class14;

import org.w3c.dom.Node;

/**
 * <p>
 * 假设所有字符都是小写字母，大字符串是str
 * </p>
 * arr是去重的单词表,每个单词都不是空字符串且可以使用任意次
 * 使用arr中的单词有多少种拼接str的方式,返回方法数
 */
public class StringSplicingMethodSize {

    /**
     * 时间复杂度  O(N ^ 3)
     *
     * @param target 目标大字符串
     * @param words  单词表
     * @return
     */
    public static int splicingMethodSize(String target, String[] words) {
        if (null == target || target.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        // 生成一个dp
        // dp[i][j] 表示用words有多少种拼的方式
        // dp[i][j] = words[x] + dp[i-words.length][j]
        int n = target.length();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        dp[n][n] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][i - 1] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j <= n; j++) {
                for (String word : words) {
                    dp[i][j] += equals(target, i, j, word) ? j - i == 0 ? 1 : dp[i][j - word.length()] : 0;
                }
            }
        }


        return dp[0][n];
    }

    /**
     * 判断target 上 i..j位置是否以word结尾
     *
     * @param target
     * @param i
     * @param j
     * @param word
     * @return
     */
    private static boolean equals(String target, int i, int j, String word) {
        if (word.length() > j - i + 1) {
            return false;
        }
        for (int k = 0; k < word.length(); k++) {
            int x = i + k;
            if (target.charAt(x) != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }


    /**
     * O(n^2)
     *
     * @param target
     * @param words
     * @return
     */
    public static int splicingMethodSize2(String target, String[] words) {
        if (null == target || target.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        char[] arr = target.toCharArray();
        // 生成前缀树
        TrieTree trieTree = build(words);

        int[] dp = new int[target.length() + 1];
        dp[target.length()] = 1;
        Node cur;
        for (int i = target.length() - 1; i >= 0; i--) {
            cur = trieTree.head;
            for (int k = i; k < target.length(); k++) {
                cur = cur.next[arr[k] - 'a'];
                if (null == cur) {
                    break;
                }
                if (cur.end) {
                    dp[i] += dp[k + 1];
                }
            }
        }
        return dp[0];
    }

    private static TrieTree build(String[] words) {
        return new TrieTree(words);
    }

    public static class TrieTree {

        private Node head;

        public boolean search(String word) {
            char[] arr = word.toCharArray();
            Node cur = head;
            for (int i = 0; i < arr.length; i++) {
                if (null == cur.next[arr[i] - 'a']) {
                    return false;
                }
                cur = cur.next[arr[i] - 'a'];
            }
            return cur.end;
        }

        public TrieTree(String[] words) {
            head = new Node();
            for (int i = 0; i < words.length; i++) {
                add(words[i]);
            }
        }

        private void add(String word) {
            char[] arr = word.toCharArray();
            Node cur = head;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (null == cur.next[index]) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = true;
        }
    }

    public static class Node {
        Node[] next;
        boolean end;

        public Node() {
            this.next = new Node[26];
        }
    }

    public static void main(String[] args) {
        String target = "aabcd";
        String[] words = new String[]{"a", "bc", "d", "c", "b"};
        System.out.println(splicingMethodSize2(target, words));
    }

}
