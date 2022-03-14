package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Running the program we can see that the same thread is reused to
 * execute the task sequentially.
 *
 * @author sm@creativefusion.net
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }
}