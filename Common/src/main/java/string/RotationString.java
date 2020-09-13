package string;

/**
 * 设定：字符串"123" 有旋转词“123” “231” “312“
 * 问给定的两个字符串s1和s2是否互为旋转词
 */
public class RotationString {


    // for test
    public static String generate(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (int) (Math.random() * 26)));
        }
        return sb.toString();
    }

    /**
     * 思路:
     * 在a+a中寻找b，找到就是有，找不到就是没有
     * 应该先实现KMP算法再做这题
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isRotation(String a, String b) {
        return true;
    }


}
