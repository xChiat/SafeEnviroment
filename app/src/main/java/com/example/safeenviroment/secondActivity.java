package com.example.safeenviroment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ListView medScheduleList = findViewById(R.id.med_schedule_list);
        ListView familyList = findViewById(R.id.family_list);

        Elderly elderly = new Elderly(
                "Juan Pérez",
                "75",
                "123-456-7890",
                "Diabetes, Hipertensión",
                "Alergia a la penicilina"
        );

        TextView nameTextView = findViewById(R.id.nameTV);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView emergencyNumberTextView = findViewById(R.id.emergencyNumberTV);
        TextView medicalInfoTextView = findViewById(R.id.medicalInfoTV);
        TextView additionalInfoTextView = findViewById(R.id.additionalInfoTV);

        nameTextView.setText("Nombre: " + elderly.getName());
        ageTextView.setText("Edad: " + elderly.getAge());
        emergencyNumberTextView.setText("Número de emergencia: " + elderly.getEmergencyNumber());
        medicalInfoTextView.setText("Información Médica: " + elderly.getMedicalInfo());
        additionalInfoTextView.setText("Información Adicional: " + elderly.getAdditionalInfo());


        String[] medSchedule = {
                "Pastilla en ayuno 7 AM",
                "Pastilla a las 1:30 PM",
                "Jarabe para la tos 9 PM",
                "Pastilla antes de dormir 10 PM"
        };

        String[] familyMembers = {
                "Nombre: María Pérez\nFono: 987-654-3210",
                "Nombre: Carlos Pérez\nFono: 654-321-0987",
                "Nombre: Ana Pérez\nFono: 321-098-7654"
        };

        CheckableAdapter medAdapter = new CheckableAdapter(this, Arrays.asList(medSchedule));
        ArrayAdapter<String> familyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, familyMembers);

        medScheduleList.setAdapter(medAdapter);
        familyList.setAdapter(familyAdapter);
    }
}