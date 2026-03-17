package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

import java.time.LocalDate;

public class Appointment extends MedicalEntity {
    private Doctor doctor;
    private Patient patient;
    private LocalDate appointmentDate;
    private AppointmentStatus status;

    public Appointment(String id, Doctor doctor, Patient patient, LocalDate appointmentDate) {
        super(id);
        this.doctor=doctor;
        this.patient=patient;
        this.appointmentDate= appointmentDate;
        this.status = AppointmentStatus.PENDING;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void confirmAppointment() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void cancelAppointment() {
        this.status = AppointmentStatus.CANCELLED;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public void displayInfo() {
        System.out.println("Appointment ID: " + id);
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Patient: " + patient.getName());
        System.out.println("Date: " + appointmentDate);
        System.out.println("Status: " + status);
    }
}
