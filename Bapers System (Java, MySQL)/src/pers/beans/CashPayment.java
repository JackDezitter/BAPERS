package pers.beans;

import pers.beans.Payment;

public class CashPayment extends Payment {
    public CashPayment(String paymentType, String jobIDs) {
        super(jobIDs);
    }
}
