package ru.serrox.labapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button go_lw1 = (Button) findViewById(R.id.butlw1);
        go_lw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, lw1Activity.class);
                startActivity(intent);
            }
        });
        Button go_lw2 = (Button) findViewById(R.id.butlw2);
        go_lw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, lw2Activity.class);
                startActivity(intent);
            }
        });
        Button go_lw3 = (Button) findViewById(R.id.butlw3);
        go_lw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, lw3Activity.class);
                startActivity(intent);
            }
        });
    }

}
