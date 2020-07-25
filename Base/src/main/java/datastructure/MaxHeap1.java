package datastructure;

import com.sun.org.glassfish.gmbal.Description;

/**
 * 最大堆
 */
public class MaxHeap1 {


    public static class MyMaxHeap {

        private int[] heap;

        private int heapSize;

        private final int limit;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        // 思路，把头和尾交换，然后把头下沉，直到没有子结点或者没有比他小的数
        @Deprecated
        private Integer WrongPop() {
            if (heapSize < 1) {
                throw new RuntimeException("最大堆为空");
            }
            Integer max = heap[0];
            int index = 0;
            while (index < heapSize) {
                // 左子树大于右子树
                if (heap[(index + 1) << 1] < heap[index << 1 | 1]) {
                    swap(heap, index, index << 1 | 1);
                    index = index << 1 | 1;
                } else {
                    swap(heap, index, (index + 1) << 1);
                    index = (index + 1) << 1;
                }
            }
            return 0;
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        // 思路，把头和尾交换，然后把头下沉，直到没有子结点或者没有比他小的数
        public Integer pop() {
            if (heapSize < 1) {
                throw new RuntimeException("最大堆为空");
            }
            Integer MAX = heap[0];
            swap(heap, 0, --heapSize);
            heapify();
            return MAX;
        }

        public void heapify() {
            int index = 0;
            int left = index << 1 | 1;
            while (left < heapSize) {
                int max = left + 1 < heapSize ? heap[left] > heap[left + 1] ? left : left + 1 : left;
                if (heap[max] < heap[index]) {
                    break;
                }
                swap(heap, index, max);
                index = max;
                left = index << 1 | 1;
            }
        }


        public void push(Integer item) {
            heap[heapSize++] = item;
            insertHeap(heapSize - 1);
            return;
        }

        private void insertHeap(Integer index) {
            int i = index;
            while (i > 0 && heap[i] > heap[(i - 1) / 2]) {
                swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        Integer myPop = my.pop();
                        Integer testPop = test.pop();
                        if (!myPop.equals(testPop)) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
