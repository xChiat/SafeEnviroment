package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Dispositivo;
import com.example.safeenviroment.models.Elderly;
import com.example.safeenviroment.models.Family;

import java.util.ArrayList;

public class ElderlyController {
    private static final ArrayList<Elderly> elderlyList = new ArrayList<>();

    // CREATE
    public static String addElderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        try {
            Elderly e = new Elderly();
            e.setRut(rut);
            e.setName(name);
            e.setAge(age);
            e.setEmergencyNumber(emergencyNumber);
            e.setMedicalInfo(medicalInfo);
            e.setAdditionalInfo(additionalInfo);
            elderlyList.add(e);
            return "Adulto mayor agregado exitosamente";
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }
    public static void addFamily(String elderlyRut, Family family) {
        Elderly elderly = findElderly(elderlyRut);
        if (elderly != null) {
            if (elderly.getFamily() == null) {
                elderly.setFamily(new ArrayList<>()); // Initialize familyList if null
            }
            elderly.getFamily().add(family);
        }
    }

    public static void addDispositivo(String rut, Dispositivo dispositivo) {
        Elderly elderly = findElderly(rut);
        if (elderly != null) {
            if (elderly.getDispositivo() == null) {
                elderly.setDispositivo(new ArrayList<>());
            }
            elderly.getDispositivo().add(dispositivo);
        }
    }

    // READ
    public static ArrayList<Elderly> findAll() {
        return elderlyList;
    }
    public static Elderly findElderly(String rut) {
        for (Elderly e : elderlyList) {
            if (e.getRut().equalsIgnoreCase(rut)) {
                return e;
            }
        }
        return null;
    }

    // UPDATE
    public static String editElderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        Elderly e = findElderly(rut);
        if (e != null) {
            e.setName(name);
            e.setAge(age);
            e.setEmergencyNumber(emergencyNumber);
            e.setMedicalInfo(medicalInfo);
            e.setAdditionalInfo(additionalInfo);
            return "Adulto mayor actualizado";
        }
        return "No existe el rut";
    }

    // DELETE
    public static boolean deleteElderly(String rut) {
        Elderly e = findElderly(rut);
        if (e != null) {
            for (Family f : e.getFamily()) {
                FamilyController.deleteFamily(f.getRut());
            }
            elderlyList.remove(e);
            return true;
        }
        return false;
    }
}
