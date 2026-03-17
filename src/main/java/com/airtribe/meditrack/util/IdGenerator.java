package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final IdGenerator instance = new IdGenerator();

    private static final AtomicInteger doctorCounter = new AtomicInteger(0);
    private static final AtomicInteger patientCounter = new AtomicInteger(0);
    private static final AtomicInteger appointmentCounter = new AtomicInteger(0);
    private static final AtomicInteger billCounter = new AtomicInteger(0);

    private IdGenerator() {
        // private constructor to prevent instantiation
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public static String generateDoctorId() {
        return "DOC-" + doctorCounter.incrementAndGet();
    }

    public static String generatePatientId() {
        return "PAT-" + patientCounter.incrementAndGet();
    }

    public static String  generateAppointmentId() {
        return "APP-" + appointmentCounter.incrementAndGet();
    }

    public static String generateBillId() {
        return "BILL-" + billCounter.incrementAndGet();
    }
}