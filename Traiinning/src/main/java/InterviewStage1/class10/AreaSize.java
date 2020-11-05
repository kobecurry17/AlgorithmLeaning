package InterviewStage1.class10;

import java.util.*;

/**
 * <p>
 * 一个数组中，如果两个数的公共因子有大于1的，这认为这两个数之间有通路
 * 返回数组中，有多少个独立的域
 * </p>
 */
@SuppressWarnings("all")
public class AreaSize {


    /**
     * 暴力解
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int maxArea1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                return 1;
            }
        }
        UnionSet unionSet = new UnionSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (maxCommonDivisor2(arr[i], arr[j]) != 1) {
                    unionSet.union(i, j);
                }
            }
        }
        return unionSet.sizeMap.size();
    }


    /**
     * 暴力解
     *
     * @return
     */
    public static int maxSize(int[] arr) {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        return list.size();

    }


    /**
     * 求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int maxCommonDivisor(int a, int b) {
        int min = a < b ? a : b;
        for (int i = min; i > 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 求两个数的最大公约数,辗转相除法
     *
     * @param a
     * @param b
     * @return
     */
    public static int maxCommonDivisor2(int a, int b) {
        int min = a < b ? a : b;
        int max = a == min ? b : a;
        int rem;
        while (min > 0) {
            rem = max % min;
            max = min;
            min = rem;
        }
        return max;
    }

    /**
     * 由Hash表做的并查集
     */
    public static class UnionSet {

        private Map<Integer, Integer> parentMap;
        private Map<Integer, Integer> sizeMap;

        public UnionSet(int size) {
            sizeMap = new HashMap<>();
            parentMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                sizeMap.put(i, 1);
                parentMap.put(i, i);
            }
        }


        public int findHead(int index) {
            Stack<Integer> stack = new Stack<>();
            while (index != parentMap.get(index)) {
                stack.push(index);
                index = parentMap.get(index);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), index);
            }
            return index;
        }

        public void union(int aIndex, int bIndex) {
            Integer aParent = findHead(aIndex);
            Integer bParent = findHead(bIndex);
            if (aParent != bParent) {
                Integer aSize = sizeMap.get(aParent);
                Integer bSize = sizeMap.get(bParent);

                int big = aSize > bSize ? aParent : bParent;
                int small = aSize > bSize ? bParent : aParent;

                sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                sizeMap.remove(small);
                parentMap.put(small, big);
            }
        }


    }

    /**
     * 由数组做的并查集
     */
    public static class UnionSet2 {

        private int[] parents;
        private int[] size;

        public int maxSize() {
            int ans = 0;
            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == i) {
                    ans++;
                }
            }
            return ans;
        }


        public UnionSet2(int len) {
            parents = new int[len];
            size = new int[len];
            for (int i = 0; i < len; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 寻找头部，返回时进行优化
         *
         * @param index
         * @return
         */
        public int findHead(int index) {
            Stack<Integer> stack = new Stack<>();
            while (parents[index] != index) {
                stack.push(index);
                index = parents[index];
            }
            while (!stack.isEmpty()) {
                parents[stack.pop()] = index;
            }
            return index;
        }


        public void union(int a, int b) {
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead != bHead) {
                int max = size[aHead] > size[bHead] ? aHead : bHead;
                int min = size[aHead] > size[bHead] ? bHead : aHead;
                size[max] = size[min] + size[max];
                size[min] = 0;
                parents[min] = max;
            }
        }
    }


    public static int maxArea2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                return 1;
            }
        }
        UnionSet2 unionSet = new UnionSet2(arr.length);
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int limit = (int) Math.sqrt(arr[i]);
            for (int j = 2; j <= limit; j++) {
                if (arr[i] % j == 0) {
                    Integer last = cache.get(j);
                    if (null != last) {
                        unionSet.union(last, i);
                    }
                    last = cache.get(arr[i] / j);
                    if (null != last) {
                        unionSet.union(last, i);
                    }
                    cache.put(j, i);
                    cache.put(arr[i] / j, i);
                }
            }
            if (cache.containsKey(arr[i])) {
                unionSet.union(i, cache.get(arr[i]));
            }
            cache.put(arr[i], i);
        }
        return unionSet.maxSize();
    }


    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void print(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("\b}");
        System.out.print("\n==================\n");
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, 30);
            if (maxArea1(arr) != maxArea2(arr)) {
                print(arr);
                maxArea1(arr);
                maxArea2(arr);
            }
        }
        System.out.println("Nice");
    }

}
