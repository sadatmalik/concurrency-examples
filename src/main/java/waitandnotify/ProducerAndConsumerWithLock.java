package waitandnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sm@creativefusion.net
 */
class ProcessorWithReentrantLock {

    private List<Integer> list = new ArrayList<>();

    private final int UPPER_LIMIT = 5;
    private final int LOWER_LIMIT = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int value = 0;

    public void producer() throws InterruptedException {
        lock.lock();
        try {
            while(true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for consumer to remove items from the list...");
                    condition.await(); // releases the lock
                } else {
                    System.out.println("Adding: "+value);
                    list.add(value);
                    value++;
                    condition.signal(); // notice it's OK to call this - synchronized block will continue until wait()
                }
                Thread.sleep(500);
            }
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for producer to add items to the list...");
                    condition.await(); // release the lock and wait for a signal
                } else {
                    System.out.println("Removed: " + list.remove(--value));
                    condition.signal(); // signals but continues processing through the while loop
                }
                Thread.sleep(500);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerAndConsumerWithLock {
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