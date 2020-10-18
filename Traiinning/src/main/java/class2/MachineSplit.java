package class2;

/**
 * 有n个打包机器，上方有一个自动装置会抓取一批物品进行分配
 * 假设现在有3台机器，货物情况是 [ 5, 0, 1]
 * 现抓取装置需要对机器内货物数量进行平分，问需要几轮可以完成平分工作
 * <p>
 * 例如:
 * [ 5, 0, 1] => [ 4, 1, 1] => [ 3, 1, 2] => [ 2, 2, 2]
 * 移动三轮后，机器内货物数量一致，因此返回3
 * </p>
 * <p>
 * 例如:
 * [ 2, 2, 3]
 * 无论如何移动无法做到平均，因此返回-1表示异常
 * </p>
 */
public class MachineSplit {

    /**
     * @param goods
     * @return
     */
    public static int splitCycle(int[] goods) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < goods.length; i++) {
            sum += goods[i];
            max = Math.max(max, goods[i]);
            min = Math.min(min, goods[i]);
        }
        if (sum % goods.length != 0) {
            return -1;
        }
        int averageGoods = sum / goods.length;
        return Math.max(Math.abs(max - averageGoods), Math.abs(averageGoods - min));
    }

    public static void main(String[] args) {
        int[] goods = {6, 0, 0, 0, 0, 0};
        System.out.println(splitCycle(goods));
    }

}
