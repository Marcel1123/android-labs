package com.example.laboratoareandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    private SensorManager sensorManager;
    private Sensor accelerare;
    private Sensor luminaS;
    private Sensor distantaS;
    private Sensor pasitS;
    private Sensor pasiS;

    // UI COMPONENT
    TextView longitude;
    TextView latitude;

    TextView x;
    TextView y;
    TextView z;

    TextView lumina;

    TextView distanta;
    TextView pasi;
    TextView pasit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerare = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        luminaS = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        distantaS = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pasitS = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        pasiS = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        longitude = findViewById(R.id.textView6);
        latitude = findViewById(R.id.textView13);

        x = findViewById(R.id.textView5);
        y = findViewById(R.id.textView11);
        z = findViewById(R.id.textView12);

        lumina = findViewById(R.id.textView7);

        distanta = findViewById(R.id.textView15);

        pasi = findViewById(R.id.textView18);
        pasit = findViewById(R.id.textView19);

        // GPS COORD
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        if(!getAcces()){
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                onLocationChanged(location);
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                onLocationChanged(location);
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, accelerare, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, luminaS, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, distantaS, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pasiS, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pasitS, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x.setText(String.valueOf(sensorEvent.values[0]));
            y.setText(String.valueOf(sensorEvent.values[1]));
            z.setText(String.valueOf(sensorEvent.values[2]));
        } else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            lumina.setText(String.valueOf(sensorEvent.values[0]));
        } else if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            distanta.setText(String.valueOf(sensorEvent.values[0]));
        } else if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            pasi.setText(String.valueOf(sensorEvent.values[0]));
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            pasit.setText("true");
        } else {
            pasit.setText("false");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        longitude.setText(String.valueOf(location.getLongitude()));
        latitude.setText(String.valueOf(location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private boolean getAcces(){
        List<String> accessList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(SensorActivity.this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            accessList.add(Manifest.permission.INTERNET);
        }
        if (ContextCompat.checkSelfPermission(SensorActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            accessList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(SensorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            accessList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!accessList.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) SensorActivity.this, accessList.toArray(new String[accessList.size()]), 0);
            return false;
        }
        return true;
    }

    public void goHome(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
