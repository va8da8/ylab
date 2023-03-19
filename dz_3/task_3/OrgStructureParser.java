package homework.dz_3.task_3;


import java.io.File;
import java.io.IOException;


public interface OrgStructureParser {


    public Employee parseStructure(File csvFile) throws IOException;
}