package ru.serrox.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by serrox on 09.11.2014.
 */
public class lw2Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw2_layout);

        final ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.layout.lw2_item_layout, R.id.lw2_tv_obj, items);
        ListView listv = (ListView) findViewById(R.id.lw2_listView);
        listv.setAdapter(a);

        ((Button) findViewById(R.id.lw2_add_b)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.add(((EditText) findViewById(R.id.lw2_input)).getText().toString());
                a.notifyDataSetChanged();
                ((EditText) findViewById(R.id.lw2_input)).setText("");
            }
        });

        listv.setLongClickable(true);

        listv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                a.remove(a.getItem(position));
                return true;
            }
        });


    }
}