package dp;

import java.util.Stack;

/**
 * 不使用额外数据结构逆序一个栈
 */
public class ReverseStack<T> {
    /**
     * 得到栈底的元素并删除
     *
     * @param stack
     * @param <T>
     * @return
     */
    public static <T> T getBottomStack(Stack<T> stack) {
        T pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            T bottomStack = getBottomStack(stack);
            stack.push(pop);
            return bottomStack;
        }
    }

    public static <T> void reverse(Stack<T> stack) {
        T bottom = getBottomStack(stack);
        if (stack.isEmpty()) {
            stack.push(bottom);
        } else {
            reverse(stack);
            stack.push(bottom);
        }
    }


    public static Stack<Integer> copy(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return new Stack<>();
        }
        Stack<Integer> copy = new Stack<>();
        copy(stack, copy);
        return copy;
    }

    private static void copy(Stack<Integer> stack, Stack<Integer> copy) {
        Integer pop = stack.pop();
        if (!stack.isEmpty()) {
            copy(stack, copy);
        }
        copy.push(pop);
        stack.push(pop);
    }

    // for test
    public static Stack<Integer> generateStack(int size) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            int value = (int) (Math.random() * 500);
            stack.push(value);
        }
        return stack;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 500;
        for (int i = 0; i < loops; i++) {
            Stack<Integer> stack = generateStack((int) (Math.random() * maxSize)+1);
            Stack<Integer> copy = copy(stack);
            copy = reverse2(copy);
            reverse(stack);
            while (!copy.isEmpty()) {
                if (!copy.pop().equals(stack.pop())) {
                    System.out.println("Oops");
                }
            }
        }
        System.out.println("Nice");

    }

    private static Stack<Integer> reverse2(Stack<Integer> copy) {
        Stack<Integer> stack = new Stack<>();
        while (!copy.isEmpty()) {
            stack.push(copy.pop());
        }
        return stack;
    }


}
