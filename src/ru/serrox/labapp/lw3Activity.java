package ru.serrox.labapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by serrox on 09.11.2014.
 */
public class lw3Activity extends Activity implements SensorEventListener {
    private SensorManager sensormgr;

    private float[] rotationMatrix = new float[16];
    private float[] accelData = new float[3];
    private float[] magnetData = new float[3];
    private float[] OrientationData = new float[3];
    private int _color;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw3_layout);

        sensormgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        /*rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];
*/
        Random rnd = new Random();
        _color = rnd.nextInt();
        findViewById(R.id.lw3_target_color_view).setBackgroundColor(_color);
        ((TextView)findViewById(R.id.lw3_target_color_value)).setText("R: " + Color.red(_color) +" G: " + Color.green(_color) + " B: " + Color.blue(_color));

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
        //float[] mD = {1,1,magnetData[2]};
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
        SensorManager.getOrientation (rotationMatrix, OrientationData);



        int xy2 = (int) (((Math.toDegrees(OrientationData[0]))+180.0)*255.0/360.0);
        int xz2 = (int) (((Math.toDegrees(OrientationData[1]))+180.0)*255.0/360.0);
        int zy2 = (int) (((Math.toDegrees(OrientationData[2]))+180.0)*255.0/360.0);
        int color = Color.rgb(xy2, xz2, zy2);
        findViewById(R.id.lw3_color_view).setBackgroundColor(color);

        ((TextView)findViewById(R.id.lw3_color_value)).setText("R: " + Color.red(color) + " G: " + Color.green(color) + " B: " + Color.blue(color));
        if((Math.abs(xy2 - Color.red(_color))<40) && (Math.abs(xz2 - Color.green(_color))<40) && (Math.abs(zy2 - Color.blue(_color))<40) &&
                ((Math.abs(xy2 - Color.red(_color)) + Math.abs(xz2 - Color.green(_color)) + Math.abs(zy2 - Color.blue(_color)))<80 )){
            Toast toast = Toast.makeText(getApplicationContext(),"Epic Win!", Toast.LENGTH_SHORT);
            toast.show();
            Random rnd = new Random();
            _color = rnd.nextInt();
            findViewById(R.id.lw3_target_color_view).setBackgroundColor(_color);
            ((TextView)findViewById(R.id.lw3_target_color_value)).setText("R: " + Color.red(_color) +" G: " + Color.green(_color) + " B: " + Color.blue(_color));
        }
        ((TextView)findViewById(R.id.textView)).setText(""+rotationMatrix[0] + " " + rotationMatrix[1] +" " + rotationMatrix[2] + "\n"
                                                        + rotationMatrix[4] + " " + rotationMatrix[5] + " " + rotationMatrix[6] + "\n"
                                                        + rotationMatrix[8] + " " + rotationMatrix[9] + " " + rotationMatrix[10]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Nothig to do here!
    }
}