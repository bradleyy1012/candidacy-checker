import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bradleykovacs
 */
public class Applicant {
    
    public static List<Applicant> applicants = new ArrayList<Applicant>();
    
    private String name;
    private double totalScore;
    private double writtenExam;
    private double programmingExam;
    private List<PreferredDepartment> preferredFields  = new ArrayList<>();
    
    public Applicant(String line)
    {
        String[] tokens = line.split(";");
        this.name = tokens[0];
        this.writtenExam = Double.parseDouble(tokens[1]);
        this.programmingExam = Double.parseDouble(tokens[2]);
        this.totalScore = (0.4 * this.writtenExam) + 
                (0.6 * this.programmingExam);
        
        // See if the applicant has previously been scraped
        int idx;
        Applicant t = null;
        for (idx = 0; idx < applicants.size(); idx++) {
            if (applicants.get(idx).getName().compareTo(this.name) == 0) {
                t = applicants.get(idx);
                break;
            }
        }
        
        // If this is a new applicant
        if (t == null) {
            this.preferredFields.add(
                    new PreferredDepartment(tokens[3], Integer.parseInt(tokens[4]))
            );
            
            for (idx = 0; idx < applicants.size(); idx++) {
                if (this.totalScore > applicants.get(idx).totalScore) break;
            }  
            applicants.add(idx, this);
        }
        else {
            // Calculate the index for where the department should go in their preferredFields
            PreferredDepartment field = new PreferredDepartment(tokens[3], Integer.parseInt(tokens[4]));
            for (idx = 0; idx < t.preferredFields.size(); idx++) {
                if (field.getPreference() < t.preferredFields.get(idx).getPreference()) 
                    break;
            }
            // Keep that shit in order so we don't need to iterate again 8)
            t.preferredFields.add(
                    idx, field
            );
        }
    }
    
    public PreferredDepartment getSpecificPreference(String department) 
    {
        for (PreferredDepartment d : this.getPreferredFields()) {
                if (d.getName().compareTo(department) == 0) 
                    return d;
            }
        return null;
    }
    
    public PreferredDepartment findSpecificDepartment(String name)
    {
        for (PreferredDepartment dept : this.getPreferredFields()) {
            if (dept.getName().compareTo(name) == 0)
                return dept;
        }
        return null;
    }
    
    public boolean hasHigherPreference(Applicant other, String department)
    {
        PreferredDepartment arrayPrefDept = other.getSpecificPreference(department);
        PreferredDepartment applicantPrefDept = this.getSpecificPreference(department);
        return applicantPrefDept.getPreference() < arrayPrefDept.getPreference();
    }
    
    public boolean hasSimilarScore(Applicant applicant)
    {
        return (Math.abs(this.getTotalScore() - applicant.getTotalScore()) <= 1);
    }

    public List<PreferredDepartment> getPreferredFields() {
        return preferredFields;
    }

    public void setPreferredFields(List<PreferredDepartment> preferredFields) {
        this.preferredFields = preferredFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWrittenExam() {
        return writtenExam;
    }

    public void setWrittenExam(double writtenExam) {
        this.writtenExam = writtenExam;
    }

    public double getProgrammingExam() {
        return programmingExam;
    }

    public void setProgrammingExam(double programmingExam) {
        this.programmingExam = programmingExam;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
    
    public static List<Applicant> getApplicants() {
        return applicants;
    }

    public static void setApplicants(List<Applicant> applicants) {
        Applicant.applicants = applicants;
    }
    
}
