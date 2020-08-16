package dp;

import java.util.Stack;

/**
 * 不使用额外空间逆序栈
 */
public class ReverseStackWithoutSpace {

    public static void main(String[] args) {
        int size = 50;
        Stack<Integer> stack = generateStack(size);
        System.out.println(stack);
        Stack<Integer> stack1 = reverse(stack);
        System.out.println(stack);
    }

    /**
     * 逆序栈
     *
     * @param stack
     * @return
     */
    private static Stack<Integer> reverse(Stack<Integer> stack) {
        process(stack);
        return null;
    }

    private static void process(Stack<Integer> stack) {
        Integer num = null;
        if (!stack.isEmpty()) {
            num = stack.pop();
            process(stack);
        }

    }

    // for test
    private static Stack<Integer> generateStack(int size) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            stack.add((int) (Math.random() * 500));
        }
        return stack;
    }

}
