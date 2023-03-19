package homework.dz_3.task_2;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class DatedMapImpl implements DatedMap {

    //  Для хранения ключей и значений
    private final Map<String, String> map;

    // Для хранения времени добавления каждого ключа
    private final Map<String, Date> insertionDates;


    public DatedMapImpl() {

        this.map = new HashMap<>();
        this.insertionDates = new HashMap<>();
    }


    @Override
    public void put(String key, String value) {

        Date date = new Date();
        map.put(key, value);
        insertionDates.put(key, date);
    }


    @Override
    public String get(String key) { return map.get(key); }


    @Override
    public boolean containsKey(String key) { return map.containsKey(key); }


    @Override
    public void remove(String key) {

        map.remove(key);
        insertionDates.remove(key);
    }


    @Override
    public Set<String> keySet() { return map.keySet(); }


    @Override
    public Date getKeyLastInsertionDate(String key) {

        return insertionDates.get(key);
    }
}