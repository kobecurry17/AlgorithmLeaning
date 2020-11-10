package InterviewStage2.class15;

import java.util.HashMap;

/**
 * 给定一个数组arr,如果某个数出现次数超过了数组长度的一半,打印这个数,输入没有不打印
 * <p>
 * 给定一个数组arr和整数k，arr长度为N,如果有某些数出现次数超过了N/K
 * 打印这些数,如果没有不打印
 * </p>
 * 你有一台机器，判断两个信息是否相等，现在给你一堆选票，判断哪个选票占有率大于50%
 */
@SuppressWarnings("all")
public class OneNumAppearMoreThanK {


    public static int appearMoreThanHalf1(int[] arr) {
        int head1 = 0;
        int head2 = 1;
        // 用两个指针,同时从头开始,一个从尾开始
        // 当两个指针值不相同时,把对应位置置为null
        // 当位置相同时,移动位置大的指针
        boolean[] status = new boolean[arr.length];
        while (head1 < arr.length && head2 < arr.length) {
            if (status[head1]) {
                head1++;
                continue;
            }
            if (head1 == head2 || status[head2]) {
                head2++;
                continue;
            }
            if (arr[head2] != arr[head1]) {
                status[head1++] = true;
                status[head2++] = true;
            } else {
                head2++;
            }
        }

        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (status[i] == false) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            int size = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == arr[index]) {
                    size++;
                }
            }
            if (size > arr.length / 2) {
                return arr[index];
            }
        }
        return Integer.MIN_VALUE;
    }

    public static int appearMoreThanHalf2(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) > arr.length / 2) {
                return key;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static int appearMoreThanHalf3(int[] arr) {
        int candy = 0;
        int HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0) {
                candy = arr[i];
                HP++;
                continue;
            }
            if (arr[i] == candy) {
                HP++;
            } else {
                HP--;
            }
        }

        if (HP == 0) {
            return Integer.MIN_VALUE;
        }
        HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candy) {
                HP++;
            }
        }
        return HP > arr.length / 2 ? candy : Integer.MIN_VALUE;
    }


    /**
     * @param arr
     * @return
     */
    public static int[] appearMoreThanK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }
        int size = 0;
        int index = 0;
        for (Integer key : map.keySet()) {

            if (map.get(key) > arr.length / k) {
                size++;
            }
        }
        int[] ans = new int[size];
        index = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) >= k) {
                ans[index++] = key;
            }
        }
        return ans;
    }


    // for testing
    public static int[] generate(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
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
        int maxLength = 5;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxLength, 3);
            int ans1 = appearMoreThanHalf1(arr);
            int ans2 = appearMoreThanHalf2(arr);
            int ans3 = appearMoreThanHalf3(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                print(arr);
            }
        }
        System.out.println("Nice");

        int[] arr = {1, 2, 3, 1, 1, 2, 1};
        int K = 4;
        print(appearMoreThanK(arr, K));
    }

}
