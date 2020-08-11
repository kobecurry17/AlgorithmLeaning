package greedy;

import java.util.*;

/**
 * 切金条
 * <p>
 * 一块金条被分成20块，是需要花费和长度相同的铜钱
 * 比如长度为20 的金条无论怎么切都需要20个铜钱
 * 问：给定一个数组，代表金条的长度，怎么把金条切成指定长度代价最小
 * 例如：100 分成 {20,30,50} 怎么分代价最小
 * </p>
 */
public class CutGolden<T> {


    private Integer length;
    private PriorityQueue<T> maxStack;
    private Comparator<T> comparator;

    /**
     * 初始化
     *
     * @param length
     * @param list
     * @param comparator
     */
    public CutGolden(Integer length, List<T> list, Comparator<T> comparator) {
        this.length = length;
        this.comparator = comparator;
        this.maxStack = new PriorityQueue<>(comparator);
        list.forEach(i -> maxStack.add(i));
    }

    /**
     * 代价最低的切法
     * 根据自然智慧，每次切的代价是长度总长
     * 所以每次切下剩余部分中最大的部分，即可得到最优解
     *
     * @return
     */
    public int cheaperCut() {
        int cost = 0;
        int l = this.length;
        while (maxStack.size()>1){
            cost +=l;
            l = l -(Integer) maxStack.poll();
        }
        return cost;
    }

    /**
     * 自定义比较器
     */
    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(30, 50, 20);
        CutGolden<Integer> integerCutGolden = new CutGolden<>(100, list, new MyComparator());
        System.out.println(integerCutGolden.cheaperCut());


        list = Arrays.asList(30, 50, 40,50);
        integerCutGolden = new CutGolden<>(170, list, new MyComparator());
        System.out.println(integerCutGolden.cheaperCut());
    }


}
