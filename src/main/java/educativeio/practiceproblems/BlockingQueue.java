package educativeio.practiceproblems;

/**
 * @author sm@creativefusion.net
 */
public class BlockingQueue {

    private int[] queue;
    private int head;
    private int tail;
    private int size;
    private int capacity;



    BlockingQueue(int queueSize) {
        queue = new int[queueSize];
        head = 0;
        tail = 0;
        size = 0;
        capacity = queueSize;
    }

    synchronized void enqueue(int item) throws InterruptedException {
        while (size == capacity) {
            this.wait();
        }

        if (tail == capacity)
            tail = 0;

        queue[tail] = item;
        tail++;
        size++;

        notifyAll();
    }

    synchronized int dequeue() throws InterruptedException {
        while (size == 0) {
            this.wait();
        }

        if (head == capacity)
            head = 0;

        int item = queue[head];
        head++;
        size--;

        notifyAll();

        return item;
    }
}

class TestQueue {
    static BlockingQueue bq = new BlockingQueue(5);

    public static void main(String[] args) {
        Runnable enqueue = () -> {
            for (int i = 0; i < 20; i++) {
                try {
                    bq.enqueue(i);
                    System.out.println(Thread.currentThread().getName() +
                            " enqueued: " + i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        class Dequeue implements Runnable {
            @Override
            public void run() {
                int i = 0;
                while (i < 10) {
                    try {
                        System.out.println(Thread.currentThread().getName() +
                                " dequeued: " + bq.dequeue());
                        i++;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        Thread t1 = new Thread(enqueue, "t1");
        Thread t2 = new Thread(new Dequeue(), "t2");
        Thread t3 = new Thread(new Dequeue(), "t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
