package com.airtribe.meditrack.entity;


public final class BillSummary {

    private final String billId;
    private final String patientName;
    private final String doctorName;
    private final double finalAmount;

    public BillSummary(String billId, String patientName,
                       String doctorName, double finalAmount) {

        this.billId = billId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.finalAmount = finalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", finalAmount=" + finalAmount +
                '}';
    }
}
