package io.ylab.intensive.lesson04.movie;


import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


public class MovieLoaderImpl implements MovieLoader {

    private final DataSource dataSource;


    public MovieLoaderImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    /**
     * Метод public void loadData(File file) throws SQLException, который
     * загружает данные из CSV-файла в базу данных. Он использует
     * класс BufferedReader для чтения строк из файла и класс
     * PreparedStatement для выполнения запросов к базе данных.
     * В начале метода устанавливается соединение с базой данных,
     * и подготавливается SQL-запрос для вставки данных в таблицу movie.
     * Затем метод пропускает первые две строки файла, так как они содержат
     * заголовки столбцов.
     * Далее метод читает строки из файла и разбивает каждую строку
     * на отдельные значения, используя разделитель ";". Затем он
     * создает объект Movie и устанавливает его свойства, используя
     * значения из строки.
     * Затем метод использует PreparedStatement для выполнения
     * SQL-запроса на вставку данных в базу данных. Каждое значение
     * объекта Movie устанавливается в соответствующий параметр
     * SQL-запроса с помощью методов setObject или setString
     * (для строковых значений).
     * В случае, если значение является null, используется метод
     * setNull, чтобы установить значение параметра в NULL.
     * Если во время выполнения происходит исключение, оно
     * обрабатывается в блоке catch, и в конце метода соединение
     * с базой данных закрывается в блоке finally.
     */
    @Override
    public void loadData(File file) throws SQLException {

        Connection connection = dataSource.getConnection();
        String insertMovie = "INSERT INTO movie(year, length, title," +
                " subject, actors, actress, director, popularity, awards) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file));
             PreparedStatement preparedStatement =
                     connection.prepareStatement(insertMovie)) {

            // пропуск первых двух строк
            reader.readLine();
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(";");
                Movie movie = new Movie();
                movie.setYear(parseInteger(values[0]));
                movie.setLength(parseInteger(values[1]));
                movie.setTitle(values[2]);
                movie.setActress(values[5]);
                movie.setSubject(values[3]);
                movie.setActors(values[4]);
                movie.setDirector(values[6]);
                movie.setPopularity(parseInteger(values[7]));
                movie.setAwards(parseBoolean(values[8]));

                preparedStatement.setObject(1,
                        movie.getYear(), Types.INTEGER);
                preparedStatement.setObject(2,
                        movie.getLength(), Types.INTEGER);
                preparedStatement.setString(3,
                        movie.getTitle());
                preparedStatement.setString(4,
                        movie.getSubject());
                preparedStatement.setString(5,
                        movie.getActors());
                preparedStatement.setString(6,
                        movie.getActress());
                preparedStatement.setString(7,
                        movie.getDirector());
                preparedStatement.setObject(8,
                        movie.getPopularity(), Types.INTEGER);
                if (movie.getAwards() == null) {

                    preparedStatement.setNull(9,
                            Types.BOOLEAN);
                } else {

                    preparedStatement.setBoolean(9,
                            movie.getAwards());
                }
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }


    /**
     * Метод private Integer parseInteger(String value), который преобразует
     * строковые значения в соответствующие типы данных. Если строковое
     * значение равно null или пусто, возвращается null.
     */
    private Integer parseInteger(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Integer.parseInt(value);
    }


    /**
     * Метод private Boolean parseBoolean(String value), который преобразует
     * строковые значения в соответствующие типы данных. Если строковое
     * значение равно null или пусто, возвращается null.
     */
    private Boolean parseBoolean(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Boolean.parseBoolean(value);

    }
}