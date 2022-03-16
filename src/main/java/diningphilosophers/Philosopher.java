package diningphilosophers;

import java.util.Random;

/**
 * @author sm@creativefusion.net
 */
public class Philosopher implements Runnable {

    private int id;
    private volatile boolean full;
    private Chopstick left;
    private Chopstick right;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while(!full) {
                think();
                if (left.pickUp(this, State.LEFT)) {
                    if (right.pickUp(this, State.RIGHT)) {
                        eat();
                        right.putDown(this, State.RIGHT);
                    }
                    left.putDown(this, State.LEFT);
                }
                if (eatingCounter == 1000) {
                    setFull(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isFull() {
        return this.full;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }
    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
