package forkjoinframework.mergesort;

import parallelcomputing.mergesort.MergeSort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = createNums(50_000_000);
        MergeSort sequentialMergeSort = new MergeSort(nums);
        long start = System.currentTimeMillis();
        sequentialMergeSort.sort();
        System.out.println("Sequential Runtime = " + (System.currentTimeMillis() - start));

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        nums = createNums(50_000_000);
        MergeSortTask parallel = new MergeSortTask(nums);
        start = System.currentTimeMillis();
        pool.invoke(parallel);
        System.out.println("Parallel Runtime = " + (System.currentTimeMillis() - start));
    }

    private static int[] createNums(int n) {
        Random random = new Random();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++)
            nums[i] = random.nextInt(10_000);
        return nums;
    }
}
