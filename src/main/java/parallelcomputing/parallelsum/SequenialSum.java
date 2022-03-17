package parallelcomputing.parallelsum;

/**
 * Linear running time algorithm with O(n) time complexity.
 *
 * @author sm@creativefusion.net
 */
public class SequenialSum {

    // O(n) time
    public int sum(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum;
    }
}
