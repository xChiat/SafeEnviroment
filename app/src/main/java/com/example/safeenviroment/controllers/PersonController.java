package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Person;

import java.util.ArrayList;

public class PersonController {
    private static final ArrayList<Person> personList = new ArrayList<>();

    // READ
    public static ArrayList<Person> findAll() {
        return personList;
    }
    public static Person findByRut(String rut) {
        for (Person p : personList) {
            if (p.getRut().equalsIgnoreCase(rut)) {
                return p;
            }
        }
        return null;
    }
}
