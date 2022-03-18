package forkjoinframework;

import java.util.concurrent.ForkJoinPool;

/**
 * An example using the fork-join framework execution service with
 * recursive actions - non-value returning recursive actions.
 *
 * @author sm@creativefusion.net
 */
public class RecursiveActionMain {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction rootAction = new SimpleRecursiveAction(240);

        // invoke() will invoke the task and await its completion if necessary
        pool.invoke(rootAction);
    }
}
