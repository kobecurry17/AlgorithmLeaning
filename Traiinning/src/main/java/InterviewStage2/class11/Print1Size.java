package InterviewStage2.class11;

/**
 * <p>
 * 给定一个正数N,在纸上按顺序写下1~N
 * 问：一共写下了多少个1
 * </p>
 */
@SuppressWarnings("all")
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


    public static int oneSize3(int num) {
        if (num == 0) {
            return 0;
        }
        // num -> 13625
        // len = 5位数
        int len = getLength(num);
        if (len == 1) {
            return 1;
        }
        // num 13625
        // tmp1 10000
        // num 7872328738273
        // tmp1 1000000000000
        int temp1 = power(len - 1);
        // num最高位 num / tmp1
        int first = num / temp1;
        // 最高1 N % tmp1 + 1
        // 最高位first tmp1
        int firstSize = first == 1 ? num % temp1 + 1 : temp1;
        // 除去最高位之外，剩下1的数量
        // 最高位1 10(k-2次方) * (k-1) * 1
        // 最高位first 10(k-2次方) * (k-1) * first
        int otherSize = first * (temp1 / 10) * (len - 1);
        return firstSize + otherSize + oneSize3(num % temp1);
    }

    private static int power(int len) {
        return (int) Math.pow(10, len);
    }

    private static int getLength(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        for (int i = 1; i < loops; i++) {
            if (oneSize1(i) != oneSize3(i)) {
                System.out.println(i);
            }
        }
        System.out.println("Nice");
    }
}
