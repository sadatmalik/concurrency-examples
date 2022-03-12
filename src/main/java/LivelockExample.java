import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implements a simple livelocking example. Acquiring the locks in the
 * same sequence will resolve the livelock in this instance.
 *
 * Other strategies for preventing livelock are to randomise the retry
 * attempt interval.
 *
 * @author sm@creativefusion.net
 */
public class LivelockExample {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public static void main(String args[]) {
        LivelockExample livelock = new LivelockExample();

        System.out.println("Starting workers...");

        new Thread(livelock::worker1, "worker-1").start();
        new Thread(livelock::worker2, "worker-2").start();
    }

    private void worker1() {
        while(true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker1 acquired lock1");
                Thread.sleep(10); // give worker2 a chance to acquire lock2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker1 trying to acquire lock2");

            if (lock2.tryLock()) {
                System.out.println("Worker1 acquired lock2");
                lock2.unlock();
            } else {
                System.out.println("Worker1 can not acquire lock2");
                continue;
            }

            break;
        }

        lock1.unlock();
    }

    private void worker2() {
        while(true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker2 acquired lock2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker2 trying to acquire lock1");

            if (lock1.tryLock()) {
                System.out.println("Worker2 acquired lock1");
                lock1.unlock();
            } else {
                System.out.println("Worker2 can not acquire lock1");
                continue;
            }

            break;
        }

        lock2.unlock();
    }

}
