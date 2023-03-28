package io.ylab.intensive.lesson04.eventsourcing.api;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


public class ApiApp {

    private final static String QUEUE_NAME = "person_queue";


    public static void main(String[] args) throws Exception {

        DataSource dataSource = initDb();
        ConnectionFactory connectionFactory = initMQ();

        Channel channel;
        PersonApi personApi = new PersonApiImpl();
        PersonApi dpersonApi = new PersonApiImpl(dataSource);
        channel = connectionFactory.newConnection().createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false,
                null);

        // вызываем метод savePerson
        personApi.savePerson(1L, "Иванов", "Иван",
                "Иванович");
        personApi.savePerson(456L, "Иванович", "Иван",
                "Иванович");
        personApi.savePerson(456L, "Иванов", "Иван",
                "Иванович");

        // вызываем метод findPerson()
        Person person = dpersonApi.findPerson(2L);
        System.out.println(person);
        System.out.println();

        // вызываем метод deletePerson() для удаления personId = 1
        personApi.deletePerson(2L);

        // вызываем метод findAll()
        List<Person> persons = dpersonApi.findAll();
        for (Person p : persons) {
            System.out.println(p);
        }
    }


    private static ConnectionFactory initMQ() throws Exception {

        return RabbitMQUtil.buildConnectionFactory();
    }


    private static DataSource initDb() throws SQLException {

        return DbUtil.buildDataSource();
    }
}