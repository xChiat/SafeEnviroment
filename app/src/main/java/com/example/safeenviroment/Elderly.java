package com.example.safeenviroment;

public class Elderly {
    private String name;
    private String age;
    private String emergencyNumber;
    private String medicalInfo;
    private String additionalInfo;

    public Elderly(String name, String age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        this.name = name;
        this.age = age;
        this.emergencyNumber = emergencyNumber;
        this.medicalInfo = medicalInfo;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
