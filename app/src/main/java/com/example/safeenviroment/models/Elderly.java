package com.example.safeenviroment.models;

import java.util.ArrayList;

public class Elderly extends Person {
    // Attributes
    private int age;
    private String emergencyNumber;
    private String medicalInfo;
    private String additionalInfo;
    private ArrayList<Family> family;
    private ArrayList<String> medicina;

    // Constructor
    public Elderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo, ArrayList<Family> family, ArrayList<String> medicina) {
        super(rut, name);
        this.age = age;
        this.emergencyNumber = emergencyNumber;
        this.medicalInfo = medicalInfo;
        this.additionalInfo = additionalInfo;
        this.family = family;
        this.medicina = medicina;
    }

    public Elderly() {}

    // Getters and Setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public ArrayList<Family> getFamily() {
        return family;
    }

    public void setFamily(ArrayList<Family> family) {
        this.family = family;
    }

    public ArrayList<String> getMedicina() {
        return medicina;
    }

    public void setMedicina(ArrayList<String> medicina) {
        this.medicina = medicina;
    }
}