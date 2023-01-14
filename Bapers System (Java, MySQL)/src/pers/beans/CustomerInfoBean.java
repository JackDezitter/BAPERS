/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.beans;

/**
 *
 * @author Talha
 */
public class CustomerInfoBean {

    //Private Attributes
    private Integer customerId;
    private String firstName;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private Long discountTypeId;
    private String customerTypeID;

    public Integer getCustomerID() {
        return customerId;
    }

    public void setCustomerID(Integer customerID) {
        this.customerId = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(Long discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }


}
