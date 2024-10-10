package com.example.safeenviroment.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.models.Elderly;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLL);

        ArrayList<Elderly> elderlyList = ElderlyController.findAll();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Elderly elderly : elderlyList) {
            View elderlyProfileLink = inflater.inflate(R.layout.elderly_perfil_link, mainLayout, false);

            TextView tvNombre = elderlyProfileLink.findViewById(R.id.tvNombre);
            TextView tvRut = elderlyProfileLink.findViewById(R.id.tvRut);

            tvNombre.setText(tvNombre.getText().toString() + " " + elderly.getName());
            tvRut.setText(tvRut.getText().toString() + " " + elderly.getRut());

            mainLayout.addView(elderlyProfileLink);
        }
    }

    public void secondAct(View v) {
        Intent i = new Intent(this, secondActivity.class);
        startActivity(i);
    }

    public void addElderly(View v) {
        Intent i = new Intent(this, addElderly.class);
        startActivity(i);
    }
}