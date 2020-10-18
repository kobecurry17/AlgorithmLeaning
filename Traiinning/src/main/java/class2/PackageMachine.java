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
public class PackageMachine {

    /**
     * @param goods
     * @return
     */
    public static int minOps(int[] goods) {
        int sum = 0;
        for (int i = 0; i < goods.length; i++) {
            sum += goods[i];
        }
        if (sum % goods.length != 0) {
            return -1;
        }
        int averageGoods = sum / goods.length;
        int leftSum = 0;
        int leftRest = 0;
        int rightRest = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < goods.length; i++) {
            leftRest = leftSum - i * averageGoods;
            rightRest = (sum - leftSum - goods[i]) - (goods.length - i - 1) * averageGoods;
            leftSum += goods[i];
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest + rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
        }
        return ans;
    }



    // for testing
    public static int[] generate(int maxSize, int max) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 10;
        int maxValue = 10;
        int[] goods = generate(maxLength, maxValue);
        System.out.println("Nice");
    }

}
