package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import java.util.List;

import java.util.stream.Collectors;

public class DoctorService implements Searchable {

    private DataStore<Doctor> doctorStore = new DataStore<>();

    public Doctor addDoctor(String name, String phone,int age,
                            Specialization specialization, double consultationFee, double yearOfExperiance) throws InvalidDataException {
        Validator.validateName(name);
        Validator.validatePhone(phone);
        Validator.validateAge(age);
        Validator.validateFee(consultationFee);
        Validator.validateYearsOfExperience(yearOfExperiance);
        String id = IdGenerator.generateDoctorId();
        Doctor doctor = new Doctor( id, name, phone, age, specialization, consultationFee,yearOfExperiance);

        doctorStore.add(doctor);
        return doctor;
    }




    public boolean removeDoctor(String Id) {

        boolean isRemoved = doctorStore.getAll().removeIf(doctor -> doctor.getId().equals(Id));

        if (isRemoved) {
            System.out.println("Doctor with ID " + Id + " has been removed.");
        } else {
            System.out.println("Doctor with ID " + Id + " not found.");
        }

        return isRemoved;
    }




    public List<Doctor> viewAllDoctors(){
       return doctorStore.getAll();
    }


//    public Doctor searchById(String id){
//
//        return doctorStore.stream()
//                .filter(doctor -> doctor.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//
//    }

    public Doctor searchById(String id) {
        return doctorStore.getAll().stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }




    public boolean updateDoctor(String id, String newName, String newPhone, int newAge,
                                Specialization newSpecialization, double newConsultationFee, double newYearOfExperience) {

        Doctor doctor = searchById(id);
        if (doctor != null) {
            // Assume setters or update methods on Doctor, or replace the object

            Validator.validateName(newName);
            Validator.validatePhone(newPhone);
            Validator.validateAge(newAge);
            Validator.validateFee(newConsultationFee);
            Validator.validateYearsOfExperience(newYearOfExperience);



            doctor.setName(newName);
            doctor.setPhone(newPhone);
            doctor.setAge(newAge);
            doctor.setSpecialization(newSpecialization);
            doctor.setConsultationFee(newConsultationFee);
            doctor.setYearOfExperiance(newYearOfExperience);
            return true;
        }
        return false;
    }


    public List<Doctor> searchBySpecialization(Specialization specialization){
        List<Doctor> result = doctorStore.stream()
                .filter(doc -> doc.getSpecialization().equals(specialization))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Doctor not found...");
        }

        return result;

    }
}
