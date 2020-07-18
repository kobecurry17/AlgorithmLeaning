package DataStructure;

import java.util.LinkedList;

public class MinStack {

    private static LinkedList<Integer> stack = new LinkedList<>();
    private static LinkedList<Integer> minStack = new LinkedList<>();
    private static LinkedList<Integer> stupidStack = new LinkedList<>();

    public static void push(Integer num) {
        stack.addFirst(num);
        if (minStack.isEmpty()) {
            minStack.addFirst(num);
        } else {
            Integer peek = minStack.peekFirst();
            if (num < peek) {
                minStack.addFirst(num);
            } else {
                minStack.addFirst(peek);
            }
        }
    }

    public static void pushStupid(Integer num) {
        stupidStack.addFirst(num);
    }

    public static Integer popStupidMin() {
        Integer min = stupidStack.pollFirst();
        LinkedList<Integer> stack = new LinkedList<>();
        Integer pop;
        while (null != (pop = stupidStack.peek())) {
            stupidStack.pop();
            if (pop < min) {
                min = pop;
            }
            stack.addFirst(pop);
        }
        while (null != (pop = stack.peek())) {
            stack.pop();
            stupidStack.addFirst(pop);
        }
        return min;
    }

    public static Integer pop() {
        Integer pop = stack.pollFirst();
        minStack.pollFirst();
        return pop;
    }

    public static Integer popMin() {
        Integer pop = minStack.pollFirst();
        stack.pollFirst();
        return pop;
    }


    public static void main(String[] args) {
        System.out.println(111);
        int times = 500000;
        int value = 500;
        int size = 50;

        for (int j = 0; j < times; j++) {
            for (int i = 0; i < size; i++) {
                int v = (int) (Math.random() * value);
                push(v);
                pushStupid(v);
            }

            for (int i = 0; i < size; i++) {
                Integer integer = popMin();
                Integer integer1 = popStupidMin();
                if (!integer.equals(integer1)) {
                    System.out.println("出错啦");
                }
            }
            System.out.println("执行一次");
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
            stupidStack = new LinkedList<>();
        }
    }
}
