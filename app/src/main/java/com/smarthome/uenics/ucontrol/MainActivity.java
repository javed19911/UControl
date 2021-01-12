package com.smarthome.uenics.ucontrol;
import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ImageView wheel;
    private double mPrevAngle = 0;
    private double mCurrAngle = 0;
    public ListView listView;
    ArrayList<String> room_name;
    //String room_name[],date[];
    private ShareClass sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent a=getIntent();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        TextView home_name= (TextView) findViewById(R.id.home_name);
        home_name.setText(a.getStringExtra("home_name"));
        sharedPreference = new ShareClass(this);
        room_name= new ArrayList<>();
        for(int i=0;i<5;i++)
        {
            String key="Room_name"+String.valueOf(i);
            String xz=sharedPreference.getValue(key,key);
            room_name.add(xz);

        }
        customAdapterRoom adapter=new customAdapterRoom(this, room_name);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        ImageView view = (ImageView) findViewById(R.id.imageView);
        wheel = (ImageView) findViewById(R.id.imageView);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                final float xc = wheel.getWidth() / 2;
                final float yc = wheel.getHeight() / 2;

                final float x = event.getX();
                final float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        wheel.clearAnimation();
                        mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        mPrevAngle = mCurrAngle;
                        mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                        animate(mPrevAngle, mCurrAngle, 0);
                        System.out.println(mCurrAngle);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        mPrevAngle = mCurrAngle = 0;
                        break;
                    }
                }
                return true;
            }

            private void animate(double fromDegrees, double toDegrees, long durationMillis) {
                final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(durationMillis);
                rotate.setFillEnabled(true);
                rotate.setFillAfter(true);
                wheel.startAnimation(rotate);
                Toast.makeText(getApplicationContext(),""+mCurrAngle,Toast.LENGTH_SHORT).show();
                System.out.println(mCurrAngle);
            }

        });
    }

}
