package SimpleDemo;

/**
 * 异或测试
 * 如何不使用额外空间交换两个数？
 * 假设 A =甲        B = 乙
 * A = A ^ B        A=甲^乙
 * B = A ^ B        B=甲^乙^乙=甲^0=甲
 * A = A ^ B        A=甲^乙^甲=乙^0=乙
 */
public class XOR {

    public static void main(String[] args) {
//        XORtest(-30000,5444);

        // 当使用的内存空间为同一个时将产生错误
        int[] arr = new int[]{1, 3, 100};
        System.out.println(arr[0]);
        System.out.println(arr[2]);
        swap(arr, 0, 2);
        System.out.println(arr[0]);
        System.out.println(arr[2]);
        swap(arr, 0, 2);
        System.out.println(arr[0]);
        System.out.println(arr[2]);
    }

    static void XORtest(int a, int b) {
        System.out.println(String.format("%d*****%d", a, b));
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(String.format("%d*****%d", a, b));
    }

    static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];

    }
}
