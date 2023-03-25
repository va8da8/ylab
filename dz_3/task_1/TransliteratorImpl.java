package homework.dz_3.task_1;


import java.util.HashMap;
import java.util.Map;

public class TransliteratorImpl implements Transliterator {

    private final Map<Character, String> translitMap = new HashMap<>();


    public TransliteratorImpl() {

        char[] russian = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж',
                'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
                'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ',
                'Ы', 'Ь', 'Э', 'Ю', 'Я'};

        String[] translit = {"A", "B", "V", "G", "D", "E", "E", "ZH",
                "Z", "I", "I", "K", "L", "M", "N", "O", "P", "R", "S",
                "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "Y",
                "", "IE", "E", "IU", "IA"};

        for (int i = 0; i < russian.length; i++) {

            // сопоставляем каждую русскую букву из массива russian
            // с соответствующей латинской буквой из массива translit
            translitMap.put(russian[i], translit[i]);
        }
    }


    @Override
    public String transliterate(String source) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {

            char c = source.charAt(i);
            // проверяем наличие соответствующего символа в HashMap
            String decodedChar = translitMap.getOrDefault(c,
                    Character.toString(c));
            stringBuilder.append(decodedChar);
        }
        return stringBuilder.toString();
    }


//    private final String[] trans = {"A", "B", "V", "G", "D", "E", "E",
//            "ZH", "Z", "I", "I", "K", "L", "M", "N", "O", "P", "R",
//            "S", "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "Y",
//            "", "IE", "E", "IU", "IA"};
//
//    private final char[] russia = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
//            'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
//            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы',
//            'Ь', 'Э', 'Ю', 'Я'};
//
//
//    @Override
//    public String transliterate(String source) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < source.length(); i++) {
//
//            char c = source.charAt(i);
//            String decodedChar = decode(c);
//            stringBuilder.append(decodedChar);
//        }
//        return stringBuilder.toString();
//    }
//
//
//    private String decode(char c) {
//
//        // Ищем индекс буквы c в массиве russia
//        int index = -1;
//        for (int i = 0; i < russia.length; i++) {
//
//            if (russia[i] == c) {
//                index = i;
//                break;
//            }
//        }
//
//        // Если индекс не найден, возвращаем с без изменений
//        if (index == -1) { return Character.toString(c); }
//        // Иначе возвращаем соответствующую латинскую букву из
//        // массива trans
//        return trans[index];
//    }
}