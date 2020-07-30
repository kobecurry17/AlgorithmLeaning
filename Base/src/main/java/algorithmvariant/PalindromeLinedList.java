package algorithmvariant;

import datastructure.Node;
import lombok.Data;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个链表，判断该链表是否为回文结构
 */
public class PalindromeLinedList {
    @Data
    public static class Node<T> {

        private T value;
        private Node next;

        public Node() {
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 使用堆栈实现
     *
     * @param head
     * @return
     */
    public static <T> boolean isPalindromeLinkedListWithStack(Node<T> head) {
        if(null==head){
            return false;
        }
        Stack<T> stack=new Stack<>();
        while (head.next!=null){
            stack.push((T)head.next.getValue());
            head=head.next;
        }
        while (head.next!=null){
            T pop = stack.pop();
            if(!pop.equals(head.next.getValue())){
                return false;
            }
        }
    }

}
