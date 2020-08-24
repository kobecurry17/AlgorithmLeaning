package trymodel;

/**
 * 背包问题
 * <p>
 * 给定两个长度都为N的数组 weights 和 values
 * 给定一个正数bags,表示一个载重为bag的袋子
 * 你装的物品不能比bags大
 * 返回你能装下的最大价值是多少？
 * </p>
 */
public class BagQuestion {


    public static int maxValue1(int[] weights, int[] values, int bags) {
        if (weights.length == 0 || values.length == 0 || bags <= 0) {
            return 0;
        }
        return process(weights, values, 0, bags, 0);
    }

    private static int process(int[] weights, int[] values, int value, int rest, int index) {
        if (index >= weights.length || rest <= 0) {
            return 0;
        }
        int yes = 0;
        if (weights[index] <= rest) {
            yes = values[index] + process(weights, values, value + values[index], rest - weights[index], index + 1);
        }
        int no = process(weights, values, value, rest, index + 1);
        return Math.max(yes, no);
    }

    // for test
    public static int[] generate(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxWeight = 30;
        int maxValue = 300;
        int maxSize = 10;
        int maxBags = 50;
        for (int i = 0; i < loops; i++) {
//            int size = (int) (Math.random() * maxSize);
            int size = 4;
            int[] values = generate(size, maxValue);
            int[] weights = generate(size, maxWeight);
            int bags = (int) (Math.random() * maxBags);
//            if (maxValue1(weights, values, bags) == 0) {
//                System.out.println("Oops!");
//            }
            System.out.println(maxValue1(weights, values, bags));
        }
    }


}
