package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyLoaderTest {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        File file = new File("D://Hello.class");
        InputStream input = new FileInputStream(file);
        byte[] result = new byte[1024];

        int count = input.read(result);
        MyLoader loader = new MyLoader();
        Class clazz = loader.defineMyClass(result, 0, count);
        System.out.println(clazz.getCanonicalName());

        Object o = clazz.newInstance();
        try {
            Method method = clazz.getMethod("sayHello", String.class);
            Object returnObject = method.invoke(o, "World!");
            System.out.println("returnObject = " + returnObject);
        } catch (IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
}
