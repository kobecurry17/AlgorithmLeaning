package algorithmvariant;

import javafx.scene.chart.ValueAxis;
import lombok.Data;

import java.util.HashMap;

/**
 * 深拷贝以下结构
 * public static  class Node{
 * int value;
 * Node next; 下一个结点
 * Node rand; 随机结点
 * }
 * publci static
 */
public class DeepClone {

    @Data
    public static class Node {
        int value;
        Node next;
        Node rand;
    }

    public Node clone1(Node head) {
//        HashMap<>
        while (head.next != null) {

        }


        return new Node();
    }

}
