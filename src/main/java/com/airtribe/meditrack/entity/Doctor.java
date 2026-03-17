package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person {

    private Specialization specialization;
    private double consultationFee;
    private double yearOfExperiance;

    public Doctor(String id, String name, String phone, int age,
                  Specialization specialization, double consultationFee, double yearOfExperiance) {

        super(id, name, phone, age);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }




    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getYearOfExperiance() {
        return yearOfExperiance;
    }

    public void setYearOfExperiance(double yearOfExperiance) {
        this.yearOfExperiance = yearOfExperiance;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doctor's ID:"+getId());
        System.out.println("Doctor's Name:"+getName());
        System.out.println("Doctor's Phone:"+getPhone());
        System.out.println("Specialization: "+specialization);
        System.out.println("consultationFee: "+consultationFee);
        System.out.println("yearOfExperiance: "+yearOfExperiance);
    }


}
