package educativeio.ch1_basics;

/**
 * @author sm@creativefusion.net
 */
public class SumToMaxInt {

    private long counter = 0;
    private int start;
    private int end;
    private static int MAX = Integer.MAX_VALUE;

    SumToMaxInt(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public long add() {
        for (int i = start; i < end; i++) {
            counter += i;
        }
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        SumToMaxInt sum = new SumToMaxInt(0, MAX);
        long millis = System.currentTimeMillis();
        long total = sum.add();
        millis = System.currentTimeMillis() - millis;
        System.out.println("One thread sum = " + total + ", time = " + millis);

        // 2 thread
        SumToMaxInt s1 = new SumToMaxInt(0, MAX/2);
        SumToMaxInt s2 = new SumToMaxInt(1+MAX/2, MAX);

        Thread t1 = new Thread(() -> {
           s1.add();
        });

        Thread t2 = new Thread(() -> {
            s2.add();
        });

        millis = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        millis = System.currentTimeMillis() - millis;

        System.out.println("Two thread sum = " + (s1.counter + s2.counter) + ", time = " + millis);
    }
}
