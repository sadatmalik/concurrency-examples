package executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable<T> can return a result as a Future<T> while Runnable can
 * not return a result.
 *
 * This is an example of using Callable interface with Futures.
 *
 * @author sm@creativefusion.net
 */

class Processor implements Callable<String> {

    private int id;

    public Processor(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Id: "+this.id;
    }
}

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