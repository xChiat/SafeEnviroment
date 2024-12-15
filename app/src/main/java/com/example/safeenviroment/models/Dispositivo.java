package com.example.safeenviroment.models;

public class Dispositivo {
    // Atributos
    private int id;
    private String tipo;
    private float Temperatura;
    private float Humedad;
    private int bpm;
    private float Gas;

    // Constructor
    public Dispositivo(int id, String tipo, float Temperatura, float Humedad, int bpm, float Gas) {
        this.id = id;
        this.tipo = tipo;
        this.Temperatura = Temperatura;
        this.Humedad = Humedad;
        this.bpm = bpm;
        this.Gas = Gas;
    }
    public Dispositivo() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.Temperatura = temperatura;
    }

    public float getHumedad() {
        return Humedad;
    }

    public void setHumedad(float humedad) {
        this.Humedad = humedad;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public float getGas() {
        return Gas;
    }

    public void setGas(float Gas) {
        this.Gas = Gas;
    }
}
