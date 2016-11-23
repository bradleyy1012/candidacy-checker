/*
 * Bradley Kovacs
 * COP4331 - Fall 2016
 * HW2 - Candidacy Checker
 */

import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 *
 * @author bradleykovacs
 */
public class CandidacyChecker {
    
    private static final String INPUT_FILE_NAME = "candidates.txt";
    private static final int MAX_APPLICANTS_TO_OUTPUT = 4;
    
    private String departmentUpForGrabs;
    private List<Applicant> choosenApplicants = new ArrayList<Applicant>();

    public CandidacyChecker(String departmentUpForGrabs) throws FileNotFoundException, IOException {
        this.departmentUpForGrabs = departmentUpForGrabs;
        BufferedReader bufferReader = new BufferedReader(new FileReader(this.INPUT_FILE_NAME));
        String line;
        while ((line = bufferReader.readLine()) != null) {
            new Applicant(line);
        }
        bufferReader.close();
    }
    
    public void findQualifiedCandidates()
    {
        for (Applicant applicant : Applicant.getApplicants()) {
            PreferredDepartment department = applicant.findSpecificDepartment(this.departmentUpForGrabs);
            if (department != null) {
                int idx;
                for (idx = 0; idx < this.choosenApplicants.size(); idx++) {
                    Applicant applicantFromArray = choosenApplicants.get(idx);
                    if (applicant.hasSimilarScore(applicantFromArray) && 
                            applicant.hasHigherPreference(applicantFromArray, departmentUpForGrabs))
                        break;
                }
                this.choosenApplicants.add(idx, applicant);  
            }
        }
    }
    
    public void writeOutputFile() throws IOException
    {
        File file = new File(this.departmentUpForGrabs + ".txt");
        if (!file.exists()) file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        
        int stop = this.choosenApplicants.size() > CandidacyChecker.MAX_APPLICANTS_TO_OUTPUT ? 
                CandidacyChecker.MAX_APPLICANTS_TO_OUTPUT : this.choosenApplicants.size();
        
        for (int i = 0; i < stop; i++) 
            bw.write(this.choosenApplicants.get(i).getName() + "\n");
        bw.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
            throws FileNotFoundException, IOException {
        
        CandidacyChecker candidacyChecker = new CandidacyChecker(args[0]);
        candidacyChecker.findQualifiedCandidates();
        candidacyChecker.writeOutputFile();
    }
}
