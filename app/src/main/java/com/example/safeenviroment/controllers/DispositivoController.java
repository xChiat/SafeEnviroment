package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Dispositivo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                    Map<String, Object> dispositivoMap = (Map<String, Object>) snapshot.getValue();
                    Dispositivo dispositivo = convertMapToDispositivo(dispositivoMap);
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

    public static String addDispositivo(int id, String tipo, ArrayList<Float> temperatura, ArrayList<Float> humedad, int bpm, ArrayList<Float> gas) {
        try {
            Dispositivo d = new Dispositivo(id, tipo, temperatura, humedad, bpm, gas);
            dispositivoList.add(d);
            databaseReference.child(String.valueOf(id)).setValue(d);
            return "Dispositivo creado";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

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

    public static String editDispositivo(int id, ArrayList<Float> temperatura, ArrayList<Float> humedad, int bpm, ArrayList<Float> gas) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            d.setTemperatura(temperatura);
            d.setHumedad(humedad);
            d.setBpm(bpm);
            d.setGas(gas);
            databaseReference.child(String.valueOf(id)).setValue(d);
            return "Dispositivo actualizado";
        }
        return "No existe el id";
    }

    public static boolean deleteDispositivo(int id) {
        Dispositivo d = findDispositivo(id);
        if (d != null) {
            dispositivoList.remove(d);
            databaseReference.child(String.valueOf(id)).removeValue();
            return true;
        }
        return false;
    }

    public static float getLatestValueFromList(ArrayList<Float> values) {
        if (values != null && !values.isEmpty()) {
            return values.get(values.size() - 1);
        }
        return 0;
    }

    public static Dispositivo convertMapToDispositivo(Map<String, Object> map) {
        int id = map.get("id") != null ? ((Long) map.get("id")).intValue() : 0;
        String tipo = map.get("tipo") != null ? (String) map.get("tipo") : "";
        ArrayList<Float> temperatura = map.get("Temperatura") != null ? convertMapToList((Map<String, Object>) map.get("Temperatura")) : new ArrayList<>();
        ArrayList<Float> humedad = map.get("Humedad") != null ? convertMapToList((Map<String, Object>) map.get("Humedad")) : new ArrayList<>();
        int bpm = map.get("bpm") != null ? ((Long) map.get("bpm")).intValue() : 0;
        ArrayList<Float> gas = map.get("Gas") != null ? convertMapToList((Map<String, Object>) map.get("Gas")) : new ArrayList<>();
        return new Dispositivo(id, tipo, temperatura, humedad, bpm, gas);
    }

    private static ArrayList<Float> convertMapToList(Map<String, Object> map) {
        ArrayList<Float> list = new ArrayList<>();
        for (Object value : map.values()) {
            list.add(Float.parseFloat(value.toString()));
        }
        return list;
    }
}

/**
 * Estructura dispositivo firebase
 *
 * datos enviados por un sensor mq5 y dht11 hacia firebase
 *
 * Dispositivo
 * |_estatico
 * ...|_Gas
 * ...|...|_-OEK2PbhTsE0Ww8bsA10:"110"
 * ...|...|_-OEK2RWEBASwp53xR2ys:"108"
 * ...|...|_-OEK2TPM0YQW4JStV_Pi:"105"
 * ...|...|_-OEK2VIC7C6t018_5LMi:"101"
 * ...|...|_-OEK2XBFtpIc--Xjkm8Y:"101"
 * ...|...|_-OEK2Z4Yx53UPKfsu31d:"99"
 * ...|...|_-OEK2_zOYmVLGCnxdCCh:"99"
 * ...|...|_-OEK2btMkJZh1ZzBlk5g:"99"
 * ...|_Humedad
 * ...|...|_-OEK2btMkJZh1ZzBlk5g :"14.5"
 * ...|_Temperatura
 * ...|...|_-OEK2btMkJZh1ZzBlk5g:"22"
 * ...|_id:1
 * ...|_tipo:"estatico"
 */