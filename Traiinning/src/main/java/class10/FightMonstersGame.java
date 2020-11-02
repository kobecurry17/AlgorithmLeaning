package class10;

/**
 * <p>
 * int[] d, d[i]:i号怪兽的能力
 * int[] p, p[i]:p号怪兽的所需要的金钱
 * 开始时你的能力是0,你的目标是从0好怪兽开始，通过所有的怪兽
 * 如果你当前的能力小于i号怪兽的能力，你必须付出p[i]的钱，收买这个怪兽，然后怪兽的能力
 * 会叠加到你的能力上
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过
 * 你也可以选择收买这个怪兽，他的能力直接累加到你的能力上
 * 返回通过所有的怪兽，需要花的最小钱数
 * </p>
 */
public class FightMonstersGame {

    public static int minMoney(int[] d, int[] p) {
        if (null == d || null == p || d.length != p.length || p.length == 0) {
            return 0;
        }
        return process(d, p, 0, 0, 0);
    }

    private static int process(int[] d, int[] p, int money, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        int p1 = process(d, p, money + p[index], ability + d[index], index++);
        if (ability < d[index]) {
            return p1;
        }
        int p2 = process(d, p, money, ability, index++);
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        int[] d = new int[]{3, 2, 5, 2, 1};
        int[] p = new int[]{3, 1, 100, 2, 1};
        System.out.println(minMoney(d, p));
    }


}
