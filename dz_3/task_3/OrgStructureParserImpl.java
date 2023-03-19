package homework.dz_3.task_3;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class OrgStructureParserImpl implements OrgStructureParser {


    @Override
    public Employee parseStructure(File csvFile) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String headerLine = reader.readLine();
        String[] headers = headerLine.split(";");
        Map<Long, Employee> employees = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {

            String[] fields = line.split(";");
            Employee employee = new Employee();
            employee.setId(Long.parseLong(fields[0]));
            if (!fields[1].isEmpty()) {

                employee.setBossId(Long.parseLong(fields[1]));
            }
            employee.setName(fields[2]);
            employee.setPosition(fields[3]);
            employees.put(employee.getId(), employee);
        }

        reader.close();

        Employee boss = null;
        for (Employee employee : employees.values()) {

            if (employee.getBossId() == null) {

                boss = employee;
            } else {

                Employee bossEmployee = employees.get(employee.getBossId());
                employee.setBoss(bossEmployee);
                bossEmployee.getSubordinate().add(employee);
            }
        }
        return boss;
    }
}