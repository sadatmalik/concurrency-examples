package studentlibrarysimulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sm@creativefusion.net
 */
public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];
        Book[] books = new Book[Constants.NUMBER_OF_BOOKS];

        ExecutorService executorService =
                Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);

        try {
            for (int i = 0; i < books.length; i++) {
                books[i] = new Book(i+1);
            }

            for (int i = 0; i < students.length; i++) {
                students[i] = new Student(i+1, books);
                executorService.execute(students[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close executor service
            executorService.shutdown();
        }
    }
}
