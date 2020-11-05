package InterviewStage1.class5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 每个信封都有长和框两个维度的数据，A信封如果想嵌套在B信封里面
 * A信封必须长和宽都大于B信封
 * 如果给你一批信封，返回最大的嵌套层数
 */
public class EnvelopeNesting {


    public static int nestingSize2(Envelop[] envelops) {
        Arrays.sort(envelops, new EnvelopComparator());
        int[] dp = new int[envelops.length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = envelops.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < envelops.length; j++) {
                if (valid(envelops[i], envelops[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[0];
    }

    public static class Envelop {
        Integer length;
        Integer width;

        public Envelop() {
        }

        public Envelop(Integer length, Integer width) {
            this.length = length;
            this.width = width;
        }
    }

    public static boolean valid(Envelop e1, Envelop e2) {
        return e1.length > e2.length && e1.width > e2.width;
    }

    public static class EnvelopComparator implements Comparator<Envelop> {
        @Override
        public int compare(Envelop o1, Envelop o2) {
            if (o1.length == o2.length) {
                return o2.width.compareTo(o1.width);
            }
            return o2.length.compareTo(o1.length);
        }
    }

    public static Envelop[] generate() {
        Envelop[] envelops = new Envelop[9];
        envelops[0] = new Envelop(3, 4);
        envelops[1] = new Envelop(2, 3);
        envelops[2] = new Envelop(4, 5);
        envelops[3] = new Envelop(1, 3);
        envelops[4] = new Envelop(2, 2);
        envelops[5] = new Envelop(3, 6);
        envelops[6] = new Envelop(1, 2);
        envelops[7] = new Envelop(3, 2);
        envelops[8] = new Envelop(2, 4);
        return envelops;
    }

    public static void main(String[] args) {
        System.out.println(nestingSize2(generate()));
    }
}
