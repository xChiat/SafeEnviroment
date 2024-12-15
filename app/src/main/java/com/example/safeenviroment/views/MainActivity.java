package com.example.safeenviroment.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.models.Elderly;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private ListView listView;
    private ArrayList<Elderly> elderlyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.mainLL);
        listView = findViewById(R.id.listView);

        FirebaseApp.initializeApp(this);

        elderlyList = ElderlyController.findAll();
        AdapterElderly adapter = new AdapterElderly(this, elderlyList);
        ElderlyController.setAdapter(adapter);
        listView.setAdapter(adapter);

        if (elderlyList.isEmpty()) {
            ElderlyController.addElderly("12345678-9", "Juan Perez", 80, "123456789", "Diabetes", "Ninguna");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Elderly selectedElderly = elderlyList.get(position);
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                intent.putExtra("elderly_rut", selectedElderly.getRut());
                startActivity(intent);
            }
        });
    }


    public void addElderly(View v) {
        Intent i = new Intent(this, addElderly.class);
        startActivity(i);
    }
}

