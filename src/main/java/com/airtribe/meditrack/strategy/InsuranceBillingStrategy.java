package com.airtribe.meditrack.strategy;

public class InsuranceBillingStrategy implements  BillingStrategy{
    @Override
    public double calculateBill(double consultationFee, double extraCharges) {
        double total = consultationFee + extraCharges;
        return total * 0.8;
    }
}
