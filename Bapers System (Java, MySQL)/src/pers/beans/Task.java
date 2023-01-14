package pers.beans;

import pers.dbutils.TaskAndJobDBOperations;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private int taskID;
    private String location;
    private String description;
    private float price;
    private LocalTime duration;




    public Task(String location, String description, float price, LocalTime duration) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
        this.location = location;
        this.description = description;
        this.price = price;
        this.duration = duration;

        TaskAndJobDBOperations.CreateTask(location, description, price, duration);
    }

    public Task() {

    }

    //Getters and Setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
