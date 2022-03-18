package streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sm@creativefusion.net
 */
public class StreamsWithFiles {

    public static void main(String[] args) throws IOException {

        String path = "/Users/sadatmalik/Desktop/java-projects/concurrency/src/main/java/streamapi/names";

        Stream<String> names = Files.lines(Paths.get(path));

        names.forEach(System.out::println);

        // refresh the stream
        names = Files.lines(Paths.get(path));

        List<String> nameList = names.collect(Collectors.toList());

        nameList.stream().forEach(System.out::println);

        nameList.stream()
                .filter(n -> n.startsWith("S"))
                .forEach(System.out::println);
    }
}
