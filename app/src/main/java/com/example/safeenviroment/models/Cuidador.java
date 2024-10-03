package com.example.safeenviroment.models;

public class Cuidador extends Person {
    // Atributos
    private String mail;
    private String password;

    // Constructor
    public Cuidador(String rut, String name, String mail, String password) {
        super(rut, name);
        this.mail = mail;
        this.password = password;
    }
    public Cuidador() {}

    // Getters y Setters
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
