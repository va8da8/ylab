package homework.dz_1.task_3;


/*
 * На вход ничего не подается, необходимо распечатать таблицу умножения
 * чисел от 1 до 9 (включая)
 * Пример:
 * Вывод:
 * 1 x 1 = 1
 * 1 x 2 = 2
 * …
 * <часть вывода пропущена>
 * …
 * 9 x 9 = 81
 */
public class MultTable {


    public static void main(String... args) throws Exception {

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {

                System.out.println(i + " X " + j + " = " + (i * j));
            }
            System.out.println();
        }
    }
}