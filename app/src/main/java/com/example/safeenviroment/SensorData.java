package com.example.safeenviroment;

import java.util.Random;

public class SensorData {
    private Random random;
    private boolean gasDetected =false;
    public SensorData() {
        random = new Random();
    }

    public String getGasStatus() {
        return gasDetected ? "Emisiones anormales de gas detectadas" : "No se han detectado fugas de gas en el hogar";
    }

    public int getGasStatusColor() {
        return gasDetected ? R.color.danger : R.color.good;
    }
    public int getGasStatusTextColor() {
        return gasDetected ? R.color.warning : R.color.dark_blue;
    }

    public int getTemperature() {
        return random.nextInt(15) + 15; // Simula una temperatura entre 15 y 30 grados
    }

    public int getHumidity() {
        return random.nextInt(50) + 30; // Simula una humedad entre 30% y 80%
    }

    public int getHeartRate() {
        return random.nextInt(40) + 60; // Simula un pulso cardíaco entre 60 y 100 bpm
    }
}
