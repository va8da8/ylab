package io.ylab.intensive.lesson04.eventsourcing.api;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonApiImpl implements PersonApi {

    private DataSource dataSource;

    private Channel channel;


    public PersonApiImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    public PersonApiImpl() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        channel = connectionFactory.newConnection().createChannel();
        channel.queueDeclare("person_queue", false, false,
                false, null);
    }


    @Override
    public void deletePerson(Long personId) {

        // создаем сообщение-команду на удаление
        String message = "DELETE " + personId;
        // публикуем сообщение в очередь RabbitMQ
        try {

            channel.basicPublish("", "person_queue",
                    null, message
                            .getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            // обрабатываем ошибку, если не удалось отправить сообщение
            System.err.println("Error: public void " +
                    "deletePerson: " + e.getMessage());
        }
    }


    @Override
    public void savePerson(Long personId, String firstName,
                           String lastName, String middleName) {

        // создаем сообщение-команду на сохранение
        String message = "SAVE " + personId + " " + firstName + " " +
                lastName + " " + middleName;
        // публикуем сообщение в очередь RabbitMQ
        try {

            channel.basicPublish("", "person_queue",
                    null, message
                            .getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            // обрабатываем ошибку, если не удалось отправить сообщение
            System.err.println("Error: public void savePerson: " +
                    e.getMessage());
        }
    }


    @Override
    public Person findPerson(Long personId) {

        Person person = null;
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("SELECT person_id," +
                            " first_name, last_name, middle_name" +
                            " FROM person WHERE person_id = ?");
            statement.setLong(1, personId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                person = new Person(
                        resultSet.getLong("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error: public Person " +
                    "findPerson: " + e.getMessage());
        }
        return person;
    }


    @Override
    public List<Person> findAll() {

        List<Person> persons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT person_id, first_name," +
                            " last_name, middle_name FROM person");
            while (resultSet.next()) {

                Person person = new Person(
                        resultSet.getLong("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Error: public List<Person> findAll: " +
                    e.getMessage());
        }
        return persons;
    }
}