package unionset;

import java.util.*;

/**
 * 一个用户类有多个属性
 * 例如b站id，a站id，c站id
 * 任一id相同时认为是同一个人，判断一组人当中一共有几个人
 */
@SuppressWarnings("all")
public class UserSize {
    // 用户实体
    private static class User {
        private String a;
        private String b;
        private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    // 并查集
    public static class UnionSet {
        /**
         * 对象集合映射表
         */
        private Map<User, Node> nodes;
        /**
         * 集合大小
         */
        private Map<Node, Integer> sizeMap;
        // 加入parent 使isUnion效率提升为O(1)
        private Map<Node, Node> parentMap;


        /**
         * a 和 b 是否在同一个集合中
         *
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(User a, User b) {
            Node node1 = nodes.get(a);
            Node node2 = nodes.get(b);
            if (null == node1 || null == node2) {
                return false;
            }
            return findParentNode(node1) == findParentNode(node2);
        }

        /**
         * 寻找一个结点的父节点，不存在则返回 Null
         *
         * @param node
         * @return
         */
        private Node findParentNode(Node node) {
            Node parent = null;
            while (node != null && node != parent) {
                parent = node;
                node = parentMap.get(node);
            }
            return parent;
        }


        /**
         * 将 a 所在的集合与 b 所在的集合合并
         *
         * @param a
         * @param b
         */
        public void union2(User a, User b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node parentNode1 = findParentNode(nodes.get(a));
            Node parentNode2 = findParentNode(nodes.get(b));
            if (parentNode1 != parentNode2) {
                Integer size1 = sizeMap.get(parentNode1);
                Integer size2 = sizeMap.get(parentNode2);
                Node big = size1 > size2 ? parentNode1 : parentNode2;
                Node small = big == parentNode1 ? parentNode2 : parentNode1;
                parentMap.put(small, big);
                sizeMap.put(big,size1+size2);
                sizeMap.remove(small);
            }
        }


        public UnionSet(User[] list) {
            nodes = new HashMap<>();
            sizeMap = new HashMap<>();
            parentMap = new HashMap<>();
            for (User t : list) {
                Node node = new Node(t);
                nodes.put(t, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }


        private static class Node<T> {
            public T t;

            public Node(T t) {
                this.t = t;
            }
        }
    }

    // for test
    public static User[] generateStudent(int size) {
        List<String> aList = new ArrayList<>(), bList = new ArrayList<>(), cList = new ArrayList<>();
        User[] users = new User[size];

        for (int i = 0; i < size; i++) {
            String a = Math.random() < 0.05 ? aList.size() > 0 ? aList.get((int) (Math.random() * aList.size())) : UUID.randomUUID().toString() : UUID.randomUUID().toString();
            String b = Math.random() < 0.05 ? aList.size() > 0 ? bList.get((int) (Math.random() * bList.size())) : UUID.randomUUID().toString() : UUID.randomUUID().toString();
            String c = Math.random() < 0.05 ? aList.size() > 0 ? cList.get((int) (Math.random() * cList.size())) : UUID.randomUUID().toString() : UUID.randomUUID().toString();
            aList.add(a);
            bList.add(b);
            cList.add(c);
            users[i] = new User(a, b, c);
        }
        return users;
    }


    /**
     * 一共有多少个不同的用户
     *
     * @param users
     * @return
     */
    @Deprecated
    public static int userSize1(User[] users) {
        if (null == users || users.length == 0) {
            return 0;
        }
        Map<String, User> mapA = new HashMap();
        Map<String, User> mapB = new HashMap();
        Map<String, User> mapC = new HashMap();
        int size = 0;
        for (int i = 0; i < users.length; i++) {
            if (!mapA.containsKey(users[i].getA()) && !mapB.containsKey(users[i].getB()) && !mapC.containsKey(users[i].getC())) {
                size++;
            }
            mapA.put(users[i].a, users[i]);
            mapB.put(users[i].b, users[i]);
            mapC.put(users[i].c, users[i]);
        }
        return size;
    }

    public static void main(String[] args) {
        int loops = 50_0000;
        int maxSize = 4;
        for (int i = 0; i < loops; i++) {
            int size = (int) (Math.random() * maxSize);
            User[] users = generateStudent(size);
            // userSize1 是错的
            int i1 = userSize1(users);
            int i2 = userSize2(users);
            if (i1 != i2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Nice");
    }

    /**
     * 一共有多少个不同的用户
     *
     * @param users
     * @return
     */
    private static int userSize2(User[] users) {
        UnionSet unionSet = new UnionSet(users);
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();
        for (int i = 0; i < users.length; i++) {
            if (mapA.containsKey(users[i].a)) {
                unionSet.union2(users[i], mapA.get(users[i].a));
            } else {
                mapA.put(users[i].a, users[i]);
            }
            if (mapB.containsKey(users[i].b)) {
                unionSet.union2(users[i], mapB.get(users[i].b));
            } else {
                mapB.put(users[i].b, users[i]);
            }
            if (mapC.containsKey(users[i].c)) {
                unionSet.union2(users[i], mapC.get(users[i].c));
            } else {
                mapC.put(users[i].c, users[i]);
            }
        }
        return unionSet.sizeMap.size();
    }


}
