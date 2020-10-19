package class4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * <p>
 * 每种工作都有难度和报酬
 * 给定一个Job类型数组jobArr，表示所有岗位， 每个岗位都可以提供人一份工作
 * 选工作的标准是在难度不超过只剩能力值的情况下，选择报酬最高的岗位
 * 给定一个int类型的数组arr，表示所有人能力
 * 返回int类型的数组，表示每个人按照标准选工作后能获得的最高报酬
 * </p>
 */
public class MostPay {

    /**
     * 暴力方法
     * O(N*K)
     *
     * @param jobArr
     * @param workers
     * @return
     */
    public static int[] maxPay1(Job[] jobArr, int[] workers) {
        int[] ans = new int[workers.length];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < jobArr.length; j++) {
                if (workers[i] >= jobArr[j].hard) {
                    ans[i] = Math.max(jobArr[j].money, ans[i]);
                }
            }
        }
        return ans;
    }

    /**
     * @param jobArr
     * @param workers
     * @return
     */
    public static int[] maxPay2(Job[] jobArr, int[] workers) {
        Arrays.sort(jobArr, new JobComparator());
        // 难度为key的工作，最优钱数是多少，有序表
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobArr[0].hard, jobArr[0].money);
        Job pre = jobArr[0]; // pre 之前组的组长
        for (int i = 1; i < jobArr.length; i++) {
            if (jobArr[i].hard != pre.hard && jobArr[i].money > pre.money) {
                pre = jobArr[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[workers.length];
        for (int i = 0; i < workers.length; i++) {
            Integer key = map.floorKey(workers[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }


    // for test
    public static Job[] generate(int maxSize) {
        int size = (int) (Math.random() * maxSize) + 1;
        Job[] jobArr = new Job[size];
        for (int i = 0; i < size; i++) {
            jobArr[i] = new Job();
        }
        return jobArr;
    }


    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static class Job {
        int money;
        int hard;

        public Job() {
            money = (int) (Math.random() * 500);
            hard = (int) (Math.random() * 50);
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }

    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 0; i < loops; i++) {
            Job[] jobArr = generate(30);
            int[] workers = generate(30, 30);
            if (!isEqual(maxPay2(jobArr, workers), maxPay1(jobArr, workers))) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");

    }
}
