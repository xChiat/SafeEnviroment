package com.example.safeenviroment.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.models.Elderly;

import java.util.ArrayList;

public class AdapterElderly extends ArrayAdapter<Elderly> {

    private AppCompatActivity appCompatActivity;
    private ArrayList<Elderly> elderlyList;

    public AdapterElderly(@NonNull AppCompatActivity context, ArrayList<Elderly> elderlyList) {
        super(context, R.layout.elderly_perfil_link, elderlyList);
        this.appCompatActivity = context;
        this.elderlyList = elderlyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = appCompatActivity.getLayoutInflater();
        View item = inflater.inflate(R.layout.elderly_perfil_link, null);

        TextView name = item.findViewById(R.id.tvNombre);
        TextView rut = item.findViewById(R.id.tvRut);

        Elderly elderly = elderlyList.get(position);
        name.setText("NOMBRE: " + elderly.getName());
        rut.setText("RUT: " + elderly.getRut());

        return item;
    }
}