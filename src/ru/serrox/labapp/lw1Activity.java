package ru.serrox.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridLayout;

/**
 * Created by serrox on 09.11.2014.
 */
public class lw1Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw1_layout);
        GridLayout Layout = (GridLayout) findViewById(R.id.grLay);
       // Layout.
    }
}