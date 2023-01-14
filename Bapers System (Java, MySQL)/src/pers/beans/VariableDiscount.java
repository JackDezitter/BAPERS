package pers.beans;

import pers.dbutils.DiscountDBOperations;

import java.sql.SQLException;

public class VariableDiscount extends Discount{
    int taskID;
    float discountAmount;
    public VariableDiscount(int customerID, int taskID, float discountAmount) throws SQLException {
        super(customerID);
        this.taskID = taskID;
        this.discountAmount = discountAmount;
        DiscountDBOperations.CreateVariableDiscount(customerID, taskID, discountAmount);
    }
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

}
