import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bradleykovacs
 */
public class Department {
    private String name;
    private List<Applicant> applicants_selected;
    
    public static List<Department> departments = new ArrayList<Department>();
    
    public Department(String name)
    {
        this.name = name;
        applicants_selected = new ArrayList<Applicant>();
        departments.add(this);
    }
}
