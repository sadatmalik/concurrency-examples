package parallelcomputing.mergesort;

/**
 * Merge sort implementation - O(n log n) time complexity
 * O(n) memory
 *
 * @author sm@creativefusion.net
 */
public class MergeSort {

    private final int[] nums;

    // merge sort is not an in-place algorithm
    private final int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void sort() {
        mergeSort(0, nums.length-1);
    }

    // divide and conquer
    private void mergeSort(int low, int high) {
        // low points to first item, high points to last item
        // base-case
        if (low >= high)
            return;

        // middle item
        int middleIndex = (low + high) / 2;

        // keep splitting into smaller problems - until a given subarray contains 1 item
        mergeSort(low, middleIndex);
        mergeSort(middleIndex+1, high);

        // now combine the sub-solutions
        merge(low, middleIndex, high);
    }

    private void merge(int low, int middle, int high) {
        // copy the items in to the temp array
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }
        int i = low; // represent index the left subarray
        int j = middle+1; // represent index in the right subarray
        int k = low; // represent index in the temp array

        // copy items from tempArray sorted into nums
        while(i <= middle && j <= high) {
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            k++;
        }

        // copy any remaining items
        while (i <= middle) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }

        while (j <= high) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public void showArray() {
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
