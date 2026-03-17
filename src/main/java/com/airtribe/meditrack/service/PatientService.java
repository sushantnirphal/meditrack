package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService {

    private DataStore<Patient> patientStore = new DataStore<>();

        // Add patient

   public Patient addPatient( String name, String phone, int age, String bloodGroup, String medicalHistory,String allergies) throws InvalidDataException {
       Validator.validateName(name);
       Validator.validatePhone(phone);
       Validator.validateAge(age);
       String id = IdGenerator.generatePatientId();
       Patient patient = new Patient(id, name, phone, age,
               bloodGroup, medicalHistory, allergies);
       patientStore.add(patient);
//       System.out.println("Patient added Successfully.");
       return patient;
   }


    //   View All Patients
   public List<Patient> viewAllPatients(){
       return patientStore.getAll();
   }

    //    Search Patient By Id
    public Patient searchPatientById(String id) {
        return patientStore.find(p -> p.getId().equals(id)).orElse(null);
    }


    // Search Patinet By Name
   /* public Patient searchPatientByName(String name) {

        return patientStore.find(p->p.getName().equals(name)).orElse(null);

    }*/

    public List<Patient> searchPatientsByName(String name) {
        return patientStore.getAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    //Update the Patient Details
    public boolean updatePatient(String  id,String updatedName,String updatedPhone, int updatedAge, String updatedBloodGroup, String updatedMedicalHistory,String updatedAllergies ) throws InvalidDataException {

        Optional<Patient> optionalPatient = patientStore.find(p->p.getId().equals(id));

        if(optionalPatient.isEmpty()){
            System.out.println("Patient Not Found...");
            return false;
        }

       Patient patient = optionalPatient.get();

        Validator.validateName(updatedName);
        Validator.validatePhone(updatedPhone);
        Validator.validateAge(updatedAge);

        patient.setName(updatedName);
        patient.setPhone(updatedPhone);
        patient.setAge(updatedAge);

        patient.setBloodGroup(updatedBloodGroup);
        patient.setMedicalHistory(updatedMedicalHistory);
        patient.setAllergies(updatedAllergies);

        System.out.println("Patient details updated successfully.");

        return true;

    }

        //remove Patients
    public boolean removePatient(String id){

       boolean isRemoved = patientStore.getAll() .removeIf(p->p.getId().equals(id));

       if(isRemoved){
           System.out.println("Patient is removed ");
       }else{
           System.out.println("Patient not found...");
       }

    return isRemoved;
    }


}




