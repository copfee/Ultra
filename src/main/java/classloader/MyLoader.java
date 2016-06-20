package classloader;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyLoader extends ClassLoader {
    public Class<?> defineMyClass(byte[] b, int off, int len) {
        return super.defineClass(null, b, off, len);
    }
}
