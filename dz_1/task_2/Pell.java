package homework.dz_1.task_2;


import java.util.Scanner;


/*
 * На вход подается число n (0 <= n <= 30), необходимо распечатать
 * n-e число.
 * Пример:
 * Ввод: 5
 * Вывод: 29
 */
public class Pell {


    public static void main(String... args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {

            int n = scanner.nextInt();

            double p = ( ((Math.pow((1 + Math.sqrt(2)), n)) -
                    (Math.pow((1 - Math.sqrt(2)), n))) / (2 * Math.sqrt(2)));
            System.out.println((int) Math.ceil(p));
        }
    }
}