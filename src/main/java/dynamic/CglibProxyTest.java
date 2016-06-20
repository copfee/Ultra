package dynamic;

/**
 * Created by Administrator on 2016/6/12.
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Hello helloProxy = (Hello) proxy.getProxyInstance(new HelloImpl());
        String result = helloProxy.sayHello("Cglib");
        System.out.println(helloProxy.getClass().getName() + " -> " + result);
    }
}
