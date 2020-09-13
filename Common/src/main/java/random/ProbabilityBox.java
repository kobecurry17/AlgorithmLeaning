package random;

/**
 * 有一个吐球机器，不断的吐出下标加一的球
 * 现在有一个只能装10个球的袋子，问怎么让每个球在袋子里的概率相等
 * 例如:
 * 吐出了100个球，那么每个球在袋子里的概率是10/100
 * 吐出了532个球每个球,那么每个球在袋子里的概率是10/532
 */
public class ProbabilityBox {

    private static class Box {
        private int capacity;
        private int[] bag;
        private int count;

        public Box(int capacity) {
            this.capacity = capacity;
            this.bag = new int[capacity];
        }


        public void add(int num) {
            // 初始状态，每个新加入的数都有 capacity/i 的概率加到池子里
            if (count < num) {
                bag[count++] = num;
                return;
            }
            // 每个新加入的数都有 capacity/i 的概率加到池子里
            if (rand(count++) < capacity) {
                // 每个在池子里的数都有 1/capacity的概率被淘汰
                bag[rand(capacity) - 1] = num;
            }
        }
    }


    // for test
    public static int rand(int maxValue) {
        return (int) (Math.random() * maxValue) + 1;
    }

    public static void main(String[] args) {

    }


}
