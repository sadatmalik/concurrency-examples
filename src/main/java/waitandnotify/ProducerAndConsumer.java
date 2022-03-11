package waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of a producer and consumer pattern using wait() and notify()
 * @author sm@creativefusion.net
 */
class Processor {

    private List<Integer> list = new ArrayList<>();

    private final int UPPER_LIMIT = 5;
    private final int LOWER_LIMIT = 0;

    private final Object lock = new Object();

    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for consumer to remove items from the list...");
                    lock.wait(); // releases the lock
                } else {
                    System.out.println("Adding: "+value);
                    list.add(value);
                    value++;
                    lock.notify(); // notice it's OK to call this - synchronized block will continue until wait()
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for producer to add items to the list...");
                    lock.wait();
                } else {
                    System.out.println("Removed: "+list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

public class ProducerAndConsumer {
    static Processor processor = new Processor();
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}