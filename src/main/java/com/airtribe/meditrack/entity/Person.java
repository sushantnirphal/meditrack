package com.airtribe.meditrack.entity;

public class Person extends MedicalEntity {

    private String name;
    private String phone;
    private int age ;



    public Person(String id,String name, String phone, int age) {
        super(id);
        this.name=name;
        this.phone=phone;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void displayInfo() {

    }
}
