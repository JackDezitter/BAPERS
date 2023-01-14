package pers.beans;

import pers.dbutils.DiscountDBOperations;

import java.sql.SQLException;

public class FixedDiscount extends Discount{
    float discountAmount;
    public FixedDiscount(int customerID, float discountAmount) throws SQLException {
        super(customerID);
        this.discountAmount = discountAmount;
        DiscountDBOperations.CreateFixedDiscount(customerID, discountAmount);

    }
    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
