package classloader;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Hello {
    public String sayHello(String name) {
        System.out.println(this.getClass().getName() + " -> " + name);
        return name;
    }
}
