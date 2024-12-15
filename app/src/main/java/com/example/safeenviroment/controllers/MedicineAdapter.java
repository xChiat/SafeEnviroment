package com.example.safeenviroment.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.safeenviroment.R;

import java.util.ArrayList;

public class MedicineAdapter extends ArrayAdapter<String> {

    public MedicineAdapter(Context context, ArrayList<String> medicines) {
        super(context, 0, medicines);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String medicine = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_checkable, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView textView = convertView.findViewById(R.id.TVHorario);

        textView.setText(medicine);

        return convertView;
    }
}