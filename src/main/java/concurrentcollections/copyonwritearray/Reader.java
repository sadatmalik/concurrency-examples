package concurrentcollections.copyonwritearray;

import java.util.List;
import java.util.Random;

/**
 * Reads from - prints out - the CopyOnWriteArray.
 *
 * @author sm@creativefusion.net
 */
public class Reader implements Runnable {
    private List<Integer> list;
    private Random random;

    public Reader(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(list);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
