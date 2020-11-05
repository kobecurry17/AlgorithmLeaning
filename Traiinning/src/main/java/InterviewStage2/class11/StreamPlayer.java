package InterviewStage2.class11;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>
 * 流播放器
 * </p>
 * 一种消息接收并打印的结构设计
 * 已知一个消息流会不断突出整数1~N,但不一定按顺序吐出。
 * 如果上次打印的数为i，那么当i+1出现时，请打印i+1及其之后接收过的并且连续的数
 * 直到1~N全部接收并打印完。
 * 请设计这种接收并打印的结构。
 * 初始时默认i==0
 */
@SuppressWarnings("all")
public class StreamPlayer {

    public static class Stream {

        public int size;

        boolean[] flag;

        int last;

        public Stream(int size) {
            this.size = size + 1;
            flag = new boolean[size + 1];
            last = 1;
        }

        public void input(int num) {
            boolean print = false;
            flag[num] = true;
            while (last < size && flag[last]) {
                if (flag[last]) {
                    print = true;
                    System.out.print(last++ + " ,");
                }
            }
            if (print) {
                System.out.print("\b\n========================================\n");
            }
        }
    }

    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }


    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<Integer, Node>();
            tailMap = new HashMap<Integer, Node>();
            waitPoint = 1;
        }

        // 消息的编号，info消息的内容, 消息一定从1开始
        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            // num~num
            headMap.put(num, cur);
            tailMap.put(num, cur);
            // 建立了num~num这个连续区间的头和尾
            // 查询有没有某个连续区间以num-1结尾
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            // 查询有没有某个连续区间以num+1开头的
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                tailMap.remove(num);
                headMap.remove(num + 1);
            }
            if (num == waitPoint) {
                print();
            }
        }

        private void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }

    }


    public static void main(String[] args) {

        int max = generate(50);

        HashSet<Integer> set = new HashSet<>();
        Integer num = generate(max);
        Integer size = num;
        Stream stream = new Stream(size);
        for (int i = 0; i < size; i++) {
            while (set.contains(num) && set.size() != max) {
                num = generate(size - 1);
            }
            stream.input(num);
            set.add(num);
        }


        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();

        box.receive(2, "B"); // - 2"
        box.receive(1, "A"); // 1 2 -> print, trigger is 1

        box.receive(4, "D"); // - 4
        box.receive(5, "E"); // - 4 5
        box.receive(7, "G"); // - 4 5 - 7
        box.receive(8, "H"); // - 4 5 - 7 8
        box.receive(6, "F"); // - 4 5 6 7 8
        box.receive(3, "C"); // 3 4 5 6 7 8 -> print, trigger is 3

        box.receive(9, "I"); // 9 -> print, trigger is 9

        box.receive(10, "J"); // 10 -> print, trigger is 10

        box.receive(12, "L"); // - 12
        box.receive(13, "M"); // - 12 13
        box.receive(11, "K"); // 11 12 13 -> print, trigger is 11

    }


}
