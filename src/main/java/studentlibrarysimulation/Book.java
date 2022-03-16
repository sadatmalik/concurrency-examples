package studentlibrarysimulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sm@creativefusion.net
 */
public class Book {
    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    // use tryLock() to avoid deadlock situations
    public void read(Student student) throws InterruptedException {
        if (lock.tryLock(3, TimeUnit.SECONDS)) {
            System.out.println(student + " starts reading " + this);
            Thread.sleep(2000);
            lock.unlock();
            System.out.println(student + " has finished reading " + this);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                '}';
    }
}
