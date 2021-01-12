package com.smarthome.uenics.ucontrol;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Switch_activity extends AppCompatActivity {
    public ListView listView;
    ArrayList<String> room_name;
    //String room_name[],date[];
    private ShareClass sharedPreference;
    Intent a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_layout);
        a=getIntent();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        TextView home_name= (TextView) findViewById(R.id.home_name);
        home_name.setText(a.getStringExtra("room_name"));
        sharedPreference = new ShareClass(this);
        room_name = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String key = "Switch_" + String.valueOf(i);
            String xz = sharedPreference.getValue(key, key);
            room_name.add(xz);

        }
        customAdapter adapter = new customAdapter(this, room_name);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }
}
