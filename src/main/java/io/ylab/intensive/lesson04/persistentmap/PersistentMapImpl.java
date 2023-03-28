package io.ylab.intensive.lesson04.persistentmap;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersistentMapImpl implements PersistentMap {

    private String name;

    private DataSource dataSource;


    public PersistentMapImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    @Override
    public void init(String name) {
        this.name = name;
    }


    @Override
    public boolean containsKey(String key) throws SQLException {

        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "SELECT COUNT(*) FROM persistent_map " +
                        "WHERE map_name = ? AND key = ?");
        statement.setString(1, name);
        statement.setString(2, key);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
    }


    @Override
    public List<String> getKeys() throws SQLException {

        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "SELECT key FROM persistent_map WHERE map_name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        List<String> keys = new ArrayList<>();
        while (resultSet.next()) {

            keys.add(resultSet.getString(1));
        }
        return keys;
    }


    @Override
    public String get(String key) throws SQLException {

        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "SELECT value FROM persistent_map" +
                        " WHERE map_name = ? AND key = ?");
        statement.setString(1, name);
        statement.setString(2, key);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {

            return resultSet.getString(1);
        } else {

            return null;
        }
    }


    @Override
    public void remove(String key) throws SQLException {

        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "DELETE FROM persistent_map" +
                        " WHERE map_name = ? AND key = ?");
        statement.setString(1, name);
        statement.setString(2, key);
        statement.executeUpdate();
    }


    @Override
    public void put(String key, String value) throws SQLException {

        remove(key);
        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "INSERT INTO persistent_map" +
                        "(map_name, key, value) VALUES (?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, key);
        statement.setString(3, value);
        statement.executeUpdate();
    }


    @Override
    public void clear() throws SQLException {

        PreparedStatement statement = dataSource.getConnection()
                .prepareStatement(
                "DELETE FROM persistent_map WHERE map_name = ?");
        statement.setString(1, name);
        statement.executeUpdate();
    }
}