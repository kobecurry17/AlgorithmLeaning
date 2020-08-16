package dp;

import java.util.Stack;

/**
 * 汉诺塔
 */
public class Hanoi {

    /**
     * n 层汉诺塔从from 转移到to需要怎么操作
     * 递归实现
     *
     * @param level
     * @param from
     * @param to
     * @param other
     */
    public static void hanoi1(int level, String from, String to, String other) {
        if (level < 1) {
            return;
        }
        process1(level, from, to, other);
    }

    private static void process1(int level, String from, String to, String other) {
        if (level == 1) {
            System.out.println("转移1从" + from + "到" + to);
            return;
        }
        process1(level - 1, from, other, to);
        process1(1, from, to, other);
        process1(level - 1, other, to, from);
    }

    private static class Record {
        public boolean finish1;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish1 = f1;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

    /**
     * 堆栈实现
     *
     * @param level
     */
    public static void hanoi2(int level) {
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, level, "左", "右", "中"));

        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                System.out.println("移动1从" + cur.from + "到" + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            } else {
                if (cur.finish1) {
                    System.out.println("移动" + cur.base + "从" + cur.from + "到" + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                } else {
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                }
            }
        }
    }

    public static void main(String[] args) {
        hanoi1(3, "左", "右", "中");
        System.out.println("==========================================");
        hanoi2(3);
    }


}
