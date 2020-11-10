package InterviewStage2.class15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 有N台咖啡机，数组中每个数字带包咖啡机生产1杯咖啡的实际，咖啡只能串行生产
 * 有M个人（无差别），求每个人最早获取咖啡的时间
 * 目标是所有人获取咖啡的实际都尽量早
 * </p>
 */
public class Coffee {

    public static class Machine {
        int machineIndex;
        int timeLine;

        public Machine(int machineIndex, int timeLine) {
            this.machineIndex = machineIndex;
            this.timeLine = timeLine;
        }
    }

    private static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return ((Integer) o1.timeLine).compareTo(o2.timeLine);
        }
    }

    public static int[] coffee(int[] machine, int peopleSize) {
        if (machine == null || peopleSize == 0 || machine.length == 0) {
            return new int[0];
        }
        int[] ans = new int[peopleSize];
        PriorityQueue<Machine> heap = new PriorityQueue(new MachineComparator());
        for (int i = 0; i < machine.length; i++) {
            heap.add(new Machine(i, machine[i]));
        }
        int index = 0;
        while (!heap.isEmpty() && index < peopleSize) {
            Machine coffee = heap.poll();
            ans[index++] = coffee.timeLine;
            coffee.timeLine = coffee.timeLine + machine[coffee.machineIndex];
            heap.add(coffee);
        }
        return ans;
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        System.out.println("Test begin ....");
        int loops = 50_0000;
        int maxValue = 50;
        int maxSize = 10;
        for (int i = 0; i < loops; i++) {
            int[] arr = generate(maxSize, maxValue);
            print(coffee(arr, 100));
        }
        System.out.println("Nice");
    }


}
