package homework.dz_2.task_2;


/*
 * Реализовать класс, описывающий комплексное число (действительная и мнимая
 * часть должны иметь точность double). Должны быть доступны следующие
 * операции:
 *  Создание нового числа по действительной части (конструктор с 1 параметром)
 *  Создание нового числа по действительной и мнимой части (конструктор
 * с 2 параметрами)
 *  Сложение
 *  Вычитание
 *  Умножение
 *  Операция получения модуля
 *  Преобразование в строку (toString)(арифметические действия должны
 * создавать новый экземпляр класса)
 */
public class ComplexNumbers {

    private final double real;

    private final double imaginary;


    public ComplexNumbers(double real) {
        this.real = real;
        this.imaginary = 0;
    }


    public ComplexNumbers(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }


    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }


    public ComplexNumbers add(ComplexNumbers other) {
        double re = this.real + other.real;
        double im = this.imaginary + other.imaginary;
        return new ComplexNumbers(re, im);
    }


    public ComplexNumbers subtract(ComplexNumbers other) {
        double re = this.real - other.real;
        double im = this.imaginary - other.imaginary;
        return new ComplexNumbers(re, im);
    }


    public ComplexNumbers multiply(ComplexNumbers other) {
        double re = this.real * other.real - this.imaginary * other.imaginary;
        double im = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumbers(re, im);
    }


//    public ComplexNumbers divide(ComplexNumbers other) {
//        double denomination = other.real * other.real +
//                other.imaginary * other.imaginary;
//        double re = (this.real * other.real +
//                this.imaginary * other.imaginary) / denomination;
//        double im = (this.imaginary * other.real -
//                this.real * other.imaginary) / denomination;
//        return new ComplexNumbers(re, im);
//    }


    public double modulus() {

        return Math.sqrt
                (this.real * this.real + this.imaginary * this.imaginary);
    }


    public String toString() {
        if (this.imaginary == 0) {

            return Double.toString(this.real);
        }
        if (this.real == 0) {

            return this.imaginary + "i";
        }
        if (this.imaginary < 0) {

            return this.real + " - " + (-this.imaginary) + "i";
        }
        return this.real + " + " + this.imaginary + "i";
    }
}