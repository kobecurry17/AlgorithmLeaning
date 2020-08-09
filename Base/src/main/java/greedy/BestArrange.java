package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲
 * 给你每一个项目的开始时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲场次最多
 * 返回最多的宣讲场次
 */
public class BestArrange {
    /**
     * 宣讲课实体
     */
    public static class Class implements Comparable<Class> {
        Integer start;
        Integer end;

        public Class(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            return end.compareTo(o.end);
        }
    }

    public static class MyComparable implements Comparator<Class> {


        @Override
        public int compare(Class o1, Class o2) {
            if (null == o1) {
                return -1;
            }
            if (null == o2) {
                return 1;
            }
            return o1.end.compareTo(o2.end);
        }
    }


    static Class[] generateClass(int size) {
        Class[] classes = new Class[size];
        int start;
        int end;
        for (int i = 0; i < size; i++) {
            start = (int) (Math.random() * 23);
            end = start + 1 + (int) ((24 - start) * (Math.random()));
            classes[i] = new Class(start, end);
        }
        return classes;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 50;

        for (int i = 0; i < loops; i++) {
            Class[] classes = generateClass((int) ((Math.random() * maxSize) + 1));
            if (maxClass1(classes) != maxClass2(classes)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");

    }

    private static int maxClass2(Class[] classes) {
        return process(classes, 0, 0);
    }

    /**
     * classes 还剩下的会议
     * done 已经进行的会议个数
     * timeline 时间线，已经到达的时间
     *
     * @param classes
     * @param done
     * @param timeLine
     * @return
     */
    private static int process(Class[] classes, int done, int timeLine) {
        if (null == classes || classes.length == 0) {
            return done;
        }
        Class[] cls;
        int max = done;
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].start >= timeLine) {
                cls = copyArrayButExcept(classes, i);
                max = Math.max(max, process(cls, done + 1, classes[i].end));
            }
        }
        return max;
    }

    /**
     * 生成新数组，并剔除不符合条件的元素
     *
     * @param classes
     * @param index
     * @return
     */
    private static Class[] copyArrayButExcept(Class[] classes, int index) {
        Class[] cls = new Class[classes.length - 1];
        int k = 0;
        for (int i = 0; i < classes.length; i++) {
            if (i != index) {
                cls[k++] = classes[i];
            }
        }
        return cls;
    }

    /**
     * 最多能安排多少场宣讲
     *
     * @param classes
     * @return
     */
    private static int maxClass1(Class[] classes) {
        int max = 0;
        int timeLine = 0;
        MyComparable myComparable = new MyComparable();
        PriorityQueue<Class> queue = new PriorityQueue(myComparable);
        for (Class c : classes) {
            queue.add(c);
        }
        while (timeLine<24&&(!queue.isEmpty()))
        {
            Class poll = queue.poll();
            if(poll.start>=timeLine){
                timeLine = poll.end;
                max++;
            }
        }
        return max;
    }


}
