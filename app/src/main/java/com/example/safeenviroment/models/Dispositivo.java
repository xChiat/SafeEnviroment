package com.example.safeenviroment.models;

import java.util.ArrayList;

public class Dispositivo {
    private int id;
    private String tipo;
    private ArrayList<Float> Temperatura;
    private ArrayList<Float> Humedad;
    private ArrayList<Float> Gas;
    private int bpm;

    public Dispositivo(int id, String tipo, ArrayList<Float> Temperatura, ArrayList<Float> Humedad, int bpm, ArrayList<Float> Gas) {
        this.id = id;
        this.tipo = tipo;
        this.Temperatura = Temperatura;
        this.Humedad = Humedad;
        this.bpm = bpm;
        this.Gas = Gas;
    }

    public Dispositivo() {}

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

    public ArrayList<Float> getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(ArrayList<Float> temperatura) {
        this.Temperatura = temperatura;
    }

    public ArrayList<Float> getHumedad() {
        return Humedad;
    }

    public void setHumedad(ArrayList<Float> humedad) {
        this.Humedad = humedad;
    }

    public ArrayList<Float> getGas() {
        return Gas;
    }

    public void setGas(ArrayList<Float> gas) {
        this.Gas = gas;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
}