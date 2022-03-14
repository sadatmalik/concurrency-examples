package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Running the program we can see that the same thread is reused to
 * execute the task sequentially.
 *
 * @author sm@creativefusion.net
 */
class Task implements Runnable{

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            System.out.println("Task id: " + id +
                    ", Thread id:" + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }
}