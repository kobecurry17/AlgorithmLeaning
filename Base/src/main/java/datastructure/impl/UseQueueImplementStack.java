package datastructure.impl;

import lombok.Data;
import org.apache.commons.lang3.time.StopWatch;

import java.util.LinkedList;
import java.util.Stack;

@Data
public class UseQueueImplementStack<T> {

    private LinkedList<T> queue;
    private LinkedList<T> queueBackUp;


    public UseQueueImplementStack() {
        queue = new LinkedList<>();
        queueBackUp = new LinkedList<>();
    }

    public int size() {
        return queueBackUp.size() + queue.size();
    }

    public T pop() {
        if (0 == size()) {
            throw new RuntimeException("队列为空");
        }
        if (queue.size() > 0) {
            while (queue.size() > 1) {
                queueBackUp.addFirst(queue.removeLast());
            }
        }
        T t = queue.removeLast();
        queue = queueBackUp;
        queueBackUp = new LinkedList<>();
        return t;
    }

    public void push(T t) {
        queue.addFirst(t);
    }

    public static void push(Stack<Integer> stack, UseQueueImplementStack<Integer> myStack, int value) {
        Integer num = (int) (Math.random() * value);
        stack.add(num);
        myStack.push(num);
    }

    public static void pop(Stack<Integer> stack, UseQueueImplementStack<Integer> myStack) {
        Integer pop1 = stack.pop();
        Integer pop2 = myStack.pop();
        if (!pop1.equals(pop2)) {
            throw new Error("出错了");
        }
    }

    public static void main(String[] args) {
        int size = 500000;
        int len = 80;
        int value = 500;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < size; i++) {
            UseQueueImplementStack<Integer> myStack = new UseQueueImplementStack();
            Stack<Integer> stack = new Stack<>();
            int pushSize = 0;
            for (int j = 0; j < len; j++) {
                if (pushSize == 0) {
                    push(stack, myStack, value);
                    pushSize++;
                } else {
                    if (Math.random() > 0.7) {
                        push(stack, myStack, value);
                        pushSize++;
                    } else {
                        pop(stack, myStack);
                        pushSize--;
                    }
                }
            }
        }
        stopWatch.stop();
        System.out.println(String.format("Nice----------------------%d", stopWatch.getTime()));
    }
}
