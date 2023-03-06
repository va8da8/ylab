package homework.dz_1.task_1;


import java.util.Scanner;


/*
 * Программе передается 3 параметра: количество строк, количество столбцов,
 * произвольный симов. Необходимо вывести фигуру, состоящую из
 * заданного списка строк и заданного количества столбцов, и каждый
 * элемент в которой равен указанному символу.
 *  Ввод: n m c
 *  Вывод: фигура
 *  Пример:
 *  Ввод: 2 3 $
 *  Вывод: $ $ $
 *         $ $ $
 */
public class Stars {


    public static void main(String... args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {

            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            char c = template.charAt(template.length() - 1);

            char[][] arr = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    System.out.print(arr[i][j] = c);
                }
                System.out.println();
            }
        }
    }
}