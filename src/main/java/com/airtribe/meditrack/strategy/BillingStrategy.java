package com.airtribe.meditrack.strategy;

public interface BillingStrategy {
    double calculateBill(double consultationFee, double extraCharges);
}
