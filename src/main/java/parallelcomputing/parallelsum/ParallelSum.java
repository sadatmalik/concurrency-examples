package parallelcomputing.parallelsum;

/**
 * @author sm@creativefusion.net
 */
public class ParallelSum {
    private ParallelWorker[] workers;
    private int numOfThreads;

    public ParallelSum(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        workers = new ParallelWorker[numOfThreads];
    }

    public int sum(int[] nums) {

        int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new ParallelWorker(nums, i*size, (i+1)*size);
            workers[i].start();
        }

        try {
            for (ParallelWorker worker : workers)
                worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (ParallelWorker worker : workers)
            total += worker.getPartialSum();

        return total;
    }
}

class ParallelWorker extends Thread {

    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = Math.min(nums.length, high);
    }

    @Override
    public void run() {
        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }
    }

    public int getPartialSum() {
        return partialSum;
    }
}
