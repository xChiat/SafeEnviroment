package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Dispositivo;

import java.util.ArrayList;

public class DispositivoController {
    private static final ArrayList<Dispositivo> dispositivoList = new ArrayList<>();

    // CREATE
    public static String addDispositivo(int id, String tipo, float temperatura, float humedad, int bpm, float gasValue, int movimiento) {
        try {
            Dispositivo d = new Dispositivo();
            d.setId(id);
            d.setTipo(tipo);
            d.setTemperatura(temperatura);
            d.setHumedad(humedad);
            d.setBpm(bpm);
            d.setGasValue(gasValue);
            d.setMovimiento(movimiento);
            dispositivoList.add(d);
            return "Dispositivo creado";
        } catch (Exception e) {
            return "Error:"+e.getMessage();
        }
    }

    // READ
    public static ArrayList<Dispositivo> findAll() {
        return dispositivoList;
    }
    public static Dispositivo findDispositivo(int id) {
        for (Dispositivo d : dispositivoList) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    // UPDATE
    public static String editDispositivo(int id, float temperatura, float humedad, int bpm, float gasValue, int movimiento) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            d.setTemperatura(temperatura);
            d.setHumedad(humedad);
            d.setBpm(bpm);
            d.setGasValue(gasValue);
            d.setMovimiento(movimiento);
            return "Dispositivo actualizado";
        }
        return "No existe el id";
    }

    // DELETE
    public static boolean deleteDispositivo(int id) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            dispositivoList.remove(d);
            return true;
        }
        return false;
    }
}
