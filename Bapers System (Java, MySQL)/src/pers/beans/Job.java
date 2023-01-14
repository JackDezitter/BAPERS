package pers.beans;

import pers.dbutils.TaskAndJobDBOperations;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Job {

    private int jobID;
    private String urgency;
    private int customerID;
    private LocalDateTime deadline;
    private String specialInstruction;
    private ArrayList<String> tasksRemaining = new ArrayList<>();
    private float price;
    private LocalDateTime dateCreated;
    private String paidStatus;
    private int numberOfTasksRemaining;
    private LocalDateTime payByDate = null;
    private String paymentType;
    private LocalTime estimatedTimeRemaining;
    private String jobStatus;

    public Job() {}
    public Job(int customerID, String urgency, LocalDateTime deadline, String specialInstruction, String tasks) throws SQLException {
        this.customerID = customerID;
        this.urgency = urgency;
        if (deadline == null){
            if (urgency.isBlank() || urgency.toLowerCase().equals("normal")){
                deadline = LocalDateTime.now().plusDays(1);
            }
            else if(urgency.toLowerCase().equals("urgent")){
                deadline = LocalDateTime.now().plusHours(6);
                System.out.println(deadline);
                System.out.println("Urgent");
            }
            else if(urgency.toLowerCase().equals("very urgent")){
                System.out.println("very urgent");
                deadline = LocalDateTime.now().plusHours(3);
            }
        }
        else{
            this.deadline = deadline;
        }
        this.specialInstruction = specialInstruction;
        this.dateCreated = LocalDateTime.now();
        List<String> taskList = Arrays.asList(tasks.split(","));
        for (String task: taskList) {
            this.tasksRemaining.add(task);
        }
        this.jobID = TaskAndJobDBOperations.CreateJob(urgency, customerID, deadline, specialInstruction, dateCreated);

        TaskAndJobDBOperations.AddJobTasks(jobID, urgency, customerID, taskList);
        TaskAndJobDBOperations.CountJobTasks(jobID);
        TaskAndJobDBOperations.CalculateEstimatedTime(jobID);
    }

    //Getters and Setters
    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public ArrayList<String> getTasksRemaining() {
        return tasksRemaining;
    }

    public void setTasksRemaining(ArrayList<String> tasksRemaining) {
        this.tasksRemaining = tasksRemaining;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated.toLocalDateTime();
    }

    public String isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalTime getEstimatedTimeRemaining() {
        return estimatedTimeRemaining;
    }

    public void setEstimatedTimeRemaining(LocalTime estimatedTimeRemaining) {
        this.estimatedTimeRemaining = estimatedTimeRemaining;
    }

    public LocalDateTime getPayByDate() {
        return payByDate;
    }

    public void setPayByDate(Timestamp payByDate) {
        if (payByDate != null) {
            this.payByDate = payByDate.toLocalDateTime();
        }
    }

    public int getNumberOfTasksRemaining() {
        return numberOfTasksRemaining;
    }

    public void setNumberOfTasksRemaining(int numberOfTasksRemaining) {
        this.numberOfTasksRemaining = numberOfTasksRemaining;
    }
    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

}
