package homework.dz_2.task_4;


public class SNILSValidatorImpl implements SNILSValidator {


    @Override
    public boolean validate(String snils) {

        int index = 9;
        int sum = 0;
        char[] arr = snils.toCharArray();

        if (arr.length != 11) {

            System.out.println("Номер введен некорректно");
            return false;
        }

        for (int i = 0; i < 9; i++) {

            int x = Character.digit(arr[i], 10);
            if (x < 0) {

                System.out.println("Номер введен некорректно");
                return false;
            }
            sum += x * index;
            index--;

        }
        // если раскомментировать, можно посмотреть значение суммы
        //System.out.println(sum);

        while (sum >= 100) {
            if (sum == 100) {

                sum = 0;
            }
            if (sum > 100) {

                sum = sum % 101;
            }
        }

        StringBuilder checkNumber = new StringBuilder();
        for (int i = 9; i < 11; i++) {

            int x = Character.digit(arr[i], 10);
            if (x < 0) {

                System.out.println("Номер введен некорректно");
                return false;
            }
            checkNumber.append(x);
        }
        int checkNumberInt = Integer.parseInt(checkNumber.toString());

        return sum == checkNumberInt;
    }
}