package class7;

import java.util.*;

/**
 * <p>
 * 给定两个字符串，几位start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串
 * 所有的字符串都是小写字母
 * 规定:start每次只能改变一个字符，最终的目标是彻底变成to,但是每次变成的新字符串必须在list中存在
 * 请返回所有最短的变换路径
 * </p>
 * 【举例】
 * start = "abc" to = "cab" list ={"cab", "acc", "cbc", "ccc", "cac", "aab", "abb"}
 * abc -> abb -> aab -> cab
 * abc -> abb -> cbb -> cab
 * abc -> cbc -> cac -> cab
 * abc -> cbc -> cbb -> cab
 * //TODO 复习
 */
public class WordMinPath {


    /**
     * 时间复杂度O(N3)
     *
     * @param from
     * @param to
     * @param list
     * @return
     */
    public static List<LinkedList<String>> wordMinPath(String from, String to, List<String> list) {
        List<LinkedList<String>> ans = new ArrayList<>();
        boolean[] help = new boolean[list.size()];
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(from);
        process(from, ans, to, help, linkedList, list);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < ans.size(); i++) {
            min = Math.min(min, ans.get(i).size());
        }
        List<LinkedList<String>> res = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i).size() == min) {
                res.add(ans.get(i));
            }
        }
        return res;
    }

    private static void process(String from, List<LinkedList<String>> ans, String to, boolean[] help, LinkedList<String> linkedList, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!help[i]) {
                if (oneStep(list.get(i), from)) {
                    if (to.equals(list.get(i))) {
                        linkedList.add(list.get(i));
                        ans.add(new LinkedList<>(linkedList));
                        linkedList.removeLast();
                        return;
                    }
                    help[i] = true;
                    linkedList.addLast(list.get(i));
                    process(list.get(i), ans, to, help, linkedList, list);
                    linkedList.removeLast();
                    help[i] = false;
                }
            }
        }
    }

    public static boolean oneStep(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int diff = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (diff > 1) {
                return false;
            }
            if (arr1[i] != arr2[i]) {
                diff++;
            }
        }
        return diff == 1 ? true : false;
    }


    public static List<List<String>> minPath2(String[] arr, String from, String to) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> next = getNext(arr, to);
        Map<String, Integer> distance = getDistances(arr, next, from);
        getShortestPaths(from, to, next, distance, new LinkedList<>(), ans);
        return ans;
    }

    private static void getShortestPaths(
            String cur, String to,
            Map<String, List<String>> nexts,
            Map<String, Integer> distances,
            LinkedList<String> path,
            List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<String>(path));
        } else {
            for (String next : nexts.getOrDefault(cur,new ArrayList<>())) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }

    private static Map<String, Integer> getDistances(String[] arr, Map<String, List<String>> nexts, String from) {
        Map<String, Integer> ans = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add(from);
        Queue<String> queue = new LinkedList<>();
        queue.add(from);
        ans.put(from, 0);
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            for (String next : nexts.getOrDefault(poll,new ArrayList<>())) {
                if (!set.contains(next)) {
                    set.add(next);
                    ans.put(next, ans.get(poll) + 1);
                    queue.add(next);
                }
            }
        }
        return ans;
    }

    private static Map<String, List<String>> getNext(String[] arr, String to) {
        Map<String, List<String>> next = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                if (oneStep(arr[i], arr[j])) {
                    list.add(arr[j]);
                }
            }
            next.put(arr[i], list);
        }
        return next;
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("cab", "acc", "cbc", "ccc", "cac", "aab", "abb");
        String[] arr = new String[]{"cab", "acc", "cbc", "ccc", "cac", "aab", "abb"};
        String from = "abc";
        String to = "cab";
        System.out.println(wordMinPath(from, to, list));
        System.out.println(minPath2(arr, from, to));
    }
}
