package com.example.safeenviroment.models;

public class Person {
    // Atributos
    private String rut;
    private String name;

    // Constructor
    public Person(String rut, String name) {
        this.rut = rut;
        this.name = name;
    }
    public Person() {}

    // Getters y Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
