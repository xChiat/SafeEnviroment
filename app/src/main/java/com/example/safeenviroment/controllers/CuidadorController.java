package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Cuidador;

import java.util.ArrayList;

public class CuidadorController {
    private static final ArrayList<Cuidador> cuidadorList = new ArrayList<>();

    // CREATE
    public static String addCuidador(String rut, String name, String mail, String password) {
        try {
            Cuidador c = new Cuidador();
            c.setRut(rut);
            c.setName(name);
            c.setMail(mail);
            c.setPassword(password);
            cuidadorList.add(c);
            return "Cuidador creado";
        } catch (Exception e) {
            return "Error:"+e.getMessage();
        }
    }

    // READ
    public static ArrayList<Cuidador> findAll() {
        return cuidadorList;
    }
    public static Cuidador findCuidador(String rut) {
        for (Cuidador c : cuidadorList) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

    // UPDATE
    public static String editCuidador(String rut, String name, String mail, String password) {
        Cuidador c = findCuidador(rut);
        if (c != null) {
            c.setName(name);
            c.setMail(mail);
            c.setPassword(password);
            return "Cuidador actualizado";
        }
        return "No existe el rut";
    }

    // DELETE
    public static boolean deleteCuidador(String rut) {
        Cuidador c = findCuidador(rut);
        if (c != null) {
            cuidadorList.remove(c);
            return true;
        }
        return false;
    }
}
