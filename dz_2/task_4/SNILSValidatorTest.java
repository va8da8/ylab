package homework.dz_2.task_4;


public class SNILSValidatorTest {


    public static void main(String... args) {

        //false
        System.out.println(new SNILSValidatorImpl()
                .validate("01468870570"));
        //true
        System.out.println(new SNILSValidatorImpl()
                .validate("90114404441"));
    }
}