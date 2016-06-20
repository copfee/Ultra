package deadlock;

/**
 * Created by Administrator on 2016/6/20.
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Resource r1 = new Resource("r1");
        Resource r2 = new Resource("r2");
        Resource r3 = new Resource("r3");

        Thread t1 = new Thread(new ResourceThread(r1, r2), "t1");
        Thread t2 = new Thread(new ResourceThread(r2, r3), "t2");
        Thread t3 = new Thread(new ResourceThread(r3, r1), "t3");

        t1.start();
        //Thread.sleep(5000);
        t2.start();
        //Thread.sleep(5000);
        t3.start();
    }

}

class Resource {
    public String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ResourceThread implements Runnable {
    private Resource rA;
    private Resource rB;

    public ResourceThread(Resource rA, Resource rB) {
        this.rA = rA;
        this.rB = rB;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on " + rA.getName());
        synchronized (rA) {
            System.out.println(name + " acquired lock on " + rA.getName());
            work();
            System.out.println(name + " acquiring lock on " + rB.getName());
            synchronized (rB) {
                System.out.println(name + " acquired lock on " + rB.getName());
                work();
            }
            System.out.println(name + " released lock on " + rB.getName());
        }
        System.out.println(name + " released lock on " + rA.getName());
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 3; i++) {
                Thread.sleep(1000);
                System.out.println(name + " count down " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
