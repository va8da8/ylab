package homework.dz_3.task_1;


public class TransliteratorImpl implements Transliterator {

    private final String[] trans = {"A", "B", "V", "G", "D", "E", "E", "ZH",
            "Z", "I", "I", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U",
            "F", "KH", "TS", "CH", "SH", "SHCH", "Y", "", "IE", "E", "IU",
            "IA"};

    private final char[] russia = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж',
            'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
            'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};


    @Override
    public String transliterate(String source) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {

            char c = source.charAt(i);
            String decodedChar = decode(c);
            result.append(decodedChar);
        }
        return result.toString();
    }


    private String decode(char c) {

        // Ищем индекс буквы c в массиве russia
        int index = -1;
        for (int i = 0; i < russia.length; i++) {

            if (russia[i] == c) {
                index = i;
                break;
            }
        }

        // Если индекс не найден, возвращаем с без изменений
        if (index == -1) { return Character.toString(c); }
        // Иначе возвращаем соответствующую латинскую букву из массива trans
        return trans[index];
    }
}