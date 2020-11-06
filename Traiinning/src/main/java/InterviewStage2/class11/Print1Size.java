package InterviewStage2.class11;

/**
 * <p>
 * 给定一个正数N,在纸上按顺序写下1~N
 * 问：一共写下了多少个1
 * </p>
 */
public class Print1Size {


    public static int oneSize1(int N) {
        int size = 0;
        for (int i = 0; i <= N; i++) {
            int n = i;
            while (n > 0) {
                size += n % 10 == 1 ? 1 : 0;
                n /= 10;
            }
        }
        return size;
    }

    /**
     * 理清所有可能性再开始写！
     * @param N
     * @return
     */
    public static int oneSize2(int N) {
        int tenPower = 1;
        int num = N;
        int cur = 0;
        while (num > 0) {
            tenPower *= 10;
            num /= 10;
        }
        int ans = 0;
        // 前面的数对当前的数的影响
        int prefix = 0;
        num = N;
        while (tenPower > 0) {
            ans += prefix * tenPower;
            cur = num / tenPower;
            if (cur == 1) {
                ans += num % tenPower + 1;
            } else if (cur != 0) {
                ans += tenPower / 10 + 1;
            }
            prefix += prefix * 10 + cur;
            num = N % tenPower;
            tenPower /= 10;
        }
        return ans;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 1; i < loops; i++) {
            if(oneSize1(i)!=oneSize2(i)){
                System.out.println(i);
            }
        }
    }
}
