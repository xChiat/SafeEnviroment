package com.example.safeenviroment.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ElderlyController.findAll();
        mainLayout = findViewById(R.id.mainLL);
        listView = findViewById(R.id.listView);

        AdapterElderly adapter = new AdapterElderly(this);
        listView.setAdapter(adapter);
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

class AdapterElderly extends ArrayAdapter<Elderly> {

    AppCompatActivity appCompatActivity;

    public AdapterElderly(@NonNull AppCompatActivity context) {
        super(context, R.layout.elderly_perfil_link, ElderlyController.findAll());

        appCompatActivity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = appCompatActivity.getLayoutInflater();
        View item = inflater.inflate(R.layout.elderly_perfil_link, null);

        TextView name = item.findViewById(R.id.tvNombre);
        TextView rut = item.findViewById(R.id.tvRut);

        name.setText(name.getText() + " " + ElderlyController.findAll().get(position).getName());
        rut.setText(rut.getText() + " " + ElderlyController.findAll().get(position).getRut());

        return item;
    }
}