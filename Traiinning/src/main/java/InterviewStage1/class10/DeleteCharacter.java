package InterviewStage1.class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * <p>
 * 给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个
 * 并让最终结果字符串的字典序最小
 * </p>
 * <p>
 * 【举例】
 * str = "acbc" , 删掉第一个 'c'，得到 "abc" 是所有结果字符串中字典序最小的
 * str = "dbcacbca" ,删掉第一个'b',第一个'c',第二个'c',第二个'a'，得到'dabc'是所有结果字符串中字典序最小的
 * </p>
 */
@SuppressWarnings("all")
public class DeleteCharacter {

    /**
     * 暴力方法
     * O(N^3)
     *
     * @param s
     * @return
     */
    public static String minString1(String s) {
        PriorityQueue<String> queue = new PriorityQueue<>(new StringComparator());
        char[] arr = s.toCharArray();
        process(arr, 0, "", queue, s);
        return queue.poll();
    }


    private static void process(char[] arr, int fromIndex, String path, PriorityQueue<String> queue, String origin) {
        if (fromIndex == arr.length) {
            if (right(path, origin)) {
                queue.add(path);
            }
            return;
        }
        process(arr, fromIndex + 1, path, queue, origin);
        process(arr, fromIndex + 1, path + arr[fromIndex], queue, origin);
    }

    private static boolean right(String path, String origin) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < origin.length(); i++) {
            set.add(origin.charAt(i));
        }
        for (int i = 0; i < path.length(); i++) {
            if (!set.contains(path.charAt(i))) {
                return false;
            }
            set.remove(path.charAt(i));
        }
        return set.isEmpty();
    }


    public static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int length = Math.min(o1.length(), o2.length());
            for (int i = 0; i < length; i++) {
                if (o1.charAt(i) > o2.charAt(i)) {
                    return 1;
                } else if (o1.charAt(i) < o2.charAt(i)) {
                    return -1;
                }
            }
            return o1.length() > o2.length() ? 1 : -1;
        }
    }

    // for testing
    public static String generate(int maxSize) {
        StringBuilder sb = new StringBuilder();
        int size = (int) (Math.random() * maxSize) + 1;
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (int) (Math.random() * 4)));
        }
        return sb.toString();
    }

    /**
     * @param s
     * @return
     */
    public static String minString2(String s) {
        String res = "";
        char[] arr = s.toCharArray();
        int[] times = new int[26];
        // 记录使用频次表
        // 以及使用表
        // 如果一个字符到达0次
        // 那么在他之前最早出现的比他小的字符得提前使用,这样才能保证最小
        for (int i = 0; i < arr.length; i++) {
            times[arr[i] - 'a']++;
        }
        int L = 0, R = 0;
        while (R < arr.length) {
            if (times[arr[R] - 'a'] == -1 || --times[arr[R] - 'a'] > 0) {
                R++;
            }
            // R位置的字符频次减到0了
            else {
                int min = R;
                // 找到最小字符
                for (int i = L; i <= R; i++) {
                    if ((times[arr[i] - 'a'] != -1) && (arr[i] < arr[min] || (arr[i] == arr[min] && i < min))) {
                        min = i;
                    }
                }
                L = min;
                if (times[arr[L] - 'a'] != -1) {
                    res += arr[L];
                    times[arr[L] - 'a'] = -1;
                }
                L++;
                for (int i = L; i <= R; i++) {
                    if (times[arr[i] - 'a'] != -1) {
                        times[arr[i] - 'a']++;
                    }
                }
                R = L;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int loops = 50_0000;
        int maxLength = 8;
        for (int i = 0; i < loops; i++) {
            String str = generate(maxLength);
            if (!minString1(str).equals(minString2(str))) {
                System.out.println(str);
                minString1(str);
                minString2(str);
            }
        }
        System.out.println("Nice");
    }

}

