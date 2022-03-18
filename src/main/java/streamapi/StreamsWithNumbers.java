package streamapi;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author sm@creativefusion.net
 */
public class StreamsWithNumbers {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 5, 3, -2, 9, 12};

        for(int n : nums)
            System.out.print(n + " ");

        Arrays.stream(nums)
                .forEach(System.out::println);

        int sum = 0;
        for(int n : nums)
            sum += n;
        System.out.println("sum = " + sum);

        sum = Arrays.stream(nums)
                .reduce(0, Integer::sum);
        System.out.println("Streaming sum = " + sum);

        sum = Arrays.stream(nums)
                .sum();
        System.out.println("Streaming sum = " + sum);

        IntStream.range(0,5)
                .forEach(x -> System.out.print(x + " "));

        System.out.println();

        IntStream.range(0,5)
                .filter(x -> x > 3)
                .forEach(x -> System.out.print(x + " "));


    }
}
