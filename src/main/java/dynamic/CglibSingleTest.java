package dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/12.
 */
public class CglibSingleTest {
    public static void main(String[] args) {
        final Hello hello = new HelloImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before...");
                Object o = method.invoke(hello, args);
                System.out.println("After...");
                return o;
            }
        });
        Hello helloProxy = (Hello) enhancer.create();
        System.out.println(helloProxy.getClass().getName());
        System.out.println(helloProxy.sayHello("CglibSingleTest"));
    }
}
