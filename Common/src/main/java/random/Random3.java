package random;

/**
 * 给定一个方法，P概率得到0,1-p概率得到1，问怎么获得等概率得到0,1的函数
 */
@SuppressWarnings("all")
public class Random3 {

    /**
     * 10%概率得到1
     * 90%概率得到0
     *
     * @return
     */
    public static int baseRand() {
        return Math.random() < 0.1 ? 1 : 0;
    }

    /**
     * 利用{@link baseRand}得到等概率的0和1
     *
     * @return
     */
    public static int rand() {
        int i = 0;
        int j = 0;
        while (i == j) {
            i = baseRand();
            j = baseRand();
        }
        return i == 1 ? 1 : 0;
    }


    public static void main(String[] args) {
        int[] arr = new int[2];
        for (int i = 0; i < 100_0000; i++) {
            arr[rand()]++;
        }
        printArr(arr);
    }


    // for test
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "=======" + arr[i]);
        }
    }


}
