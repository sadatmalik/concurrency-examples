package callablesandfutures;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Simple processor used in CallableFutures demo.
 *
 * @author sm@creativefusion.net
 */
public class Processor implements Callable<String> {

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