package io.ylab.intensive.lesson04.persistentmap;


import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.sql.SQLException;


public class PersistenceMapTest {


    public static void main(String[] args) throws SQLException {

        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);
        persistentMap.init("my_map");
        persistentMap.put("key1", "value1");
        persistentMap.put("key2", "value2");
        System.out.println("Keys: " + persistentMap.getKeys());
        System.out.println("Value for key1: " + persistentMap.get("key1"));
        persistentMap.remove("key2");
        System.out.println("Keys after removing key2: " +
                persistentMap.getKeys());
        persistentMap.clear();
        System.out.println("Keys after clearing: " +
                persistentMap.getKeys());
    }


    public static DataSource initDb() throws SQLException {

        String createMapTable = ""
                + "drop table if exists persistent_map; "
                + "CREATE TABLE if not exists persistent_map (\n"
                + "   map_name varchar,\n"
                + "   KEY varchar,\n"
                + "   value varchar\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMapTable, dataSource);
        return dataSource;
    }
}