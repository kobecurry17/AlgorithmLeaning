package trymodel;


/**
 * 字符转换
 * 假设 1-A 2-B 3-C ... 26-Z
 * 那么111 可以转换为 'AAA' 'AK' 'KA'
 * 题目:输入一个字符串,输出一共有多少种转换方式
 */
@SuppressWarnings("all")
public class CharacterTransfer {

    /**
     * 字符串中一共有多少种转换
     *
     * @param str
     * @return
     */
    public static int transformSize(String str) {
        if (null == str || str.trim().equals("")) {
            return 0;
        }
        return process(str, 0);
    }

    public static int transformSize2(String str) {
        if (null == str || str.trim().equals("")) {
            return 0;
        }
        char[] array = str.toCharArray();
        int[] dp = new int[array.length + 1];
        for (int i = array.length-1; i >= 0; i--) {
            if (array[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i]++;
                if (i < array.length - 1) {
                    if (array[i] == '1') {
                        dp[i] += dp[i + 2];
                    }
                    if (array[i] == '2' && array[i + 1] >= 0 && array[i + 1] <= '6') {
                        dp[i] += dp[i + 2];
                    }
                }
                dp[i] += dp[i + 1];
            }
        }
        return dp[0];
    }


    private static int process(String str, int index) {
        int res = 0;
        // base case
        if (index >= str.length()) {
            return 1;
        }
        if (str.charAt(index) == '0') {
            return 0;
        }
        if (index < str.length() - 1) {
            if (str.charAt(index) == '1') {
                res += process(str, index + 2);
            }
            char next = str.charAt(index + 1);
            if (str.charAt(index) == '2' && next >= 0 && next <= '6') {
                res += process(str, index + 2);
            }
        }
        res += process(str, ++index);
        return res;
    }

    public static String generate(int length) {
        long size = 10;
        for (int i = 0; i < length; i++) {
            size *= 10;
        }
        return String.valueOf((long) (Math.random() * size));
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 30;
        for (int i = 0; i < loops; i++) {
            String generate = generate((3));
//            String generate = generate((int) (Math.random() * maxLength));
            int i1 = transformSize(generate);
            int i2 = transformSize2(generate);
            if(i1!=i2){
               System.out.println("Oops!");
           }
        }
        System.out.println("Nice");
    }
}
