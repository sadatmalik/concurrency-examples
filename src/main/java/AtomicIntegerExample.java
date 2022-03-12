import java.util.concurrent.atomic.AtomicInteger;

/**
 * Can synchronize access to an int variable
 *
 * Or can use an Atomic variable instance that wraps the integer in in-built
 * thread safe (internally synchronized) methods.
 *
 * @author sm@creativefusion.net
 */
public class AtomicIntegerExample {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(AtomicIntegerExample::increment);
        Thread t2 = new Thread(AtomicIntegerExample::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter = " + counter);
    }

    private static void increment() {
        for (int i = 0; i < 100000; i++)
            counter.incrementAndGet();
    }

}
