package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.strategy.DiscountBillingStrategy;
import com.airtribe.meditrack.strategy.InsuranceBillingStrategy;
import com.airtribe.meditrack.strategy.NormalBillingStrategy;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    private static DoctorService doctorService = new DoctorService();
    private static PatientService patientService = new PatientService();
    private static AppointmentService appointmentService = new AppointmentService();
    private static BillingService billingService = new BillingService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n===== MediTrack Menu =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Doctors");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7.  Cancel Appointments");
            System.out.println("8. Confirm Appointments");
            System.out.println("9. Generate Bill");
            System.out.println("10. View Bills");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        addDoctor(scanner);
                        break;

                    case 2:
                        addPatient(scanner);
                        break;

                    case 3:
                        createAppointment(scanner);
                        break;

                    case 4:
                        doctorService.viewAllDoctors()
                                .forEach(Doctor::displayInfo);
                        break;

                    case 5:
                        patientService.viewAllPatients()
                                .forEach(Patient::displayInfo);
                        break;

                    case 6:
                        appointmentService.viewAllAppointments()
                                .values()
                                .forEach(Appointment::displayInfo);
                        break;

                    case 7:
                        cancelAppointment(scanner);
                        break;

                    case 8:
                        confirmAppointment(scanner);

                    case 9:
                        generateBill(scanner);
                        break;

                    case 10:
                        billingService.viewAllBills();
                        break;

                    case 0:
                        System.out.println("Exiting application...");
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (InvalidDataException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void addDoctor(Scanner scanner) throws InvalidDataException {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter specialization (GENERAL_PHYSICIAN, CARDIOLOGIST, DERMATOLOGIST, NEUROLOGIST, PEDIATRICIAN): ");
        Specialization specialization =
                Specialization.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter consultation fee: ");
        double consultationFee = scanner.nextDouble();

        System.out.print("Enter years of experience: ");
        double experience = scanner.nextDouble();
        scanner.nextLine();

        Doctor doctor = doctorService.addDoctor(name, phone, age,
                specialization, consultationFee, experience);

        System.out.println("Doctor added successfully!");
        System.out.println("Doctor ID: " + doctor.getId());
    }

    private static void addPatient(Scanner scanner) throws InvalidDataException {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter blood group: ");
        String bloodGroup = scanner.nextLine();

        System.out.print("Enter medical history: ");
        String history = scanner.nextLine();

        System.out.print("Enter allergies: ");
        String allergies = scanner.nextLine();

        Patient patient = patientService.addPatient(
                name, phone, age, bloodGroup, history, allergies);

        System.out.println("Patient added successfully!");
        System.out.println("Patient ID: " + patient.getId());
    }

    private static void createAppointment(Scanner scanner) {

        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        Doctor doctor = doctorService.searchById(doctorId);
        Patient patient = patientService.searchPatientById(patientId);

        if (doctor == null || patient == null) {
            System.out.println("Doctor or Patient not found.");
            return;
        }

        Appointment appointment =
                appointmentService.createAppointment(doctor, patient);

        System.out.println("Appointment created successfully!");
        System.out.println("Appointment ID: " + appointment.getId());
    }


    private static void confirmAppointment(Scanner scanner){
        System.out.print("Enter Appointment ID to confirm:");
        String appointmentId = scanner.nextLine();
        try{
            appointmentService.confirmAppointment(appointmentId);
            System.out.println("Appointment confirmed Successfully.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void cancelAppointment(Scanner scanner) {

        System.out.print("Enter Appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();

        try {
            appointmentService.cancelAppointment(appointmentId);
            System.out.println("Appointment cancelled successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateBill(Scanner scanner) {

        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();

        Appointment appointment =
                appointmentService.searchByIdAppointment(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.print("Enter extra charges: ");
        double extraCharges = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Select Billing Type:");
        System.out.println("1 Normal Billing");
        System.out.println("2 Insurance Billing");
        System.out.println("3 Discount Billing");
        System.out.print("Enter your Bill Type:");

        int type = scanner.nextInt();
        scanner.nextLine();

        BillingStrategy strategy;

        switch (type) {

            case 1:
                strategy = new NormalBillingStrategy();
                break;

            case 2:
                strategy = new InsuranceBillingStrategy();
                break;

            case 3:
                strategy = new DiscountBillingStrategy();
                break;

            default:
                strategy = new NormalBillingStrategy();
        }

        BillSummary summary =
                billingService.generateBill(appointment, extraCharges, strategy);

        System.out.println(summary);
    }


}