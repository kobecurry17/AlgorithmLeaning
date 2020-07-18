package datastructure.impl;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TimerTask;

/**
 * 使用堆栈实现队列
 */
@Data
public class UseStackImplementQueue<T> {

    private Stack<T> stack;
    private Stack<T> stackBackUp;

    public UseStackImplementQueue() {
        this.stack = new Stack<>();
        this.stackBackUp = new Stack<>();
    }

    /**
     * 弹出
     *
     * @return 队列尾元素
     */
    public T pop() {
        if (stack.isEmpty() && stackBackUp.isEmpty()) {
            throw new NullPointerException("队列是空的！");
        }
        T t;
        if (stack.isEmpty()) {
            t = stackBackUp.pop();
        } else {
            while (stack.size() > 1) {
                stackBackUp.push(stack.pop());
            }
            t = stack.pop();
        }
        return t;
    }

    public void push(T t) {
        if (!stackBackUp.isEmpty()) {
            while (stackBackUp.size() > 0) {
                stack.push(stackBackUp.pop());
            }
        }
        stack.push(t);
    }

    public static void push(LinkedList<Integer> queue, UseStackImplementQueue<Integer> myQueue, int value) {
        Integer num = (int) (Math.random() * value);
        queue.addFirst(num);
        myQueue.push(num);
    }

    public static void pop(LinkedList<Integer> queue, UseStackImplementQueue<Integer> myQueue) {
        Integer integer = queue.removeLast();
        Integer pop = myQueue.pop();
        if (!integer.equals(pop)) {
            throw new Error("出错了");
        }
    }

    public static void main(String[] args) {
        int size = 500000;
        int len = 80;
        int value = 500;
        for (int i = 0; i < size; i++) {
            LinkedList<Integer> queue = new LinkedList<>();
            UseStackImplementQueue<Integer> myQueue = new UseStackImplementQueue();
            int pushSize = 0;
            for (int j = 0; j < len; j++) {
                if (pushSize == 0) {
                    push(queue, myQueue, value);
                    pushSize++;
                } else {
                    if (Math.random() > 0.7) {
                        push(queue, myQueue, value);
                        pushSize++;
                    } else {
                        pop(queue, myQueue);
                        pushSize--;
                    }
                }
            }
        }
        System.out.println("Nice");
    }

}
