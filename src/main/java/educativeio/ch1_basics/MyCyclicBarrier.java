package educativeio.ch1_basics;

/**
 * @author sm@creativefusion.net
 */
public class MyCyclicBarrier {

    private int barrierCount;
    private final Object barrierLock = new Object();

    MyCyclicBarrier(int barrierCount, Runnable action) {
        this.barrierCount = barrierCount;
        new Action(action).start();
    }

    public void await() {

        synchronized (barrierLock) {

            barrierCount--;
            barrierLock.notifyAll();

            while (barrierCount > 0) {
                try {
                    barrierLock.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    class Action extends Thread {
        Runnable action;

        Action(Runnable r) {
            action = r;
        }

        @Override
        public void run() {
            synchronized (barrierLock) {
                while (barrierCount > 0) {
                    try {
                        barrierLock.wait();
                    } catch (InterruptedException e) {

                    }
                }
                action.run();
            }
        }
    }

}
