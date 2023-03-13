package homework.dz_2.task_5;


/*
 * Объект данного класса, будучи созданным, может принимать значения через
 * метод add. Приняв значение, объект меняет свое внутреннее состояние,
 * чтобы в любой момент времени предоставить данные о количестве переданных
 * ему элементах, минимальному их них, максимальному из них, а также о
 * среднем арифметическом всех переданных ему элементов
 */
public class StatsAccumulatorImpl implements StatsAccumulator {

    private Integer min;

    private Integer max;

    private Integer count;

    private Double sum;


    public StatsAccumulatorImpl() {

        this.count = 0;
        this.sum = 0.0;
        this.min = null;
        this.max = null;
    }


    @Override
    public void add(int value) {

        if (this.min == null || value < this.min) {
            this.min = value;
        }

        if (this.max == null || value > this.max) {
            this.max = value;
        }

        this.count++;
        this.sum += value;
    }


    @Override
    public int getMin() {
        return this.min;
    }


    @Override
    public int getMax() {
        return this.max;
    }


    @Override
    public int getCount() {
        return this.count;
    }


    @Override
    public Double getAvg() {
        return this.sum / this.count;
    }
}