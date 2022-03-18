package forkjoinframework.maxfindingalgorithm;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) {
        long[] nums = createNums(250_000_000);
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);

        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.println("Sequential max = " + sequentialMaxFinding.max(nums));
        System.out.println("Runtime = " + (System.currentTimeMillis() - start));

        ParallelMaxFindingTask parallelMaxFindingTask =
                new ParallelMaxFindingTask(nums, 0, nums.length);
        start = System.currentTimeMillis();
        System.out.println("Parallel max = " + pool.invoke(parallelMaxFindingTask));
        System.out.println("Runtime = " + (System.currentTimeMillis() - start));
    }

    private static long[] createNums(int n) {
        Random random = new Random();
        long[] nums = new long[n];
        for (int i = 0; i < nums.length; i++)
            nums[i] = random.nextInt(1000);
        return nums;
    }
}
