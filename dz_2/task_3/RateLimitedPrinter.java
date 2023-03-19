package homework.dz_2.task_3;


/*
 * Реализовать класс RateLimiterPrinter. Класс имеет конструктор, в
 * который передается interval и метод print(), в который передается строка.
 * Класс функционирует по следующему принципу: на объекте класса вызывается
 * метод print(). Далее идет проверка, когда был последний вывод в консоль.
 * Если интервал (в миллисекундах) между последним состоявшимся выводом и
 * текущим выводом больше значения interval, переданного в конструктор - то
 * происходит вывод значения. Иначе - не происходит, и сообщение
 * отбрасывается. То есть класс ограничивает частоту вывода в консоль.
 * Другими словами, сообщение не будет выводится чаще чем 1 раз в interval
 * милисекунд. Реализовать описанный класс.
 */
public class RateLimitedPrinter {

    private final int interval;

    private long lastPrintTime;


    public RateLimitedPrinter(int interval) {
        this.interval = interval;
        this.lastPrintTime = 0;
    }


    // чтобы предотвратить параллельный доступ, делаем метод synchronized
    public synchronized void print(String message) {

        long currentTime = System.currentTimeMillis();

        if (currentTime - this.lastPrintTime >= this.interval) {

            System.out.println(message);
            this.lastPrintTime = currentTime;
        }
    }
}