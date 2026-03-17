package com.airtribe.meditrack.util;


import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {

    private Validator() {
        // Prevent object creation
    }

    public static void validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty.");
        }
    }

    public static void validateAge(int age) throws InvalidDataException {
        if (age < 0) {
            throw new InvalidDataException("Age must be greater than 0.");
        }
    }

    public static void validatePhone(String phone) throws InvalidDataException {
        if (phone == null || phone.length() != 10) {
            throw new InvalidDataException("Phone number must be 10 digits.");
        }
    }

    public static void validateFee(double fee) throws InvalidDataException {
        if (fee < 0) {
            throw new InvalidDataException("Consultation fee cannot be negative.");
        }
    }

    public static void validateYearsOfExperience(double yearOfExperiance) throws InvalidDataException{
        if(yearOfExperiance < 0){
            throw new InvalidDataException("Doctor Years of Experience Cant -ve.");
        }
    }

}
