package diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * We are able to avoid deadlock because of use of tryLock() in the Chopstick class
 * acquisition - pickUp() method.
 *
 * @author sm@creativefusion.net
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
        Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];

        for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; ++i) {
            chopsticks[i] = new Chopstick(i);
        }

        ExecutorService executorService =
                Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

        try {
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; ++i) {
                philosophers[i] = new Philosopher(i, chopsticks[i],
                        chopsticks[(i+1)%Constants.NUMBER_OF_PHILOSOPHERS]);

                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }

        } finally {
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            for (Philosopher philosopher : philosophers)
                System.out.println(philosopher + " ate " + philosopher.getEatingCounter() + " times");
        }
    }
}
