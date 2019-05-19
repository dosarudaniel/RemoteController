package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static SensorManager mySensorManager;
    private boolean sersorrunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if(mySensors.size() > 0){
            mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            sersorrunning = true;
            Toast.makeText(this, "Start ORIENTATION Sensor", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "No ORIENTATION Sensor", Toast.LENGTH_LONG).show();
            sersorrunning = false;
            finish();
        }

    }

    private SensorEventListener mySensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            System.out.println("Azimuth: " + String.valueOf(event.values[0]));
            System.out.println("Pitch: " + String.valueOf(event.values[1]));
            System.out.println("Roll: " + String.valueOf(event.values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(sersorrunning){
            mySensorManager.unregisterListener(mySensorEventListener);
            Toast.makeText(MainActivity.this, "unregisterListener", Toast.LENGTH_SHORT).show();
        }
    }
}



