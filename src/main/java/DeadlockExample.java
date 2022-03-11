import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implements a simple deadlocking example. Acquiring the locks in the
 * same sequence will resolve the deadlock.
 *
 * @author sm@creativefusion.net
 */
public class DeadlockExample {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public static void main(String args[]) {
        DeadlockExample de = new DeadlockExample();

        System.out.println("Starting workers...");

        new Thread(de::worker1, "worker-1").start();
        new Thread(de::worker2, "worker-2").start();
    }

    private void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquired lock1");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock(); // deadlocks
        System.out.println("Worker1 acquired lock2");

        lock2.unlock();
        lock1.unlock();
    }

    private void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquired lock2");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock(); // deadlocks
        System.out.println("Worker2 acquired lock1");

        lock1.unlock();
        lock2.unlock();
    }

}
