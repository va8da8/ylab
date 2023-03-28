package homework.dz_3.task_5;


import java.io.File;
import java.io.IOException;


public class Test {


    public static void main(String... args) {

        try {

            File dataFile = new Generator()
                    .generate("src/main/java/homework/" +
                            //"dz_3/task_5/data.txt", 375_000_000);
                            "dz_3/task_5/data.txt", 1_000);
            System.out.println(new Validator(dataFile).isSorted()); // false
            File sortedFile = new Sorter().sortFile(dataFile);
            System.out.println(new Validator(sortedFile).isSorted()); // true
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}