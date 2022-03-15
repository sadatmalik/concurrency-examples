package concurrentcollections.copyonwritearray;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sm@creativefusion.net
 */
public class CopyOnWriteArrayExample {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(
                new Integer[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        );

        new Thread(new Writer(list)).start();
        new Thread(new Writer(list)).start();
        new Thread(new Writer(list)).start();
        new Thread(new Reader(list)).start();
    }
}
