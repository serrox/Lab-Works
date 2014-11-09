package ru.serrox.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import java.util.Random;

/**
 * Created by serrox on 09.11.2014.
 */
public class lw1Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw1_layout);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lw1m, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lw1_upd:
                // TODO: обработчик нажатия здесь
                Random rnd = new Random();
                ((FrameLayout)findViewById(R.id.lw1_bc)).setBackgroundColor(rnd.nextInt());
                ((FrameLayout)findViewById(R.id.lw1_bl)).setBackgroundColor(rnd.nextInt());
                ((FrameLayout)findViewById(R.id.lw1_cc)).setBackgroundColor(rnd.nextInt());
                ((FrameLayout)findViewById(R.id.lw1_cr)).setBackgroundColor(rnd.nextInt());
                ((FrameLayout)findViewById(R.id.lw1_tl)).setBackgroundColor(rnd.nextInt());
                ((FrameLayout)findViewById(R.id.lw1_tr)).setBackgroundColor(rnd.nextInt());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}