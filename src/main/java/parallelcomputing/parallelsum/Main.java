package parallelcomputing.parallelsum;

import java.util.Random;

/**
 * Below about 100,000,000 nums - sequential sum is faster
 *
 * Above this - parallel sum is faster.
 *
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[250_000_000];
        for (int i = 0; i < nums.length; i++)
            nums[i] = random.nextInt(100);

        SequenialSum sequenialSum = new SequenialSum();
        long startTime = System.currentTimeMillis();
        int sum = sequenialSum.sum(nums);
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("Sequential Sum = " + sum + " , runtime = " + runTime);

        // parallel sum
        int n = Runtime.getRuntime().availableProcessors();
        ParallelSum parallelSum = new ParallelSum(n);
        startTime = System.currentTimeMillis();
        sum = parallelSum.sum(nums);
        runTime = System.currentTimeMillis() - startTime;
        System.out.println("Parallel Sum = " + sum + " , runtime = " + runTime);
    }
}
