package com.example.safeenviroment.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.ElderlyController;

public class addElderly extends AppCompatActivity {
    EditText name, rut, age, emergencyNumber, infoMed, infoAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_elderly);
        name = findViewById(R.id.etNombre);
        rut = findViewById(R.id.etRut);
        age = findViewById(R.id.etAge);
        emergencyNumber = findViewById(R.id.etPhone);
        infoMed = findViewById(R.id.etInfomed);
        infoAdd = findViewById(R.id.etInfoad);

    }
    public void addElderly(View v) {
        if (areFieldsNotEmpty(name, rut, age, emergencyNumber, infoMed, infoAdd)) {
            ElderlyController.addElderly(
                    rut.getText().toString(),
                    name.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    emergencyNumber.getText().toString(),
                    infoMed.getText().toString(),
                    infoAdd.getText().toString()
            );
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            System.out.println("Error: Campos vacíos");
        }
    }
    private boolean areFieldsNotEmpty(EditText... fields) {
        for (EditText field : fields) {
            if (field.getText().toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}