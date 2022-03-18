package forkjoinframework.maxfindingalgorithm;

/**
 * Sequential - linear search - O(n) time - assumes an unsorted input.
 *
 * If sorted input - then O(log n) time.
 *
 * @author sm@creativefusion.net
 */
public class SequentialMaxFinding {

    // linear
    public long max(long[] nums) {
        long max = nums[0];

        for(int i = 1; i < nums.length; i++)
            max = Math.max(nums[i], max);

        return max;
    }
}
