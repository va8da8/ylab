package homework.dz_2.task_2;


public class ComplexNumberTest {


    public static void main(String... args) {

        ComplexNumbers complexNumbers = new ComplexNumbers(-4, 2);
        System.out.println(complexNumbers
                .add(new ComplexNumbers(1, -3)));
        System.out.println(complexNumbers
                .subtract(new ComplexNumbers(1, -3)));
        System.out.println(complexNumbers
                .multiply(new ComplexNumbers(1, -3)));
//        System.out.println(complexNumbers
//                .divide(new ComplexNumbers(1,-3)));
        System.out.println(complexNumbers.modulus());
    }
}