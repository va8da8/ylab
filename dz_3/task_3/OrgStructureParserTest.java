package homework.dz_3.task_3;


import java.io.File;
import java.io.IOException;


public class OrgStructureParserTest {


    public static void main(String... args) {

        File csvFile = new File
                ("src/main/java/homework/dz_3/task_3/file.csv");
        OrgStructureParser parser = new OrgStructureParserImpl();
        try {

            Employee boss = parser.parseStructure(csvFile);
            System.out.println("Босс: " + boss.getName());
            System.out.println("Прямые подчиненные:");
            for (Employee subordinate : boss.getSubordinate()) {

                System.out.println("- " + subordinate.getName());
                for (Employee el : subordinate.getSubordinate()) {

                    System.out.print("  подчиненные: ");
                    System.out.println("- " + el.getName());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка " + e.getMessage());
        }
    }
}