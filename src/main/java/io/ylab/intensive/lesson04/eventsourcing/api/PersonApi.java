package io.ylab.intensive.lesson04.eventsourcing.api;


import io.ylab.intensive.lesson04.eventsourcing.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;


public interface PersonApi {


    void deletePerson(Long personId) throws IOException,
            TimeoutException, SQLException;


    void savePerson(Long personId, String firstName, String lastName,
                    String middleName) throws IOException, SQLException,
            TimeoutException;


    Person findPerson(Long personId);


    List<Person> findAll();
}