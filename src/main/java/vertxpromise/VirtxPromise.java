package vertxpromise;

import io.vertx.core.Future;
import io.vertx.core.Promise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author sm@creativefusion.net
 */
class VirtxPromise {
    static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String args[]) throws Exception {
        // initialise promise
        Promise<String> promise = Promise.promise();

        // async call
        threadPool.execute(new Computation(promise));

        // handle future
        Future<String> future = promise.future();
        future
                .onSuccess(System.out::println)
                .onFailure(err -> System.out.println(err.getMessage()));

        threadPool.shutdown();
    }
}

class Computation implements Runnable {

    Promise<String> promise;

    Computation(Promise<String> promise) {
        this.promise = promise;
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() % 2L == 0L) {
            promise.complete("Success");
        } else {
            promise.fail(new RuntimeException("Failed"));
        }
    }
}