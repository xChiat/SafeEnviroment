package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Elderly;
import com.example.safeenviroment.models.Family;
import com.example.safeenviroment.views.AdapterElderly;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class ElderlyController {
    private static final ArrayList<Elderly> elderlyList = new ArrayList<>();
    private static DatabaseReference databaseReference;
    private static AdapterElderly adapter;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("elderly");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        elderlyList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Elderly elderly = snapshot.getValue(Elderly.class);
                            if (elderly != null) {
                                elderlyList.add(elderly);
                            }
                        }
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    public static void setAdapter(AdapterElderly adapter) {
        ElderlyController.adapter = adapter;
    }

    private static void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    // CREATE
    public static String addElderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        try {
            Elderly e = new Elderly();
            e.setRut(rut);
            e.setName(name);
            e.setAge(age);
            e.setEmergencyNumber(emergencyNumber);
            e.setMedicalInfo(medicalInfo);
            e.setAdditionalInfo(additionalInfo);
            elderlyList.add(e);
            databaseReference.child(rut).setValue(e);
            return "Adulto mayor agregado exitosamente";
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }

    public static void addFamily(String elderlyRut, Family family) {
        Elderly elderly = findElderly(elderlyRut);
        if (elderly != null) {
            if (elderly.getFamily() == null) {
                elderly.setFamily(new ArrayList<>());
            }
            elderly.getFamily().add(family);
            databaseReference.child(elderlyRut).child("family").child(family.getRut()).setValue(family);
        }
    }

    public static void addMedicine(String elderlyRut, String fecha, String descripcion) {
        Elderly elderly = findElderly(elderlyRut);
        if (elderly != null) {
            if (elderly.getMedicina() == null) {
                elderly.setMedicina(new ArrayList<>());
            }
            String txt = "Horario: " + fecha + " - " + descripcion;
            elderly.getMedicina().add(txt);
            databaseReference.child(elderlyRut).child("medicina").setValue(elderly.getMedicina());
        }
    }

    // READ
    public static ArrayList<Elderly> findAll() {
        return elderlyList;
    }

    public static Elderly findElderly(String rut) {
        for (Elderly e : elderlyList) {
            if (e.getRut().equalsIgnoreCase(rut)) {
                return e;
            }
        }
        return null;
    }

    // UPDATE
    public static String editElderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        Elderly e = findElderly(rut);
        if (e != null) {
            e.setName(name);
            e.setAge(age);
            e.setEmergencyNumber(emergencyNumber);
            e.setMedicalInfo(medicalInfo);
            e.setAdditionalInfo(additionalInfo);
            databaseReference.child(rut).setValue(e);
            return "Adulto mayor actualizado";
        }
        return "No existe el rut";
    }

    // DELETE
    public static boolean deleteElderly(String rut) {
        Elderly e = findElderly(rut);
        if (e != null) {
            elderlyList.remove(e);
            databaseReference.child(rut).removeValue();
            return true;
        }
        return false;
    }
}