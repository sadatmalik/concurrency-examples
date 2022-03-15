package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A CyclicBarrier is used in situations where you want to create a group of
 * tasks to perform work in parallel + wait until they are all finished before
 * moving on to the next step -> something like join() -> something like
 * CountDownLatch
 *
 * CountDownLatch: one-shot event
 * CyclicBarrier: it can be reused over and over again
 *
 * + cyclicBarrier has a barrier action: a runnable, that will run automatically
 * when the count reaches 0
 *
 * new CyclicBarrier(N) -> N threads will await() for each other
 *
 * @author sm@creativefusion.net
 */

class Worker implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker(int id, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
        this.id = id;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " + id + " starts the task...");

        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with ID " + id + " finished...");

        try {
            cyclicBarrier.await(); // waits for all parallel threads to complete
            System.out.println("After await...");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "" + this.id;
    }
}

public class CyclicBarrierExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("We are able to use the trained neural network...");
            }
        });

        for(int i = 0; i < 5; i++)
            executorService.execute(new Worker(i, barrier));

        executorService.shutdown();
    }
}