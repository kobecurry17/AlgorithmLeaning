package datastructure;

import java.util.*;

/**
 * 最大堆
 */
public class MaxHeap2 {


    public static class MyMaxHeap<T> {

        private List<T> heap;

        private int heapSize;

        private Map<T, Integer> indexMap;

        private Comparator<? super T> comparator;


        public MyMaxHeap(Comparator<? super T> com) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            comparator = com;
        }

        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            insertHeap(valueIndex);
            heapify(valueIndex, heapSize);
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        // 思路，把头和尾交换，然后把头下沉，直到没有子结点或者没有比他小的数
        public T pop() {
            if (heapSize < 1) {
                throw new RuntimeException("最大堆为空");
            }
            T MAX = heap.get(0);
            swap(indexMap, heap, 0, heapSize);
            heapify(0, --heapSize);
            return MAX;
        }

        private void heapify(int index,int  heapSize) {
            int left = index << 1 | 1;
            while (left < heapSize) {
                int max = left + 1 < heapSize ? comparator.compare(heap.get(left), heap.get(left + 1)) > 0 ? left : left + 1 : left;
                if (comparator.compare(heap.get(max), heap.get(index)) < 0) {
                    break;
                }
                swap(indexMap, heap, index, max);
                index = max;
                left = index << 1 | 1;
            }
        }


        public void push(T t) {

            heap.set(heapSize++, t);
            insertHeap(heapSize - 1);
            return;
        }

        private void insertHeap(int i) {
            while (i > 0 && comparator.compare(heap.get(i), heap.get((i - 1) / 2)) > 0) {
                swap(indexMap, heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private static <T> void swap(Map<T, Integer> indexMap, List<T> heap, int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
            heap.set(i, o2);
            heap.set(j, o1);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }


    }

    public static class Student {
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }

    }

    public static class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }

    }

    public static void main(String[] args) {
        Student s1 = null;
        Student s2 = null;
        Student s3 = null;
        Student s4 = null;
        Student s5 = null;
        Student s6 = null;

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);
        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        MyMaxHeap<Student> myHeap = new MyMaxHeap<>(new StudentComparator());
        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);
        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        heap = new PriorityQueue<>(new StudentComparator());

        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);

        s2.age = 6;
        s4.age = 12;
        s5.age = 10;
        s6.age = 84;

        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        myHeap = new MyMaxHeap<>(new StudentComparator());

        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);

        s2.age = 6;
        myHeap.resign(s2);
        s4.age = 12;
        myHeap.resign(s4);
        s5.age = 10;
        myHeap.resign(s5);
        s6.age = 84;
        myHeap.resign(s6);

        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }


        // 对数器
        System.out.println("test begin");
        int maxValue = 100000;
        int pushTime = 1000000;
        int resignTime = 100;
        MyMaxHeap<Student> test = new MyMaxHeap<>(new StudentComparator());
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < pushTime; i++) {
            Student cur = new Student(1, (int) (Math.random() * maxValue), 1000);
            list.add(cur);
            test.push(cur);
        }
        for (int i = 0; i < resignTime; i++) {
            int index = (int) (Math.random() * pushTime);
            list.get(index).age = (int) (Math.random() * maxValue);
            test.resign(list.get(index));
        }
        int preAge = Integer.MIN_VALUE;
        while (test.isEmpty()) {
            Student cur = test.pop();
            if (cur.age < preAge) {
                System.out.println("Oops!");
            }
            preAge = cur.age;
        }
        System.out.println("test finish");


    }

}
