package com.airtribe.meditrack.strategy;

public class DiscountBillingStrategy implements  BillingStrategy{
    @Override
    public double calculateBill(double consultationFee, double extraCharges) {

            double total = consultationFee + extraCharges;
            return total - 100;


    }
}
