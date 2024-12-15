package com.example.safeenviroment.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safeenviroment.R;
import com.example.safeenviroment.controllers.DispositivoController;
import com.example.safeenviroment.controllers.ElderlyController;
import com.example.safeenviroment.models.Dispositivo;
import com.example.safeenviroment.models.Elderly;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        Button btnDownBPM = findViewById(R.id.DownBPM);
        Button btnNormalBPM = findViewById(R.id.NormalBPM);
        Button btnUpBPM = findViewById(R.id.upBPM);

        btnDownBPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsoCardiaco("baja");
            }
        });

        btnNormalBPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsoCardiaco("normal");
            }
        });

        btnUpBPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsoCardiaco("alta");
            }
        });

        if (elderly != null) {
            nameTextView.setText("NOMBRE: " + elderly.getName());
            rutTextView.setText("RUT: " + elderly.getRut());
            ageTextView.setText("EDAD: " + elderly.getAge());
            emergencyNumberTextView.setText("NÚMERO DE EMERGENCIA: " + elderly.getEmergencyNumber());
            medicalInfoTextView.setText("INFORMACIÓN MÉDICA: " + elderly.getMedicalInfo());

            dispositivoList = DispositivoController.findAll();
            for (Dispositivo d : dispositivoList) {
                if (d != null && d.getTipo() != null) {
                    if (d.getTipo().equals("movil")) {
                        // Simulate mobile device data
                    } else if (d.getTipo().equals("estatico")) {
                        System.out.println("Monitorizando dispositivo estático");
                        monitorStaticDevice("estatico");
                    }
                }
            }
        }

        DatabaseReference deviceRef = FirebaseDatabase.getInstance().getReference("Dispositivo");
        deviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dispositivoList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Dispositivo dispositivo = snapshot.getValue(Dispositivo.class);
                    if (dispositivo != null) {
                        dispositivoList.add(dispositivo);
                        if (dispositivo.getTipo().equals("estatico")) {
                            updateStaticDeviceUI(dispositivo);
                            checkGasAlert(dispositivo.getGas());
                            checkTemperatureAlert(dispositivo.getTemperatura());
                            checkHumidityAlert(dispositivo.getHumedad());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void monitorStaticDevice(String name) {
        DatabaseReference deviceRef = FirebaseDatabase.getInstance().getReference("Dispositivo").child(name);
        deviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Dispositivo dispositivo = dataSnapshot.getValue(Dispositivo.class);
                if (dispositivo != null) {
                    updateStaticDeviceUI(dispositivo);
                    checkGasAlert(dispositivo.getGas());
                    checkTemperatureAlert(dispositivo.getTemperatura());
                    checkHumidityAlert(dispositivo.getHumedad());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void updateStaticDeviceUI(Dispositivo dispositivo) {
        TextView tempTextView = findViewById(R.id.tvTemp);
        TextView humTextView = findViewById(R.id.TVHum);
        tempTextView.setText(dispositivo.getTemperatura() + "°C");
        humTextView.setText(dispositivo.getHumedad() + "%");
    }

    private void checkGasAlert(float gasValue) {
        Button gasButton = findViewById(R.id.BTNgasDetected);
        if (gasValue < 200) {
            gasButton.setText("NO HAY GAS");
            gasButton.setTextColor(getResources().getColor(R.color.green));
        } else if (gasValue >= 200 && gasValue <= 1000) {
            gasButton.setText("ADVERTENCIA: GAS DETECTADO");
            gasButton.setTextColor(getResources().getColor(R.color.warning));
            gasAlert("Advertencia: Se ha detectado gas en niveles moderados (" + gasValue + " ppm). Verifique la ventilación y las posibles fuentes de gas.");
        } else if (gasValue > 1000) {
            gasButton.setText("PELIGRO: GAS DETECTADO");
            gasButton.setTextColor(getResources().getColor(R.color.md_theme_error));
            gasAlert("Alerta crítica: Se ha detectado gas en niveles peligrosos (" + gasValue + " ppm). Evacúe el área y tome medidas de seguridad inmediatas.");
        }
    }

    private void checkTemperatureAlert(float temperatura) {
        Button type = findViewById(R.id.BTNtempType);
        if (temperatura < 15) {
            type.setText("TEMPERATURA BAJA");
            type.setTextColor(getResources().getColor(R.color.blue));
            tempAlert("La temperatura ambiente es baja: " + temperatura + "°C");
        } else if (temperatura >= 15 && temperatura <= 25) {
            type.setText("TEMPERATURA NORMAL");
            type.setTextColor(getResources().getColor(R.color.green));
        } else if (temperatura > 25) {
            type.setText("TEMPERATURA ALTA");
            type.setTextColor(getResources().getColor(R.color.md_theme_error));
            tempAlert("La temperatura ambiente es alta: " + temperatura + "°C");
        }
    }

    private void checkHumidityAlert(float humedad) {
        Button type = findViewById(R.id.BTNhumType);
        if (humedad < 30) {
            type.setText("HUMEDAD BAJA");
            type.setTextColor(getResources().getColor(R.color.blue));
            humAlert("La humedad ambiente es baja: " + humedad + "%");
        } else if (humedad >= 30 && humedad <= 60) {
            type.setText("HUMEDAD NORMAL");
            type.setTextColor(getResources().getColor(R.color.green));
        } else if (humedad > 60) {
            type.setText("HUMEDAD ALTA");
            type.setTextColor(getResources().getColor(R.color.md_theme_error));
            humAlert("La humedad ambiente es alta: " + humedad + "%");
        }
    }

    private void gasAlert(String mensaje) {
        if (!isFinishing()) {
            new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage(mensaje)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }

    private void tempAlert(String mensaje) {
        if (!isFinishing()) {
            new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage(mensaje)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }

    private void humAlert(String mensaje) {
        if (!isFinishing()) {
            new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage(mensaje)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }

    public void secondActivity(View v) {
        Intent i = new Intent(this, secondActivity.class);
        i.putExtra("elderly_rut", getIntent().getStringExtra("elderly_rut"));
        startActivity(i);
    }

    public void pulsoCardiaco(String tipo) {
        Button type = findViewById(R.id.BTNbpmType);
        int pulso;
        String elderlyRut = getIntent().getStringExtra("elderly_rut");
        String deviceName = "movil"; // Assuming device ID is 1 for this example

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

        // Push simulated BPM data to Firebase
        DatabaseReference deviceRef = FirebaseDatabase.getInstance().getReference("Dispositivo").child(deviceName);
        deviceRef.child("bpm").setValue(pulso);

        TextView bpmTextView = findViewById(R.id.BPM);
        bpmTextView.setText(String.valueOf(pulso));
    }

    private void alertCuidador(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}