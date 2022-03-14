package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example using a fixed thread pool. A fixed number of threads
 * is sequentially assigned the next available task.
 *
 * @author sm@creativefusion.net
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i++) {
            executor.execute(new Task(i));
        }
        executor.shutdown();
    }
}