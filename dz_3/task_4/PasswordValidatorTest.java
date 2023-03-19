package homework.dz_3.task_4;


public class PasswordValidatorTest {


    public static void main(String... args) {

        boolean result = register(
                "valid_login",
                "valid_password",
                "valid_password");
        System.out.println(result);
    }


    public static boolean register
            (String login, String password, String confirmPassword) {

        try {
            if (!login.matches("[a-zA-Z0-9_]+")) {

                throw new WrongLoginException
                        ("Логин содержит недопустимые символы");
            }

            if (login.length() >= 20) {

                throw new WrongLoginException("Логин слишком длинный");
            }

            if (!password.matches("[a-zA-Z0-9_]+")) {

                throw new WrongPasswordException
                        ("Пароль содержит недопустимые символы");
            }

            if (password.length() >= 20) {

                throw new WrongPasswordException("Пароль слишком длинный");
            }

            if (!password.equals(confirmPassword)) {

                throw new WrongPasswordException
                        ("Пароль и подтверждение не совпадают");
            }
        } catch (WrongLoginException | WrongPasswordException e) {

            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}