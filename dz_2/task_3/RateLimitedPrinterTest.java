package homework.dz_2.task_3;


public class RateLimitedPrinterTest {


    public static void main(String... args) {

        RateLimitedPrinter rateLimitedPrinter =
                new RateLimitedPrinter(1000);
        for (int i = 0; i < 1_000_000_000; i++) {

            rateLimitedPrinter.print(String.valueOf(i));
        }
    }
}