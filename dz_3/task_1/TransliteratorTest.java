package homework.dz_3.task_1;


public class TransliteratorTest {


    public static void main(String... args) {

        Transliterator transliterator = new TransliteratorImpl();
        String res = transliterator
                .transliterate("HELLO! ПРИВЕТ! Go, boy-!");
        System.out.println(res);
    }
}