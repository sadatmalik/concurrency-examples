package concurrentcollections.copyonwritearray;

import java.util.List;
import java.util.Random;

/**
 * Writes to the CopyOnWriteArray.
 *
 * @author sm@creativefusion.net
 */
public class Writer implements Runnable {
    private List<Integer> list;
    private Random random;

    public Writer(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        while(true) {
            try {
                list.set(random.nextInt(list.size()),
                        random.nextInt(10));
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
