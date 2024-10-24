package com.example.safeenviroment.models;

import java.util.ArrayList;

public class Elderly extends Person {
    // Atributos
    private int age;
    private String emergencyNumber;
    private String medicalInfo;
    private String additionalInfo;
    private ArrayList<Family> family;
    private ArrayList<Dispositivo> dispositivo;
    private ArrayList medicina;

    // Constructor
    public Elderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo, ArrayList<Family> family, ArrayList<Dispositivo> dispositivo, ArrayList medicina) {
        super(rut, name);
        this.age = age;
        this.emergencyNumber = emergencyNumber;
        this.medicalInfo = medicalInfo;
        this.additionalInfo = additionalInfo;
        this.family = family;
        this.dispositivo = dispositivo;
        this.medicina = medicina;
    }

    public Elderly() {
        this.dispositivo = new ArrayList<>();
    }

    // Getters y Setters
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

    public ArrayList<Dispositivo> getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(ArrayList<Dispositivo> dispositivo) {
        this.dispositivo = dispositivo;
    }

    public ArrayList getMedicina() {
        return medicina;
    }

    public void setMedicina(ArrayList medicina) {
        this.medicina = medicina;
    }
}
