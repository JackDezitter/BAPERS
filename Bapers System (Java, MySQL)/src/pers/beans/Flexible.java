package pers.beans;

import pers.dbutils.DiscountDBOperations;

import java.sql.SQLException;

public class Flexible extends Discount{
    float band1;
    float band2;
    float band1Discount;
    float band2Discount;
    float band3Discount;

    public Flexible(int customerID, float band1, float band2, float band1Discount, float band2Discount, float band3Discount) throws SQLException {
        super(customerID);
        this.band1 = band1;
        this.band2 = band2;
        this.band1Discount = band1Discount;
        this.band2Discount = band2Discount;
        this.band3Discount = band3Discount;
        DiscountDBOperations.CreateFlexibleDiscount(customerID, band1, band2, band1Discount, band2Discount, band3Discount);
    }

    public float getBand1() {
        return band1;
    }

    public void setBand1(float band1) {
        this.band1 = band1;
    }

    public float getBand2() {
        return band2;
    }

    public void setBand2(float band2) {
        this.band2 = band2;
    }

    public float getBand1Discount() {
        return band1Discount;
    }

    public void setBand1Discount(float band1Discount) {
        this.band1Discount = band1Discount;
    }

    public float getBand2Discount() {
        return band2Discount;
    }

    public void setBand2Discount(float band2Discount) {
        this.band2Discount = band2Discount;
    }

    public float getBand3Discount() {
        return band3Discount;
    }

    public void setBand3Discount(float band3Discount) {
        this.band3Discount = band3Discount;
    }

}
