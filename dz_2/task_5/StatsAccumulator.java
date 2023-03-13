package homework.dz_2.task_5;


public interface StatsAccumulator {


    /**
     * Добавляет число в аккумулятор. Вызывается многократно
     *
     * @param value число
     */
    void add(int value);


    /**
     * Возвращает минимальное из всех добавленных чисел
     *
     * @return минимальное из всех добавленных чисел
     */
    int getMin();


    /**
     * Возвращает максимальное из всех добавленных чисел
     *
     * @return максимальное из всех добавленных чисел
     */
    int getMax();


    /**
     * Возвращает количество всех добавленных чисел
     *
     * @return количество добавленных чисел
     */
    int getCount();


    /**
     * Возвращает среднее арифметическое всех добавленных чисел
     *
     * @return среднее арифметическое всех добавленных
     */
    Double getAvg();
}