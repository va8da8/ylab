package homework.dz_3.task_2;


public class DatedMapTest {


    public static void main(String... args) {

        DatedMap datedMap = new DatedMapImpl();
        datedMap.put("key1", "value1");
        datedMap.put("key2", "value2");
        System.out.println(datedMap.get("key1"));
        System.out.println(datedMap.containsKey("key2"));
        datedMap.remove("key1");
        System.out.println(datedMap.keySet());
        System.out.println(datedMap.getKeyLastInsertionDate("key2"));
    }
}