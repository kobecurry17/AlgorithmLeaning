package fibonacci;


public class Cow {

    /**
     * 成熟奶牛每隔一年生一头母牛，母牛三年成熟，N年后有多少头牛
     * F(n)=F(n-1)+f(n-3)
     *
     * @param year
     * @return
     */
    public static int cowNum1(int year) {
        if (year < 3) {
            return 1;
        }
        return cowNum1(year - 1) + cowNum1(year - 3);
    }

    /**
     * 理论上结果等于一个四阶矩阵的n-2次幂与系数即可得到结果
     * 矩阵泰国复杂就不算了
     *
     * @param year
     * @return
     */
    public static int cowNum2(int year) {
        if (year < 3) {
            return 1;
        }
        int res = 0;
        return res;
    }


    // for test
    public static int generate(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxNum = 30;
        for (int i = 1; i < 30; i++) {
            System.out.println(i + "-" + cowNum1(i));
        }
    }

}
