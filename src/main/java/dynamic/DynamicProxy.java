package dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/6/12.
 */
public class DynamicProxy implements InvocationHandler {
    private Object object = null;

    public Object getProxyInstance(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始动态代理调用");
        Object result = method.invoke(object, args);
        System.out.println("结束动态代理调用");
        return result;
    }
}
