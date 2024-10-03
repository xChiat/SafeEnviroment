package com.example.safeenviroment.models;

public class Family extends Person {
    // Atributos
    private String fono;
    private String relationship;

    // Constructor
    public Family(String rut, String name, String fono, String relationship) {
        super(rut, name);
        this.fono = fono;
        this.relationship = relationship;
    }
    public Family() {}

    // Getters y Setters
    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
