package InterviewStage2.class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 给定一个字符类型的二维数组board
 * 和一个字符串组成的列表words
 * 可以从board任何一个位置出发，每一步u可以走向上、下、左、右四个方向
 * 但是一条路径已经走过的位置，不能重复走。
 * 返回那些word可以被走出来
 * </p>
 * 【例如】
 * board = {
 * {'o','a','a','n'},
 * {'e','t','a','e'},
 * {'i','h','k','r'},
 * {'i','f','l','v'}
 * }
 * words = {"oath","pea","eat","rain"},
 * 输出:["eat","oath"]
 */
public class WordSearch {


    public static String[] accessWord(char[][] board, String[] words) {
        if (words == null || words.length == 0 || board == null || board.length == 0 || board[0].length == 0) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        HashMap<Character, List<Node>> map = build(board);

        for (String word : words) {
            if (map.containsKey(word.charAt(0))) {
                boolean flag = true;
                // 单词中某一个字母词频不存在，直接返回false
                for (int i = 0; i < word.length(); i++) {
                    if (!map.containsKey(word.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }

                for (Node node : map.get(word.charAt(0))) {
                    if (process(board, word, map, new boolean[board.length][board[0].length], node)) {
                        list.add(word);
                        break;
                    }
                }
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 简历board的词频信息
     *
     * @param board
     * @return
     */
    private static HashMap<Character, List<Node>> build(char[][] board) {
        HashMap<Character, List<Node>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Node node = new Node(i, j);
                List<Node> list = map.getOrDefault(board[i][j], new ArrayList<>());
                list.add(node);
                map.put(board[i][j], list);
            }
        }
        return map;
    }

    /**
     * 判断 board 中能否得到word
     *
     * @param board
     * @param word
     * @param map
     * @param status
     * @param cur
     * @return
     */
    private static boolean process(char[][] board, String word, HashMap<Character, List<Node>> map, boolean[][] status, Node cur) {
        if (null == word || word.length() == 0) {
            return true;
        }
        char[] arr = word.toCharArray();
        boolean next = false;
        if (board[cur.x][cur.y] == arr[0] && !status[cur.x][cur.y]) {
            status[cur.x][cur.y] = true;
            String nextPart = word.substring(1);
            if (cur.x - 1 >= 0) {
                next |= process(board, nextPart, map, status, new Node(cur.x - 1, cur.y));
            }
            if (cur.x + 1 < board.length) {
                next |= process(board, nextPart, map, status, new Node(cur.x + 1, cur.y));
            }
            if (cur.y - 1 >= 0) {
                next |= process(board, nextPart, map, status, new Node(cur.x, cur.y - 1));
            }
            if (cur.y + 1 < board[0].length) {
                next |= process(board, nextPart, map, status, new Node(cur.x, cur.y + 1));
            }
            status[cur.x][cur.y] = false;
            return next;
        }
        return false;
    }


    public static class Node {
        int x;
        int y;

        public Node(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }

    // for test
    public static void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("\n==================\n");
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        print(accessWord(board, words));
    }
}
