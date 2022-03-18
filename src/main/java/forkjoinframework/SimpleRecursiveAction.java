package forkjoinframework;

import java.util.concurrent.RecursiveAction;

/**
 * @author sm@creativefusion.net
 */
public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        // if the task is too large, then we split it and execute in parallel
        if (simulatedWork > 100) {
            System.out.println("Parallel execution - splitting task");
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            // arrange to execute the sub-tasks in the thread pool the task is currently
            // running in - i.e. within the fork-join pool
            action1.fork();
            action2.fork();

            // wait for actions to complete - to allow all sout
            action1.join();
            action2.join();

        } else {
            System.out.println("The task is small - sequential execution is fine");
            System.out.println("Size of task = " + simulatedWork);
        }

    }
}
