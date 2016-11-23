/**
 *
 * @author bradleykovacs
 */
public class PreferredDepartment {
    private String name;
    private int preference;
    
    public PreferredDepartment(String name, int preference)
    {
        this.name = name;
        this.preference = preference;  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }
    
}
