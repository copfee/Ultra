package classloader;

/**
 * Created by Administrator on 2016/6/13.
 */
public class HelloSimple {
    public static void main(String[] args) {
        int a = 5;
        int b = a;
        System.out.println(b);
        a = 10;
        System.out.println(b);
        String c = "cccc";
        String d = c;
        System.out.println(d);
        c = "dddd";
        System.out.println(d);
    }

    public void sayHello() {
        System.out.println("Hello World!");
    }
}
