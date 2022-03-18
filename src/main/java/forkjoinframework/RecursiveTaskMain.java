package forkjoinframework;

import java.util.concurrent.ForkJoinPool;

/**
 * @author sm@creativefusion.net
 */
public class RecursiveTaskMain {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveTask rootTask = new SimpleRecursiveTask(300);

        System.out.println("task result = " + pool.invoke(rootTask));
    }
}
