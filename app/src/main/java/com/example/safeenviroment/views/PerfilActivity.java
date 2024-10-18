package com.example.safeenviroment.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.DispositivoController;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.models.Dispositivo;
import com.example.safeenviroment.models.Elderly;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView rutTextView;
    private TextView ageTextView;
    private TextView emergencyNumberTextView;
    private TextView medicalInfoTextView;
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

        String elderlyRut = getIntent().getStringExtra("elderly_rut");
        Elderly elderly = ElderlyController.findElderly(elderlyRut);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (elderly != null) {
            nameTextView.setText("NOMBRE: " + elderly.getName());
            rutTextView.setText("RUT: " + elderly.getRut());
            ageTextView.setText("EDAD: " + elderly.getAge());
            emergencyNumberTextView.setText("NÚMERO DE EMERGENCIA: " + elderly.getEmergencyNumber());
            medicalInfoTextView.setText("INFORMACIÓN MÉDICA: " + elderly.getMedicalInfo());

            gasDetected();

            dispositivoList = elderly.getDispositivo();
            if (dispositivoList == null) {
                dispositivoList = new ArrayList<>();
            }
            DispositivoController.addDispositivo(1, "movil", 0, 0, 0, 0, 0);
            DispositivoController.addDispositivo(2, "estatico", 0, 0, 0, 0, 0);
            ElderlyController.addDispositivo(elderlyRut, DispositivoController.findDispositivo(1));
            ElderlyController.addDispositivo(elderlyRut, DispositivoController.findDispositivo(2));

            for (Dispositivo d : dispositivoList) {
               if (d.getTipo().equals("movil")) {
                   Button downBPMButton = findViewById(R.id.DownBPM);
                   Button normalBPMButton = findViewById(R.id.NormalBPM);
                   Button upBPMButton = findViewById(R.id.upBPM);

                   downBPMButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           pulsoCardiaco("baja");
                       }
                   });

                   normalBPMButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           pulsoCardiaco("normal");
                       }
                   });

                   upBPMButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           pulsoCardiaco("alta");
                       }
                   });

               } else if (d.getTipo().equals("estatico")) {
                   // Temperatura
                   Button DownTempButton = findViewById(R.id.BTNDownTemp);
                   Button NormalTempButton = findViewById(R.id.BTNNormalTemp);
                   Button UpTempButton = findViewById(R.id.BTNupTemp);

                   DownTempButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           TemperaturaAmbiente("Baja");
                       }
                   });
                   NormalTempButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           TemperaturaAmbiente("Normal");
                       }
                   });
                   UpTempButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           TemperaturaAmbiente("Alta");
                       }
                   });
                   // Humedad
                   Button DownHumButton = findViewById(R.id.BTNDownH);
                   Button NormalHumButton = findViewById(R.id.BTNNormalH);
                   Button UpHumButton = findViewById(R.id.BTNupH);

                   DownHumButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           HumedadAmbiente("Baja");
                       }
                   });
                   NormalHumButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           HumedadAmbiente("Normal");
                       }
                   });
                   UpHumButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           HumedadAmbiente("Alta");
                       }
                   });
               }
            }
        }
    }

    public void secondActivity(View v) {
        Intent i = new Intent(this, secondActivity.class);
        i.putExtra("elderly_rut", getIntent().getStringExtra("elderly_rut"));
        startActivity(i);
    }

    public void gasDetected() {
        Button gasButton = findViewById(R.id.BTNgasDetected);
        Boolean gas;
        gas = Math.random() < 0.5;
        if (gas) {
            gasButton.setText("GAS DETECTADO");
            gasButton.setTextColor(getResources().getColor(R.color.md_theme_error));
            alertCuidador("Se ha detectado gas");
        }else {
            gasButton.setText("NO HAY GAS");
            gasButton.setTextColor(getResources().getColor(R.color.green));
        }
    }

    public void pulsoCardiaco(String tipo) {
        Button type = findViewById(R.id.BTNbpmType);
        int pulso;
        if (tipo.equals("normal")) {
            pulso = (int) (Math.random() * (90 - 60 + 1) + 60);
            type.setText(" NORMAL ");
            type.setTextColor(getResources().getColor(R.color.green));
        } else if (tipo.equals("baja")) {
            pulso = (int) (Math.random() * (60 - 30 + 1) + 30);
            type.setText("BAJO");
            type.setTextColor(getResources().getColor(R.color.blue));
            alertCuidador("El adulto mayor tiene un pulso cardiaco bajo: " + pulso);
        } else if (tipo.equals("alta")) {
            pulso = (int) (Math.random() * (110 - 90 + 1) + 90);
            type.setText("ALTO");
            type.setTextColor(getResources().getColor(R.color.md_theme_error));
            alertCuidador("El adulto mayor tiene un pulso cardiaco Alto: " + pulso);
        } else {
            throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }

        TextView bpmTextView = findViewById(R.id.BPM);
        bpmTextView.setText(String.valueOf(pulso));
    }
    private void alertCuidador(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public void TemperaturaAmbiente(String tipo) {
        Button type = findViewById(R.id.BTNtempType);
        float temperatura = 0;
        if (tipo == "Baja") {
            temperatura = (float) (Math.random() * (15 - 10 + 1) + 10);
            type.setText("BAJA");
            type.setTextColor(getResources().getColor(R.color.blue));
            alertCuidador("La temperatura ambiente es baja: " + temperatura+"°C");
        } else if (tipo == "Normal") {
            temperatura = (float) (Math.random() * (25 - 15 + 1) + 15);
            type.setText("NORMAL");
            type.setTextColor(getResources().getColor(R.color.green));
        } else if (tipo == "Alta") {
            temperatura = (float) (Math.random() * (35 - 25 + 1) + 25);
            type.setText("ALTA");
            type.setTextColor(getResources().getColor(R.color.md_theme_error));
            alertCuidador("La temperatura ambiente es alta: " + temperatura+"°C");
        }

        TextView tempTextView = findViewById(R.id.tvTemp);
        tempTextView.setText(temperatura+"°C");
    }
    public void HumedadAmbiente(String tipo) {
        Button type = findViewById(R.id.BTNhumType);
        float humedad = 0;
        if (tipo == "Baja") {
            humedad = (float) (Math.random() * (30 - 20 + 1) + 20);
            type.setText("BAJA");
            type.setTextColor(getResources().getColor(R.color.blue));
            alertCuidador("La humedad ambiente es baja: " + humedad+"%");
        } else if (tipo == "Normal") {
            humedad = (float) (Math.random() * (60 - 30 + 1) + 30);
            type.setText("NORMAL");
            type.setTextColor(getResources().getColor(R.color.green));
        } else if (tipo == "Alta") {
            humedad = (float) (Math.random() * (100 - 60 + 1) + 60);
            type.setText("ALTA");
            type.setTextColor(getResources().getColor(R.color.md_theme_error));
            alertCuidador("La humedad ambiente es alta: " + humedad+"%");
        }

        TextView humTextView = findViewById(R.id.TVHum);
        humTextView.setText(humedad+"%");
    }
}

