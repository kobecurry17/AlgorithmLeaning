package basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapTest {
    private String a;
    private String b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashMapTest hashMapTest = (HashMapTest) o;
        return Objects.equals(a, hashMapTest.a);
    }

    public HashMapTest(String a) {
        this.a = a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Map<HashMapTest, HashMapTest> map= new HashMap<>();
        HashMapTest hashMapTest = new HashMapTest("11");
        map.put(hashMapTest, hashMapTest);
        System.out.println(map.size());
        System.out.println(map.keySet());
        hashMapTest.setA("bbb");
        map.remove(hashMapTest);
        System.out.println(map.size());
        System.out.println(map.keySet());
        HashMapTest bbb = new HashMapTest("11");
        map.put(bbb,bbb);
        System.out.println(map.size());
        System.out.println(map.keySet());

    }


}




