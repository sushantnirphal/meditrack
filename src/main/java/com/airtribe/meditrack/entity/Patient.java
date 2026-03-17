package com.airtribe.meditrack.entity;

public class Patient extends Person {

    private String bloodGroup;
    private String medicalHistory;
    private String allergies;

    public Patient(String id, String name,String phone, int age, String bloodGroup, String medicalHistory,String allergies) {
        super(id, name, phone, age);

        this.bloodGroup=bloodGroup;
        this.medicalHistory=medicalHistory;
        this.allergies=allergies;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Patient's ID:"+getId());
        System.out.println("Patient's Name:"+getName());
        System.out.println("Patient's Phone:"+getPhone());
        System.out.println("BloodGroup: "+bloodGroup);
        System.out.println("Medical History: "+medicalHistory);
        System.out.println("Allergies:"+allergies);
    }
}
