package pers.beans;

import pers.beans.Payment;

import java.time.LocalDate;
import java.util.List;

public class CardPayment extends Payment {


    String cardType;
    String cardNumber;
    String cvv;
    LocalDate expiryDate;
    List<String> jobIDList;
    public CardPayment(String jobIDs, String cardType, String cardNumber, String cvv, LocalDate expiryDate) {
        super(jobIDs);
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.jobIDList = this.getJobIDList();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
