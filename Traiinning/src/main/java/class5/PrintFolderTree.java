package class5;


import java.util.TreeMap;


/**
 * 打印目录树
 * 如:
 * String[] arr={"b\st","d\","a\d\e","a\b\c"}
 */
public class PrintFolderTree {

    public static Node root = new Node(null);


    public static class Node {
        private char value;
        private TreeMap<Character, Node> next;
        private int pass;

        public Node(Character value) {
            if (null != value) {
                this.value = value;
                pass = 1;
            }
            next = new TreeMap<>();
        }

    }

    private static void print(Node node, int space) {
        if (null == node) {
            return;
        }
        String prefix = "";
        for (int i = 0; i < space; i++) {
            prefix += "  ";
        }
        for (int i = 0; i < node.pass; i++) {
            System.out.println(prefix + node.value);
        }
        node.next.keySet().forEach(i -> print(node.next.get(i), space + 2));
    }

    public static void generateTrieTree(String[] arr) {
        root.pass = arr.length;
        for (int i = 0; i < arr.length; i++) {
            char[] str = arr[i].toCharArray();
            Node cur = root;
            Node next = cur;
            for (int j = 0; j < str.length; j++) {
                if (str[j] != '\\') {
                    if (cur.next.containsKey(str[j])) {
                        next = cur.next.get(str[j]);
                        next.pass++;
                    } else {
                        next = new Node(str[j]);
                        cur.next.put(str[j], next);
                    }
                } else {
                    cur = next;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
        generateTrieTree(arr);
        print(root, 1);
    }

}
