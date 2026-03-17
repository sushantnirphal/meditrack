package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.HashMap;
import java.util.Map;

public class BillingService {

    private Map<String, Bill> billStore = new HashMap<>();

    public BillSummary generateBill(Appointment appointment,
                                    double extraCharges,
                                    BillingStrategy strategy) {

        double consultationFee =
                appointment.getDoctor().getConsultationFee();

        // Strategy Pattern used here
        double baseAmount =
                strategy.calculateBill(consultationFee, extraCharges);

        String billId = IdGenerator.generateBillId();

        Bill bill = new Bill(billId, appointment, baseAmount);

        billStore.put(billId, bill);

        return new BillSummary(
                billId,
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                bill.calculateAmount()
        );
    }

    public void viewAllBills() {
        billStore.values().forEach(Bill::displayInfo);
    }

    public Bill searchBillById(String id) {
        return billStore.get(id);
    }
}
