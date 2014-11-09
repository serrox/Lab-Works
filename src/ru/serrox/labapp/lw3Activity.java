package ru.serrox.labapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by serrox on 09.11.2014.
 */
public class lw3Activity extends Activity implements SensorEventListener {
    private SensorManager sensormgr;

    private float[] rotationMatrix;
    private float[] accelData;
    private float[] magnetData;
    private float[] OrientationData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw3_layout);

        sensormgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];

        Random rnd = new Random();
        int color = rnd.nextInt();
        ((FrameLayout)findViewById(R.id.lw3_target_color_view)).setBackgroundColor(color);
        ((TextView)findViewById(R.id.lw3_target_color_value)).setText("R: " + Color.red(color) +" G: " + Color.green(color) + " B: " + Color.blue(color));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormgr.registerListener(this, sensormgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
        sensormgr.registerListener(this, sensormgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormgr.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        final int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            accelData = event.values.clone();
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetData = event.values.clone();
        }
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
        SensorManager.getOrientation (rotationMatrix, OrientationData);

        int color = Color.rgb((int)((OrientationData[0]/360)*255),(int)((OrientationData[1]/360)*255),(int)((OrientationData[2]/360)*255));
        ((FrameLayout)findViewById(R.id.lw3_color_view)).setBackgroundColor(color);

        ((TextView)findViewById(R.id.lw3_color_value)).setText("R: " + (int)((OrientationData[0]/360)*255) +" G: " + (int)((OrientationData[1]/360)*255) + " B: " + (int)((OrientationData[2]/360)*255));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Nothig to do here!
    }
}