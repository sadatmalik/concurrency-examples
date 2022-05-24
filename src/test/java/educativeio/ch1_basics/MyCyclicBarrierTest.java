package educativeio.ch1_basics;

import org.junit.jupiter.api.Test;

class MyCyclicBarrierTest {

    @Test
    void testCyclicBarrier() {
        MyCyclicBarrier cb = new MyCyclicBarrier(3, () -> {
            System.out.println("Barrier released action");
        });

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " waiting on barrier");
            cb.await();
            System.out.println(Thread.currentThread().getName() + " released");
        };

        Thread[] t = new Thread[3];

        for (int i = 1; i <= 3; i++)
            t[i-1] = new Thread(r, "Thread" + i);

        for (int i = 1; i <= 3; i++)
            t[i-1].start();

        for (int i = 1; i <= 3; i++) {
            try {
                t[i-1].join();
            } catch (InterruptedException e) {

            }
        }


    }
}