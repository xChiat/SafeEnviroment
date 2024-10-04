package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Family;

import java.util.ArrayList;

public class FamilyController {
    private static final ArrayList<Family> familyList = new ArrayList<>();

    // CREATE
    public static String addFamily(String rut, String name, String fono, String relationship) {
        try {
            Family f = new Family();
            f.setRut(rut);
            f.setName(name);
            f.setFono(fono);
            f.setRelationship(relationship);
            familyList.add(f);
            return "Familiar agregado correctamente";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // READ
    public static ArrayList<Family> findAll() {
        return familyList;
    }
    public static Family findFamily(String rut) {
        for (Family f : familyList) {
            if (f.getRut().equalsIgnoreCase(rut)) {
                return f;
            }
        }
        return null;
    }

    // UPDATE
    public static String editFamily(String rut, String name, String fono, String relationship) {
        Family f = findFamily(rut);
        if (f != null) {
            f.setName(name);
            f.setFono(fono);
            f.setRelationship(relationship);
            return "Familiar actualizado";
        }
        return "No existe el rut";
    }

    // DELETE
    public static boolean deleteFamily(String rut) {
        Family f = findFamily(rut);
        if (f != null) {
            familyList.remove(f);
            return true;
        }
        return false;
    }
}
