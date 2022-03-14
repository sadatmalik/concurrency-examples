package executorservice;

import java.util.concurrent.TimeUnit;

/**
 * Defines a simple task that is used in the various thread pool examples.
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