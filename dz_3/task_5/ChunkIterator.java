package homework.dz_3.task_5;


import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Класс ChunkIterator реализует интерфейсы Iterator<Long>,
 * Comparable<ChunkIterator> и Closeable. Он имеет два поля:
 * reader - объект BufferedReader для чтения файла, и
 * nextValue - следующее значение, которое должен вернуть итератор.
 */
public class ChunkIterator implements Iterator<Long>,
        Comparable<ChunkIterator>, Closeable {

    private final BufferedReader reader;

    private Long nextValue;


    /**
     * Конструктор ChunkIterator принимает объект File,
     * открывает BufferedReader для чтения файла и инициализирует
     * поле nextValue вызовом метода getNextValue(), который читает
     * первую строку файла и преобразует ее в Long.
     */
    public ChunkIterator(File file) throws IOException {

        reader = new BufferedReader(new FileReader(file));
        nextValue = getNextValue();
    }


    /**
     * Метод private Long getNextValue() throws IOException, который
     * читает следующую строку файла, преобразует ее в Long и возвращает
     * его. Если строка пуста, метод закрывает BufferedReader и
     * возвращает null.
     */
    private Long getNextValue() throws IOException {

        String line = reader.readLine();
        if (line != null) {

            return Long.parseLong(line);
        } else {

            close();
            return null;
        }
    }


    /**
     * Метод public boolean hasNext(), который возвращает true,
     * если nextValue не равен null, что означает, что есть еще
     * значения для чтения.
     */
    @Override
    public boolean hasNext() { return nextValue != null; }


    /**
     * Метод public Long next(), который возвращает nextValue и затем
     * вызывает getNextValue() для чтения следующего значения.
     * Если при вызове getNextValue() возникает ошибка IOException,
     * метод next() выбрасывает исключение NoSuchElementException.
     */
    @Override
    public Long next() {

        Long value = nextValue;
        try {

            nextValue = getNextValue();
        } catch (IOException e) {

            throw new NoSuchElementException
                    ("Не удалось прочитать следующее значение"
                            + e.getMessage());
        }
        return value;
    }


    /**
     * Метод public void close() throws IOException, который
     * закрывает BufferedReader.
     */
    @Override
    public void close() throws IOException { reader.close(); }


    /**
     * Метод  public int compareTo(ChunkIterator other), который
     * реализует сравнение двух итераторов по их nextValue.
     * Он возвращает результат сравнения nextValue данного
     * итератора с nextValue другого итератора.
     */
    @Override
    public int compareTo(ChunkIterator other) {

        return Long.compare(nextValue, other.nextValue);
    }
}