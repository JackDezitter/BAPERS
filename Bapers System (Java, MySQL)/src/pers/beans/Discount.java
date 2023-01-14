package pers.beans;

public class Discount {

    int customerID;
    public Discount(int customerID){
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

