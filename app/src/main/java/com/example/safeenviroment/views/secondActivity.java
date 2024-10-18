package com.example.safeenviroment.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.controllers.FamilyController;
import com.example.safeenviroment.models.Elderly;
import com.example.safeenviroment.models.Family;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    TextView tvInfoMed;
    TextView tvInfoAdd;
    EditText rutEditText;
    EditText relationEditText;
    EditText nameEditText;
    EditText numberEditText;
    ListView familyListView;
    FamilyAdapter familyAdapter;
    ArrayList<Family> familyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        tvInfoMed = findViewById(R.id.InfoMed); // Ensure this is a TextView
        tvInfoAdd = findViewById(R.id.InfoAd);  // Ensure this is a TextView
        rutEditText = findViewById(R.id.rutET); // Ensure this is an EditText
        relationEditText = findViewById(R.id.relationET); // Ensure this is an EditText
        nameEditText = findViewById(R.id.nameET); // Ensure this is an EditText
        numberEditText = findViewById(R.id.numberET); // Ensure this is an EditText
        familyListView = findViewById(R.id.lvFamily);

        String elderlyRut = getIntent().getStringExtra("elderly_rut");
        Elderly elderly = ElderlyController.findElderly(elderlyRut);
        tvInfoMed.setText("Info Medica: " + elderly.getMedicalInfo());
        tvInfoAdd.setText("Info Adicional: " + elderly.getAdditionalInfo());

        familyList = elderly.getFamily();
        if (familyList == null) {
            familyList = new ArrayList<>();
        }
        familyAdapter = new FamilyAdapter(this, familyList);
        familyListView.setAdapter(familyAdapter);
    }

    public void addFamily(View v) {
        if (areFieldsNotEmpty(rutEditText, nameEditText, numberEditText, relationEditText)) {
            String rut = rutEditText.getText().toString();
            String name = nameEditText.getText().toString();
            String number = numberEditText.getText().toString();
            String relation = relationEditText.getText().toString();

            FamilyController.addFamily(rut, name, number, relation);
            Family family = FamilyController.findFamily(rut);
            ElderlyController.addFamily(getIntent().getStringExtra("elderly_rut"), family);

            familyList.add(family);
            familyAdapter.notifyDataSetChanged();
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

class FamilyAdapter extends ArrayAdapter<Family> {

    public FamilyAdapter(Context context, ArrayList<Family> families) {
        super(context, 0, families);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Family family = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.family_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.familyName);
        TextView relationTextView = convertView.findViewById(R.id.familyRelation);
        TextView numberTextView = convertView.findViewById(R.id.familyNumber);

        nameTextView.setText(family.getName());
        relationTextView.setText(family.getRelationship());
        numberTextView.setText(family.getFono());

        return convertView;
    }
}