package streamapi;

import java.util.stream.IntStream;

/**
 * @author sm@creativefusion.net
 */
public class ParallelStreams {

    public static void main(String[] args) {
        // sequential
        long start = System.currentTimeMillis();
        long numOfPrimes = IntStream.range(2, Integer.MAX_VALUE / 100)// 21 million integers!
                .filter(ParallelStreams::isPrime)
                .count();
        System.out.println("Number of primes = " + numOfPrimes);
        System.out.println("Sequential run time = " + (System.currentTimeMillis() - start));

        System.out.println();

        // parallel
        start = System.currentTimeMillis();
        numOfPrimes = IntStream.range(2, Integer.MAX_VALUE / 100)// 21 million integers!
                .parallel()
                .filter(ParallelStreams::isPrime)
                .count();
        System.out.println("Number of primes = " + numOfPrimes);
        System.out.println("Parallel run time = " + (System.currentTimeMillis() - start));

    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        if (n == 2)
            return true;

        if (n % 2 == 0)
            return false;

        long maxDivisor = (long)Math.sqrt(n);
        for (int i = 3; i < maxDivisor; i+=2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
