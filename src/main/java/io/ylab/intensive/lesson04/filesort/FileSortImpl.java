package io.ylab.intensive.lesson04.filesort;


import javax.sql.DataSource;
import java.io.*;
import java.sql.*;


public class FileSortImpl implements FileSorter {

    private final DataSource dataSource;


    public FileSortImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    @Override
    public File sort(File data) throws SQLException, IOException {

        // загрузку данных из txt-файла в таблицу базы данных numbers
        loadData(data);

        // сортировка данных в таблице numbers
        sortData();

        // выгрузка отсортированных данных из таблицы sorted_numbers
        // в новый txt-файл
        return saveSortedDataToFile();
    }


    /**
     * Метод private void loadData(File data) throws SQLException, который
     * считывает txt-файл построчно с помощью BufferedReader,
     * парсит строку в число типа long и добавляет его в PreparedStatement.
     * Метод использует batch-processing.
     */
    private void loadData(File data) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO numbers (val)" +
                             " VALUES (?)")) {

            BufferedReader reader = new BufferedReader(new FileReader(data));
            String line;
            int batchSize = 0;
            while ((line = reader.readLine()) != null) {

                statement.setLong(1, Long.parseLong(line));
                statement.addBatch();
                if (++batchSize % 1000 == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
        } catch (IOException e) {
            throw new RuntimeException("Error reading data file", e);
        }
    }


    /**
     * Метод private void sortData() throws SQLException, который
     * создает индекс на столбце val таблицы numbers и создает новую
     * таблицу sorted_numbers, содержащую те же данные, отсортированные
     * в порядке убывания.
     */
    private void sortData() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate
                    ("CREATE INDEX idx_val ON numbers (val)");
            statement.executeUpdate
                    ("drop table if exists sorted_numbers;" +
                            "CREATE TABLE if not exists sorted_numbers " +
                            "AS SELECT * FROM numbers ORDER BY val DESC");
        }
    }


    /**
     * Метод  private File saveSortedDataToFile() throws SQLException,
     * IOException, который открывает новый PrintWriter для создания
     * нового txt-файла sorted_data.txt и считывает отсортированные
     * данные из таблицы sorted_numbers с помощью ResultSet.
     * Затем каждое число типа long записывается в файл с помощью
     * writer.println.
     */
    private File saveSortedDataToFile() throws SQLException, IOException {

        File outputFile = new File("src/main/java/io/ylab/" +
                "intensive/lesson04/filesort/sorted_data.txt");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             PrintWriter writer = new PrintWriter(outputFile)) {

            ResultSet resultSet = statement.executeQuery
                    ("SELECT val FROM sorted_numbers");
            while (resultSet.next()) {

                writer.println(resultSet.getLong("val"));
            }
        }
        return outputFile;
    }
}