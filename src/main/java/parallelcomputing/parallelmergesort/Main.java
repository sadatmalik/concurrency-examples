package parallelcomputing.parallelmergesort;

import parallelcomputing.mergesort.MergeSort;

import java.util.Random;

/**
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads = " + numOfThreads);

        int[] numbers1 = createArray(100_000_000);
        int[] numbers2 = new int[numbers1.length];
        for (int i = 0; i < numbers1.length; i++)
            numbers2[i] = numbers1[i];

        // parallel merge sort
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(numbers1, numOfThreads);
        long startTime = System.currentTimeMillis();
        parallelMergeSort.sort();
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("Run time with parallel sort = " + runTime);

        // sequential merge sort
        MergeSort mergeSort = new MergeSort(numbers2);
        startTime = System.currentTimeMillis();
        mergeSort.sort();
        runTime = System.currentTimeMillis() - startTime;
        System.out.println("Run time with sequential sort = " + runTime);
    }


    private static int[] createArray(int n) {
        int[] a = new int[n];
        Random random = new Random();

        for (int i : a)
            a[i] = random.nextInt(n);

        return a;
    }
}
