package simpledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EOR2 extends EOR {

    public static void main(String[] args) {
        List<Integer> integers = generateArray(500000);
        findTwoNumber(integers.toArray());
    }

    static List<Integer> generateArray(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer i1 = (int) (Math.random() * Integer.MAX_VALUE);
            list.add(i1);
            list.add(i1);
        }
        Integer remove1 = list.remove(list.size() - 1);
        System.out.println("remove1=" + remove1);
        List<Integer> collect = list.stream().sorted((x, y) -> Math.random() > 0.5f ? 1 : -1).collect(Collectors.toList());
        Integer remove2 = collect.remove(list.size() - 1);
        System.out.println("remove2=" + remove2);
        return collect;
    }


    static void findTwoNumber(Object[] arr) {

        int result = (int) arr[0];
        for (int i = 1; i < arr.length; i++) {
            // result = a^b
            result = result ^ (int) arr[i];
        }
        int i1 = result & (~result + 1);
        int resultA=0;
        for (int i = 0; i < arr.length; i++) {
            if (((int) arr[i] & i1) == 0) {
                resultA = ((int)arr[i])^resultA;
            }
        }
        System.out.println(String.format("%s++++%s",resultA,result^resultA));
    }


}
