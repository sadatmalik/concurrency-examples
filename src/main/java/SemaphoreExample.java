import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphores control the accesses (permits) to a finite number of available
 * resources.
 *
 * Threads will block on the semaphore until a permit is available and may
 * be successfully acquired.
 *
 * @author sm@creativefusion.net
 */
enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    // simulates data download
    private void downloadData() {
        try {
            System.out.println("Downloading data from the web...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class SemaphoreExample {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++)
            service.execute(Downloader.INSTANCE::download);

        service.shutdown();
    }
}
