package com.airtribe.meditrack.strategy;

public class NormalBillingStrategy implements BillingStrategy {

    @Override
    public double calculateBill(double consultationFee, double extraCharges) {
        return consultationFee+extraCharges;
    }
}
