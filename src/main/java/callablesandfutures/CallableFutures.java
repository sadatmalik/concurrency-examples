package callablesandfutures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable<T> can return a result as a Future<T> while Runnable can
 * not return a result.
 *
 * This is an example of using Callable interface with Futures.
 *
 * @author sm@creativefusion.net
 */
public class CallableFutures {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            // waits/blocks for a result
            // note use of submit() not execute() - submit() works with Callable and Runnable
            Future<String> future = executorService.submit(new Processor(i+1));
            list.add(future);
        }

        for(Future<String> future : list){
            try{
                System.out.println(future.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}

class CallableFutures2 {
    static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main( String args[] ) throws Exception {
        compute(10); // some computation
        threadPool.shutdown();
    }

    static int compute(final int n) throws Exception {
        Callable<Integer> computation = () -> {
            int computationResult = 0;

            // perform some computation

            return computationResult;
        };

        Future<Integer> f = threadPool.submit(computation);
        return f.get();
    }

}