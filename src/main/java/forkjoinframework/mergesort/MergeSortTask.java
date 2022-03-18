package forkjoinframework.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Parallel merge sort using fork-join framework.
 *
 * @author sm@creativefusion.net
 */
public class MergeSortTask extends RecursiveAction {

    private int[] nums;

    public MergeSortTask(int[] nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {

        // base case
        if (nums.length <= 1) {
            mergeSort(nums);
            return;
        }

        int middleIndex = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex+1, nums.length);

        MergeSortTask task1 = new MergeSortTask(left);
        MergeSortTask task2 = new MergeSortTask(right);

        invokeAll(task1, task2);

        merge(left, right, nums);
    }

    public void mergeSort(int[] nums) {

        if (nums.length <= 1)
            return;

        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, nums);
    }

    private void merge(int[] leftSubarray, int[] rightSubarray, int[] originalArray) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSubarray.length && j < rightSubarray.length) {
            if (leftSubarray[i] < rightSubarray[j])
                originalArray[k++] = leftSubarray[i++];
            else
                originalArray[k++] = rightSubarray[j++];
        }

        while (i < leftSubarray.length)
            originalArray[k++] = leftSubarray[i++];

        while (j < rightSubarray.length)
            originalArray[k++] = rightSubarray[j++];
    }

}
