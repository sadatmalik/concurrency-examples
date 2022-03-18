package streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sm@creativefusion.net
 */
public class StreamsWithCustomObjects {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Adam", true));
        students.add(new Student("Sue", false));
        students.add(new Student("Kevin", false));
        students.add(new Student("Joe", true));
        students.add(new Student("Daniel", true));

        students.stream()
                .map(Student::getName)
                .filter(n -> n.startsWith("D"))
                .forEach(System.out::println);

        System.out.println();

        students.stream()
                .filter(Student::isLocal)
                .forEach(s -> System.out.println(s.getName()));

        System.out.println();

        long count = students.stream()
                .filter(s -> !s.isLocal())
                .count();

        System.out.println("Count = " + count);

        System.out.println();

        String locals = students.stream()
                .filter(s -> s.isLocal())
                .map(Student::getName)
                .collect(Collectors.joining(" "));

        System.out.println("Locals = " + locals);
    }
}
