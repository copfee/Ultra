package dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/12.
 */
public class CglibProxy implements MethodInterceptor {

    private Object object = null;

    public Object getProxyInstance(Object object) {
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始CGLIB代理调用");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("结束CGLIB代理调用");
        return result;
    }
}
