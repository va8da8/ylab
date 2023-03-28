package io.ylab.intensive.lesson04.eventsourcing.db;


import com.rabbitmq.client.*;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbApp {

    private final static String QUEUE_NAME = "person_queue";


    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = initMQ();
        DataSource dataSource = initDb();

        try (Connection conn = dataSource.getConnection();
             Channel channel = connectionFactory.newConnection()
                     .createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false,
                    false, null);
            Consumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    if (message.contains("SAVE")) {

                        String[] parts = message.split(" ");
                        long personId = Long.parseLong(parts[1]);
                        String firstName = parts[2];
                        String lastName = parts[3];
                        String middleName = parts[4];
                        try (PreparedStatement statement =
                                     conn.prepareStatement(
                                "SELECT COUNT(*) FROM person" +
                                        " WHERE person_id = ?")) {

                            statement.setLong(1, personId);
                            ResultSet resultSet = statement.executeQuery();
                            resultSet.next();
                            int count = resultSet.getInt(1);
                            resultSet.close();
                            if (count == 0) {

                                try (PreparedStatement insertStatement =
                                             conn.prepareStatement(
                                        "INSERT INTO person " +
                                                "(person_id, first_name," +
                                                " last_name, middle_name)" +
                                                " VALUES (?, ?, ?, ?)")) {
                                    insertStatement.setLong(1,
                                            personId);
                                    insertStatement.setString(2,
                                            firstName);
                                    insertStatement.setString(3,
                                            lastName);
                                    insertStatement.setString(4,
                                            middleName);
                                    insertStatement.executeUpdate();
                                }
                            } else {

                                try (PreparedStatement updateStatement =
                                             conn.prepareStatement(
                                        "UPDATE person SET " +
                                                "first_name = ?, " +
                                                "last_name = ?, " +
                                                "middle_name = ? " +
                                                "WHERE person_id = ?")) {
                                    updateStatement.setString(1,
                                            firstName);
                                    updateStatement.setString(2,
                                            lastName);
                                    updateStatement.setString(3,
                                            middleName);
                                    updateStatement.setLong(4,
                                            personId);
                                    updateStatement.executeUpdate();
                                }
                            }
                        } catch (SQLException e) {
                            System.err.println("Error: if " +
                                    "(message.contains(\"SAVE\")): " +
                                    e.getMessage());
                        }
                    }

                    if (message.contains("DELETE")) {
                        String[] parts = message.split(" ");
                        long personId = Long.parseLong(parts[1]);
                        try (PreparedStatement statement =
                                     conn.prepareStatement(
                                "DELETE FROM person WHERE" +
                                        " person_id = ?")) {

                            statement.setLong(1, personId);
                            int rowsDeleted = statement.executeUpdate();
                            if (rowsDeleted == 0) {
                                System.out.println("Ни один человек с" +
                                        " идентификатором " + personId +
                                        " не найден для удаления.");
                            }
                        } catch (SQLException e) {
                            System.err.println("Error: if " +
                                    "(message.contains(\"DELETE\")): " +
                                    e.getMessage());
                        }
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume(QUEUE_NAME, false, consumer);

            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static ConnectionFactory initMQ() throws Exception {

        return RabbitMQUtil.buildConnectionFactory();
    }


    private static DataSource initDb() throws SQLException {

        String ddl = ""
                + "drop table if exists person;"
                + "CREATE TABLE if not exists person (\n"
                + "person_id bigint primary key,\n"
                + "first_name varchar,\n"
                + "last_name varchar,\n"
                + "middle_name varchar\n"
                + ")";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }

}