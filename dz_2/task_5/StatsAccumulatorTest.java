package homework.dz_2.task_5;


public class StatsAccumulatorTest {


    public static void main(String... args) {

        StatsAccumulatorImpl s = new StatsAccumulatorImpl();
        s.add(1);
        s.add(2);
        // 1.5 - среднее арифметическое элементов
        System.out.println(s.getAvg());
        s.add(0);
        // 0 - минимальное из переданных значений
        System.out.println(s.getMin());
        s.add(3);
        s.add(8);
        // 8 - максимальный из переданных
        System.out.println(s.getMax());
        // 5 - количество переданных элементов
        System.out.println(s.getCount());
    }
}