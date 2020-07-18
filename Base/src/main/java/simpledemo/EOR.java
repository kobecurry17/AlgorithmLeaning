package simpledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目：一堆数字中只有一个数字出现了奇数次其他都出现了偶数次，找到这个数字
 */
public class EOR {

    public static void main(String[] args) {

        List<Integer> integers = generateArray(22000);
        System.out.println(find(integers.toArray()));
    }

    static List<Integer> generateArray(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size ; i ++) {
            Integer i1 = (int) (Math.random() * Integer.MAX_VALUE);
            list.add(i1);
            list.add(i1);
        }
        Integer remove = list.remove(list.size()-1);
        System.out.println("remove="+remove);
        List<Integer> collect = list.stream().sorted((x, y) -> Math.random() > 0.5f ? 1 : -1).collect(Collectors.toList());
        return collect;

    }


    static int find(Object[] arr) {

        int result = (int) arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = result ^ (int) arr[i];
        }
        return result;
    }

}
