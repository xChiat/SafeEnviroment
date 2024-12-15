package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Dispositivo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DispositivoController {
    private static final ArrayList<Dispositivo> dispositivoList = new ArrayList<>();
    private static DatabaseReference databaseReference;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Dispositivo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dispositivoList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Dispositivo dispositivo = snapshot.getValue(Dispositivo.class);
                    if (dispositivo != null) {
                        dispositivoList.add(dispositivo);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    // CREATE
    public static String addDispositivo(int id, String tipo, float temperatura, float humedad, int bpm, float gasValue) {
        try {
            Dispositivo d = new Dispositivo();
            d.setId(id);
            d.setTipo(tipo);
            d.setTemperatura(temperatura);
            d.setHumedad(humedad);
            d.setBpm(bpm);
            d.setGas(gasValue);
            dispositivoList.add(d);
            databaseReference.child(String.valueOf(id)).setValue(d);
            return "Dispositivo creado";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
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
    public static String editDispositivo(int id, float temperatura, float humedad, int bpm, float gasValue) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            d.setTemperatura(temperatura);
            d.setHumedad(humedad);
            d.setBpm(bpm);
            d.setGas(gasValue);
            databaseReference.child(String.valueOf(id)).setValue(d);
            return "Dispositivo actualizado";
        }
        return "No existe el id";
    }

    // DELETE
    public static boolean deleteDispositivo(int id) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            dispositivoList.remove(d);
            databaseReference.child(String.valueOf(id)).removeValue();
            return true;
        }
        return false;
    }
}