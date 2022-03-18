package forkjoinframework;

import java.util.concurrent.RecursiveTask;

/**
 * @author sm@creativefusion.net
 */
public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private Integer simulatedWork;

    public SimpleRecursiveTask(Integer simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Large task - splitting for parallel execution " + simulatedWork);

            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork/2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork/2);

            // add the tasks to the thread pool for parallel execution
            task1.fork();
            task2.fork();

            // wait for them to finish
            int subSolution = 0;
            subSolution += task1.join();
            subSolution += task2.join();

            return subSolution;

        } else {
            System.out.println("The task is small - sequential processing " + simulatedWork);
            return 2 * simulatedWork;
        }
    }
}
