package streamapi;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author sm@creativefusion.net
 */
public class StreamsWithStringsAndCharacters {

    public static void main(String[] args) {
        String[] names = {
                "Adam", "Daniel", "Martha", "Kevin", "Ben", "Joe", "Brad", "Susan"
        };

        Stream.of(names)
                .forEach(System.out::println);

        System.out.println();

        Stream.of(names)
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        Stream.of(names)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();

        Stream.of(names)
                .filter(n -> n.startsWith("B"))
                .forEach(System.out::println);
    }
}
