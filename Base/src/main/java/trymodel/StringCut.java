package trymodel;

/**
 * 给定一个字符串str,给定一个字符串类型数组
 * arr里的每一个字符串，代表一张贴纸，你可以吧他们单独剪开使用，目的是拼出str来
 * 返回至少需要多少张贴纸？
 * 例如：str="babac",arr = {"ba","c","abcd"}
 * 至少需要两张贴纸 "ba" 和 "abcd" 这样能凑出babac,所以返回2
 */
@SuppressWarnings("all")
public class StringCut {


    public static int ans1(String str, String[] arr) {
        if (null == str || str.trim() == "") {
            return 0;
        }
        int[][] map = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            map[i] = parse(arr[i]);
        }
        return process(str, arr, map);
    }

    private static int process(String rest, String[] arr, int[][] map) {
        if (rest.isEmpty()) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] frequencise = parse(rest);
        for (int i = 0; i < arr.length; i++) {
            if (!need(frequencise, map[i])) {
                continue;
            }
            int[] rest1 = sub(frequencise, map, i);
            String r = format(rest1);
            int tmp = process(r, arr, map);
            if (tmp != -1) {
                ans = Math.min(1 + tmp, ans);
            }
        }
        return Integer.MAX_VALUE == ans ? -1 : ans;
    }

    private static String format(int[] rest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest.length; i++) {
            for (int j = 0; j < rest[i]; j++) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }

    private static int[] sub(int[] frequencise, int[][] map, int index) {
        int[] arr = map[index];
        int[] res = new int[26];
        for (int i = 0; i < arr.length; i++) {
            res[i] = frequencise[i] > arr[i] ? frequencise[i] - arr[i] : 0;
        }
        return res;
    }


    // 剩余部分是否需要改贴纸
    private static boolean need(int[] target, int[] str) {
        boolean flag = false;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != 0 && str[i] != 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    private static int[] parse(String str) {
        int[] res = new int[26];
        for (int i = 0; i < str.length(); i++) {
            res[str.charAt(i) - 'a']++;
        }
        return res;
    }


    // for test
    public static String generate(int maxSize, int maxValue) {
        StringBuilder sb = new StringBuilder();
        int size = 1 + (int) (Math.random() * maxSize);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (int) (Math.random() * maxValue)));
        }
        return sb.toString();
    }

    // for test
    public static String[] generateArr(int arraySize, int stringSize) {
        String[] arr = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            arr[i] = generate(stringSize, 26);
        }
        return arr;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 10;
        int maxStringSize = 10;
        int maxValue = 26;
        int maxArraySize = 5;
        for (int i = 0; i < loops; i++) {

            String[] arr = {"x","y","dd"};
            String str = "abcccccdddddbbbaaaaa";
            System.out.println(ans1(str, arr));
        }


    }

}
