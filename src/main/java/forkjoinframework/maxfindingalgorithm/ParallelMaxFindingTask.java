package forkjoinframework.maxfindingalgorithm;

import java.util.concurrent.RecursiveTask;

/**
 * @author sm@creativefusion.net
 */
public class ParallelMaxFindingTask extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxFindingTask(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {

        if (highIndex - lowIndex < 5000) {
            return sequentialMaxFinding();
        } else {
            Integer middleIndex = (highIndex + lowIndex) / 2;

            ParallelMaxFindingTask task1 =
                    new ParallelMaxFindingTask(nums, lowIndex, middleIndex);
            ParallelMaxFindingTask task2 =
                    new ParallelMaxFindingTask(nums, middleIndex+1, highIndex);

            // invokes all tasks until completion or exception is encountered
            invokeAll(task1, task2);
            return Math.max(task1.join(), task2.join());
        }
    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIndex];
        for(int i = lowIndex+1; i < highIndex; i++)
            max = Math.max(nums[i], max);
        return max;
    }
}
