package com.example.safeenviroment.models;

public class Dispositivo {
    // Atributos
    private int id;
    private float temperatura;
    private float humedad;
    private int bpm;
    private float gasValue;
    private int movimiento;

    // Constructor
    public Dispositivo(int id, float temperatura, float humedad, int bpm, float gasValue, int movimiento) {
        this.id = id;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.bpm = bpm;
        this.gasValue = gasValue;
        this.movimiento = movimiento;
    }
    public Dispositivo() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public float getGasValue() {
        return gasValue;
    }

    public void setGasValue(float gasValue) {
        this.gasValue = gasValue;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }
}
