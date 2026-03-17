package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;

public class Bill extends MedicalEntity implements Payable {

    private Appointment appointment;
    private double amount;
    private double tax;
    private double finalAmount;
//    private static final double TAX_RATE = 0.05;

    public Bill(String id,Appointment appointment,double  amount) {
        super(id);
        this.amount=amount;
        this.appointment=appointment;
        calculateFinalAmount();
    }



    private void calculateFinalAmount(){
        this.tax=amount* Constants.TAX_RATE;
        this.finalAmount=amount+tax;
    }

    @Override
    public double calculateAmount() {

        return finalAmount;
    }

//    public double getFinalAmount() {
//        return finalAmount;
//    }

    @Override
    public void displayInfo() {
        System.out.println("Bill ID: " + id);
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Doctor: " + appointment.getDoctor().getName());
        System.out.println("Base Amount: " + amount);
        System.out.println("Tax: " + tax);
        System.out.println("Final Amount: " + finalAmount);
    }


}
