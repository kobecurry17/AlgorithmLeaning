package InterviewStage2.class15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 有N台咖啡机，数组中每个数字带包咖啡机生产1杯咖啡的实际，咖啡只能串行生产
 * 有M个人（无差别），求每个人最早获取咖啡的时间
 * 目标是所有人获取咖啡的实际都尽量早
 * </p>
 * TODO:确认题目
 */
public class Coffee {

    public static class Machine {
        int peopleIndex;
        int machineIndex;
        int timeLine;

        public Machine(int peopleIndex, int machineIndex, int timeLine) {
            this.peopleIndex = peopleIndex;
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

        }
        return new int[0];
    }


}
