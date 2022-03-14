package executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Example of scheduled thread pool executor service.
 *
 * @author sm@creativefusion.net
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // initial delay 1s, period 2s
        executor.scheduleAtFixedRate(new Task(1), 1000, 2000, TimeUnit.MILLISECONDS);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}