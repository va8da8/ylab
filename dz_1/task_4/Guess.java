package homework.dz_1.task_4;


import java.util.Random;
import java.util.Scanner;


/*
 * Игра угадайка. При запуске программа загадывает число от 1 до 99
 * (включительно) и дает пользователю 10 попыток отгадать. Далее пользователь
 * начинает вводить число. И тут возможен один из следующих вариантов:
 * Пользователь отгадал число. В таком случае выводится строка
 * “Ты угадал с N попытки”, где N - номер текущей попытки пользователя
 * Пользователь ввел число, меньше загаданного. В таком случае выводится
 * сообщение “Мое число меньше! У тебя осталось M попыток” где M - количество
 * оставшихся попыток
 * Пользователь ввел число, больше загаданного. В таком случае выводится
 * сообщение “Мое число больше! У тебя осталось M попыток” где M - количество
 * оставшихся попыток
 * У пользователя закончились попытки и число не было угадано. В таком
 * случае выводится сообщение “Ты ну угадал”
 * Получить случайный элемент от 1 до 99 (включительно):
 * int number = new Random().nextInt(99) + 1;
 */
public class Guess {


    public static void main(String... args) {

        // здесь загадывается число от 1 до 99
        int number = new Random().nextInt(100);
        // здесь задается количество попыток
        int maxAttempts = 10;
        System.out.println("Я загадал число. У тебя " + maxAttempts +
                " попыток угадать.");
        int count = 1;
        Scanner scanner = new Scanner(System.in);

        do {

            int numeric = scanner.nextInt();

            if (numeric == number) {

                System.out.println("Ты угадал с " + count + " попытки!");
                break;
            }

            if (numeric > number) {

                maxAttempts--;
                count++;
                System.out.println("Мое число меньше! " + "Осталось " +
                        maxAttempts + " попыток");
            }

            if (numeric < number) {

                maxAttempts--;
                count++;
                System.out.println("Мое число больше! " + "Осталось " +
                        maxAttempts + " попыток");
            }

            if (maxAttempts == 0) {

                System.out.println("Ты не угадал");
            }
        } while (count != 11);
    }
}