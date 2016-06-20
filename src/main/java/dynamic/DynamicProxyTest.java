package dynamic;

/**
 * Created by Administrator on 2016/6/12.
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        DynamicProxy proxy = new DynamicProxy();
        Hello helloProxy = (Hello) proxy.getProxyInstance(new HelloImpl());
        String result = helloProxy.sayHello("World!");
        System.out.println(helloProxy.getClass().getName() + " -> " + result);
    }
}
