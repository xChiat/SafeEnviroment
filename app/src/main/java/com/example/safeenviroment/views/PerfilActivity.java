package com.example.safeenviroment.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.DispositivoController;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.models.Dispositivo;
import com.example.safeenviroment.models.Elderly;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView rutTextView;
    private TextView ageTextView;
    private TextView emergencyNumberTextView;
    private TextView medicalInfoTextView;
    private ListView dispositivoListView;
    private ArrayList<Dispositivo> dispositivoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nameTextView = findViewById(R.id.tvNombre);
        rutTextView = findViewById(R.id.tvRut);
        ageTextView = findViewById(R.id.tvAge);
        emergencyNumberTextView = findViewById(R.id.tvEmergencyNumber);
        medicalInfoTextView = findViewById(R.id.tvInfoMed);
        dispositivoListView = findViewById(R.id.disp);

        String elderlyRut = getIntent().getStringExtra("elderly_rut");
        Elderly elderly = ElderlyController.findElderly(elderlyRut);

        if (elderly != null) {
            nameTextView.setText("NOMBRE: " + elderly.getName());
            rutTextView.setText("RUT: " + elderly.getRut());
            ageTextView.setText("EDAD: " + elderly.getAge());
            emergencyNumberTextView.setText("NÚMERO DE EMERGENCIA: " + elderly.getEmergencyNumber());
            medicalInfoTextView.setText("INFORMACIÓN MÉDICA: " + elderly.getMedicalInfo());

            dispositivoList = elderly.getDispositivo();
            AdapterDispositivo adapter = new AdapterDispositivo(this, dispositivoList);
            dispositivoListView.setAdapter(adapter);
        }
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDeviceDialog();
            }
        });

    }

    public void pulsoCardiaco(String tipo) {
        int pulso;
        if (tipo.equals("normal")) {
            pulso = (int) (Math.random() * (90 - 60 + 1) + 60);
        } else if (tipo.equals("baja")) {
            pulso = (int) (Math.random() * (60 - 30 + 1) + 30);
        } else if (tipo.equals("alta")) {
            pulso = (int) (Math.random() * (110 - 90 + 1) + 90);
        } else {
            throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }

        TextView bpmTextView = findViewById(R.id.BPM);
        bpmTextView.setText(String.valueOf(pulso));
    }

    private void showAddDeviceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.agregar_dispositivo, null);
        builder.setView(dialogView)
                .setTitle("Agregar dispositivo");

        AutoCompleteTextView deviceDropdown = dialogView.findViewById(R.id.deviceDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.simple_items, android.R.layout.simple_dropdown_item_1line);
        deviceDropdown.setAdapter(adapter);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String selectedDeviceType = deviceDropdown.getText().toString();
            String deviceType = selectedDeviceType.equals("Dispositivo movil") ? "movil" : "estatico";

            int id = DispositivoController.findAll().size() + 1;
            DispositivoController.addDispositivo(id, deviceType, 0, 0, 0, 0, 0);
            Dispositivo dispositivo = DispositivoController.findDispositivo(id);
            String elderlyRut = getIntent().getStringExtra("elderly_rut");
            ElderlyController.addDispositivo(elderlyRut, dispositivo);
        });

        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

class AdapterDispositivo extends ArrayAdapter<Dispositivo> {

    private PerfilActivity perfilActivity;
    private ArrayList<Dispositivo> dispositivoList;

    public AdapterDispositivo(@NonNull PerfilActivity context, ArrayList<Dispositivo> dispositivoList) {
        super(context, 0, dispositivoList);
        this.perfilActivity = context;
        this.dispositivoList = dispositivoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Dispositivo dispositivo = dispositivoList.get(position);
        LayoutInflater inflater = perfilActivity.getLayoutInflater();
        View item;

        if (dispositivo.getTipo().equals("movil")) {
            item = inflater.inflate(R.layout.pulso_cardiaco, parent, false);

            Button downBPMButton = item.findViewById(R.id.DownBPM);
            Button normalBPMButton = item.findViewById(R.id.NormalBPM);
            Button upBPMButton = item.findViewById(R.id.upBPM);

            downBPMButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perfilActivity.pulsoCardiaco("baja");
                }
            });

            normalBPMButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perfilActivity.pulsoCardiaco("normal");
                }
            });

            upBPMButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perfilActivity.pulsoCardiaco("alta");
                }
            });

        } else {
            item = inflater.inflate(R.layout.detec_g_t_h, parent, false);
            // Configurar la vista para el dispositivo estático si es necesario
        }

        return item;
    }
}