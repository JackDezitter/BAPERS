/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.beans;

/**
 *
 * @author Alia
 */
public class DiscountType {
    String discountType;
    Long discountTypeId;

    @Override
    public String toString(){
        return discountType;
    }

    public DiscountType(String discountType, Long discountTypeId) {
        this.discountType = discountType;
        this.discountTypeId = discountTypeId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Long getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(Long discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

}
