package concurrentcollections.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * 	BlockingQueue -> an interface that represents a queue that is thread safe
 * 		Put items or take items from it ...
 *
 * 		For example: one thread putting items into the queue and another thread taking
 * 		items from it at the same time
 *
 *   	Example uses a producer-consumer pattern - but notice no need for wait() and
 *      notify() - this is all handled in the queue implementation
 *
 * 		put() puts items into the queue
 * 		take() takes items from the queue
 *
 * @author sm@creativefusion.net
 */

class FirstWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public FirstWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                System.out.println("Adding to the queue - " + counter);
                blockingQueue.put(String.valueOf(counter));
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public SecondWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Taking from the queue - " + blockingQueue.take());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}
