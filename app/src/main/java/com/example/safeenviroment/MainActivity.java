package com.example.safeenviroment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Elderly elderly = new Elderly(
                "Juan Pérez",
                "75",
                "123-456-7890",
                "Diabetes, Hipertensión",
                "Alergia a la penicilina"
        );
        SensorData sensorData = new SensorData();

        TextView nameTextView = findViewById(R.id.nameTV);
        TextView ageTextView = findViewById(R.id.ageTV);
        TextView emergencyNumberTextView = findViewById(R.id.emergencyNumberTV);
        TextView medicalInfoTextView = findViewById(R.id.medicalInfoTV);
        TextView additionalInfoTextView = findViewById(R.id.additionalInfoTV);

        TextView gasStatusTextView = findViewById(R.id.gasStatusTextView);
        TextView temperatureTextView = findViewById(R.id.temperatureTextView);
        TextView humidityTextView = findViewById(R.id.humidityTextView);
        TextView heartRateTextView = findViewById(R.id.heartRateTextView);

        nameTextView.setText("Nombre: " + elderly.getName());
        ageTextView.setText("Edad: " + elderly.getAge());
        emergencyNumberTextView.setText("Número de emergencia: " + elderly.getEmergencyNumber());
        medicalInfoTextView.setText("Información Médica: " + elderly.getMedicalInfo());
        additionalInfoTextView.setText("Información Adicional: " + elderly.getAdditionalInfo());

        gasStatusTextView.setText(sensorData.getGasStatus());
        gasStatusTextView.setBackgroundColor(getResources().getColor(sensorData.getGasStatusColor()));
        gasStatusTextView.setTextColor(getResources().getColor(sensorData.getGasStatusTextColor()));
        temperatureTextView.setText("Temperatura: " + sensorData.getTemperature() + "°C");
        humidityTextView.setText("Humedad: " + sensorData.getHumidity() + "%");
        heartRateTextView.setText("Pulso Cardíaco: " + sensorData.getHeartRate() + " bpm");
    }
    public void secondAct(View v){
        Intent i = new Intent(this, secondActivity.class);
        startActivity(i);
    }
}