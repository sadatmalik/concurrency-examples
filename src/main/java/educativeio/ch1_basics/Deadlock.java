package educativeio.ch1_basics;

/**
 * @author sm@creativefusion.net
 */
public class Deadlock {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private int counter = 0;

    Runnable incrementer = () -> {
        while (true) {
            synchronized (lock1) {
                System.out.println("Incrementer has lock1");
                synchronized (lock2) {
                    System.out.println("Incrementer has lock2");
                    counter++;
                    System.out.println("counter = " + counter);
                }
            }
        }
    };

    Runnable decrementer = () -> {
        while (true) {
            synchronized (lock2) {
                System.out.println("Decrementer has lock2");
                synchronized (lock1) {
                    System.out.println("Decrementer has lock1");
                    counter--;
                    System.out.println("counter = " + counter);
                }
            }
        }
    };

    public static void main(String[] args) {
        Deadlock dl = new Deadlock();
        new Thread(dl.incrementer).start();
        new Thread(dl.decrementer).start();
    }

}
