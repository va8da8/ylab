package homework.dz_2.task_1;


public class SequencesImpl implements SequenceGenerator {


    private void methodFor(int number, int n, String str) {

        System.out.print(str + " ");
        for (int i = 0; i < n; i++) {

            if (i == n - 1) {

                System.out.print((number + 2) + "...");
                System.out.println();
                break;
            }
            System.out.print((number += 2) + ", ");
        }
    }


    // A. 2, 4, 6, 8, 10...
    @Override
    public void a(int n) {

        String str = "A.";
        int number = 0;
        methodFor(number, n, str);
    }


    // B. 1, 3, 5, 7, 9...
    @Override
    public void b(int n) {

        String str = "B.";
        int number = -1;
        methodFor(number, n, str);
    }


    // C. 1, 4, 9, 16, 25...
    @Override
    public void c(int n) {

        System.out.print("C. ");
        for (int i = 1; i <= n; i++) {

            int number = i * i;
            if (i == n) {

                System.out.print(number + "...");
                System.out.println();
                break;
            }
            System.out.print(number + ", ");
        }
    }


    // D. 1, 8, 27, 64, 125...
    @Override
    public void d(int n) {

        System.out.print("D. ");
        for (int i = 1; i <= n; i++) {

            int number = i * i * i;
            if (i == n) {

                System.out.print(number + "...");
                System.out.println();
                break;
            }
            System.out.print(number + ", ");
        }
    }


    // E. 1, -1, 1, -1, 1, -1...
    @Override
    public void e(int n) {

        System.out.print("E. ");
        int number = 1;
        int number_1 = -1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {

                if (i == n - 1) {

                    System.out.print(number + "...");
                    System.out.println();
                    break;
                }
                System.out.print(number + ", ");
            } else {
                if (i == n - 1) {

                    System.out.print(number_1 + "...");
                    System.out.println();
                    break;
                }
                System.out.print(number_1 + ", ");
            }
        }
    }


    // F. 1, -2, 3, -4, 5, -6...
    @Override
    public void f(int n) {

        System.out.print("F. ");
        int number = 0;
        for (int i = 0; i < n; i++) {

            number++;
            if (i % 2 == 0) {

                if (i == n - 1) {

                    System.out.print(number + "...");
                    System.out.println();
                    break;
                }
                System.out.print(number + ", ");
            } else {

                if (i == n - 1) {

                    System.out.print(-number + "...");
                    System.out.println();
                    break;
                }
                System.out.print(-number + ", ");
            }
        }
    }


    // G. 1, -4, 9, -16, 25....
    @Override
    public void g(int n) {

        System.out.print("G. ");
        for (int i = 1; i <= n; i++) {

            int number = i * i;
            if (i % 2 == 0) {

                if (i == n) {

                    System.out.print(-number + "....");
                    System.out.println();
                    break;
                }
                System.out.print(-number + ", ");
            } else {

                if (i == n) {

                    System.out.print(number + "....");
                    System.out.println();
                    break;
                }
                System.out.print(number + ", ");
            }
        }
    }


    // H. 1, 0, 2, 0, 3, 0, 4....
    @Override
    public void h(int n) {

        System.out.print("H. ");
        int number = 1;
        for (int i = 1; i <= n; i++) {

            if (i == n) {

                System.out.print(number + "....");
                System.out.println();
                break;
            }
            System.out.print(number++ + ", ");

            i++;

            if (i == n) {

                System.out.print(0 + "....");
                System.out.println();
                break;
            }
            System.out.print(0 + ", ");
        }
    }


    // I. 1, 2, 6, 24, 120, 720...
    @Override
    public void i(int n) {

        System.out.print("I. ");
        int number = 1;
        for (int i = 0; i < n; i++) {

            number = number * (i + 1);
            if (i == n - 1) {

                System.out.print(number + "...");
                System.out.println();
                break;
            }
            System.out.print(number + ", ");
        }
    }


    // J. 1, 1, 2, 3, 5, 8, 13, 21...
    @Override
    public void j(int n) {

        System.out.print("J. ");
        int number_0 = 1;
        int number_1 = 1;
        int number_2;
        System.out.print(number_0 + ", " + number_1 + ", ");
        for (int i = 3; i <= n; i++) {

            number_2 = number_0 + number_1;
            if (i == n) {

                System.out.print(number_2 + "...");
                System.out.println();
                break;
            }
            System.out.print(number_2 + ", ");
            number_0 = number_1;
            number_1 = number_2;
        }
    }
}