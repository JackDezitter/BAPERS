package pers.beans;

import java.util.Arrays;
import java.util.List;

public class Payment {


    private String jobIDs;
    private List<String> jobIDList;
    public Payment (String jobIDs){
        this.jobIDs = jobIDs;
        jobIDList = Arrays.asList(jobIDs.split(","));

    }

    public String getJobIDs() {
        return jobIDs;
    }

    public void setJobIDs(String jobIDs) {
        this.jobIDs = jobIDs;
    }

    public List<String> getJobIDList() {
        return jobIDList;
    }

    public void setJobIDList(List<String> jobIDList) {
        this.jobIDList = jobIDList;
    }
}
