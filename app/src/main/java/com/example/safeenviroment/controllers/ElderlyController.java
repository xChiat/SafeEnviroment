package com.example.safeenviroment.controllers;

import com.example.safeenviroment.models.Elderly;
import com.example.safeenviroment.models.Family;
import com.example.safeenviroment.views.AdapterElderly;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

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
                elderlyList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Elderly elderly = snapshot.getValue(Elderly.class);
                    elderlyList.add(elderly);
                }
                if (adapter != null) {
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                }
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
        Elderly elderly = new Elderly(rut, name, age, emergencyNumber, medicalInfo, additionalInfo, new ArrayList<>(), new ArrayList<>());
        databaseReference.child(rut).setValue(elderly);
        return rut;
    }

    public static void addFamily(String elderlyRut, Family family) {
        DatabaseReference elderlyRef = databaseReference.child(elderlyRut).child("family");
        elderlyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Family>> t = new GenericTypeIndicator<ArrayList<Family>>() {};
                ArrayList<Family> familyList = dataSnapshot.getValue(t);
                if (familyList == null) {
                    familyList = new ArrayList<>();
                }
                familyList.add(family);
                elderlyRef.setValue(familyList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    public static void addMedicine(String elderlyRut, String fecha, String descripcion) {
        DatabaseReference medicineRef = databaseReference.child(elderlyRut).child("medicina");
        medicineRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                ArrayList<String> medicineList = dataSnapshot.getValue(t);
                if (medicineList == null) {
                    medicineList = new ArrayList<>();
                }
                medicineList.add("Horario: " + fecha + " - " + descripcion);
                medicineRef.setValue(medicineList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    // READ
    public static ArrayList<Elderly> findAll() {
        return elderlyList;
    }

    public static Elderly findElderly(String rut) {
        for (Elderly elderly : elderlyList) {
            if (elderly.getRut().equals(rut)) {
                return elderly;
            }
        }
        return null;
    }

    // UPDATE
    public static String editElderly(String rut, String name, int age, String emergencyNumber, String medicalInfo, String additionalInfo) {
        Elderly elderly = findElderly(rut);
        if (elderly != null) {
            elderly.setName(name);
            elderly.setAge(age);
            elderly.setEmergencyNumber(emergencyNumber);
            elderly.setMedicalInfo(medicalInfo);
            elderly.setAdditionalInfo(additionalInfo);
            databaseReference.child(rut).setValue(elderly);
            return rut;
        }
        return null;
    }

    // DELETE
    public static boolean deleteElderly(String rut) {
        Elderly elderly = findElderly(rut);
        if (elderly != null) {
            databaseReference.child(rut).removeValue();
            elderlyList.remove(elderly);
            return true;
        }
        return false;
    }
}

/** Estructura datos firebase
 * elderly
 * |_12345678-9
 * ...|_additionalInfo:"Ninguna"
 * ...|_age:80
 * ...|_emergencyNumber:"123456789"
 * ...|_family
 * ...|...|_2-2
 * ...|......|_fono:"856"
 * ...|......|_name:"pepe"
 * ...|......|_relationship:"hijo"
 * ...|......|_rut:"2-2"
 * ...|_medicalInfo:"Diabetes"
 * ...|_medicina
 * ...|...|_0:"Horario: 6 - jwjsjdk"
 * ...|_name:"Juan Perez"
 * ...|_rut:"12345678-9"
 */