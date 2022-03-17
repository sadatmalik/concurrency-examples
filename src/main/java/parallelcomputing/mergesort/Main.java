package parallelcomputing.mergesort;

/**
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[] {
          5, -1, 0, 7, 2, 3, 2, 1, 0, 1, 2
        };

        MergeSort sort = new MergeSort(nums);
        sort.sort();
        sort.showArray();
    }
}
