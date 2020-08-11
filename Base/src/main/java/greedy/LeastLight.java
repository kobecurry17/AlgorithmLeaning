package greedy;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由'X'和'.'两种字符组成
 * 'X'表示墙，不能放灯，也不需要点亮
 * '.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要多少灯
 */
@SuppressWarnings("all")
public class LeastLight {

    /**
     * 暴力解法
     *
     * @param lights
     * @return
     */
    private static Integer minLight1(char[] lights) {
        HashSet<Integer> set = new HashSet<>();
        return process(lights, 0, set);
    }

    private static Integer process(char[] lights, int index, HashSet<Integer> set) {
        if (index == lights.length) {
            // 如果到尾了
            for (int i = 0; i < lights.length; i++) {
                if (lights[i] == '.') {
                    if (!set.contains(i - 1) &&
                            !set.contains(i) &&
                            !set.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return set.size();
        } else {
            // 没有到尾
            int no = process(lights, index + 1, set);
            int yes = Integer.MAX_VALUE;
            if (lights[index] == '.') {
                set.add(index);
                yes = process(lights, index + 1, set);
                set.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 优化解法
     * 局部最优即全局最优
     * 一个灯如果它之前有灯未点亮，则它必须点亮
     * 一个灯如果它之后有未点亮的灯，则他不需要点亮
     *
     * @param lights
     * @return
     */
    private static Integer minLight2(char[] lights) {
        int ans = 0;
        boolean[] need = new boolean[lights.length];
        if(lights.length>1&&lights[1] =='X'){
            ans++;
            need[0] =true;
        }
        for (int i = 1; i < lights.length; i++) {
            if (lights[i] == '.') {
                if(need[i-1]){
                    continue;
                }
                if ((lights[i-1] == '.' && !need[i-1])||(i+1<lights.length&&lights[i+1]=='X')){
                    need[i] = true;
                    ans++;
                }
            }
        }
        return ans;
    }


    /**
     * for test
     *
     * @param length
     * @return
     */
    public static char[] generate(int length) {
        char[] str = new char[length];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.3 ? 'X' : '.';
        }
        return str;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 20;

        for (int i = 0; i < loops; i++) {
            char[] lights = generate(((int) (maxSize * Math.random())));
            Integer l1 = minLight1(lights);
            Integer l2 = minLight2(lights);
            if (l1 != l2 && l1.equals(l2)) {
                System.out.println("Oops");
            }
            System.out.println(1);
        }
        System.out.println("Nice");
    }


}
