package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentService {
    private Map<String, Appointment> appointments = new HashMap<>();

//    public Appointment createAppointment(Appointment appointment){
//        appointments.put(appointment.getId(),appointment);
//        System.out.println("Appointment Created Successfully");
//        return appointment;
//    }



    public Appointment createAppointment(Doctor doctor, Patient patient) {

//        if (!validateInput(doctor, patient)) {
//            throw new IllegalArgumentException("Doctor and Patient cannot be null.");
//        }

        String id = IdGenerator.generateAppointmentId();

        Appointment appointment =
                new Appointment(id, doctor, patient, LocalDate.now());

        appointments.put(id, appointment);

        return appointment;
    }



    public Map<String,Appointment> viewAllAppointments(){
//
        return appointments;
    }

    public Appointment searchByIdAppointment(String id){
        return appointments.get(id);
    }

    public void cancelAppointment(String id) throws AppointmentNotFoundException {
        Appointment appointment =appointments.get(id);


        if(appointment == null){

            throw new AppointmentNotFoundException("Appointment Id:"+id+"Not found");
        }

        appointment.cancelAppointment();
    }

    public void confirmAppointment (String id){
        Appointment appointment = appointments.get(id);
        if(appointment==null){
            throw new AppointmentNotFoundException("Appointment Id:"+id+"Not found");
        }
       appointment.confirmAppointment();
    }

    public List<Appointment> searchByDoctor(Doctor doctor) {
        return appointments.values().stream()
                .filter(appt -> appt.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }

    public List<Appointment> searchByDate(LocalDate date) {
        return appointments.values().stream()
                .filter(appt -> appt.getAppointmentDate().equals(date))
                .collect(Collectors.toList());
    }




    private boolean validateInput(Doctor doctor, Patient patient) {
        return doctor != null && patient != null;
    }


}
