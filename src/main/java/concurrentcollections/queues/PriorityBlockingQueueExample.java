package concurrentcollections.queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue implements the BlockingQueue interface
 *
 * 	- an unbounded concurrent queue
 *
 * 	- uses the same ordering rules as  java.util.PriorityQueue class -> have to
 * 	implement the Comparable interface for queue elements or use natural ordering
 *
 * 		- The comparable interface determines queue order
 * 		- The priority can be the same i.e. compare() == 0 case
 *
 *  - no null items are permitted
 *
 * @author sm@creativefusion.net
 */

class FirstWorker2 implements Runnable {

    private BlockingQueue<Person> blockingQueue;

    public FirstWorker2(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(new Person(42, "Bob"));
            blockingQueue.put(new Person(29, "Hermione"));
            blockingQueue.put(new Person(18, "Felicity"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(37, "Adam"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(37, "Emily"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker2 implements Runnable {

    private BlockingQueue<Person> blockingQueue;

    public SecondWorker2(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person> {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class PriorityBlockingQueueExample {
    public static void main(String[] args) {

        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        FirstWorker2 firstWorker = new FirstWorker2(queue);
        SecondWorker2 secondWorker = new SecondWorker2(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}