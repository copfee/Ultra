package dynamic;

/**
 * Created by Administrator on 2016/6/12.
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String name) {
        String value = "Hello, " + name;
        synchronized(name) {
            System.out.println(this.getClass().getName() + " -> " + value);
        }
        return value;
    }
}
