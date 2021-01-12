package com.smarthome.uenics.ucontrol;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by uenics on 1/11/2016.
 */
public class StartActivity extends AppCompatActivity {
    private double mPrevAngle = 0;
    private double mCurrAngle = 0,mPrevAngle1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_layout);
        final TextView regulator_status= (TextView) findViewById(R.id.next);
        final TextView regulator_status1= (TextView) findViewById(R.id.speed);
        final RelativeLayout regulator_status12= (RelativeLayout) findViewById(R.id.header1);
        SeekBar regulator= (SeekBar) findViewById(R.id.seekBar);
        regulator.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                regulator_status.setText("" + seekBar.getProgress());
                regulator_status12.setVisibility(View.VISIBLE);
                regulator_status1.setText("" + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                regulator_status12.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });
        ImageView im= (ImageView) findViewById(R.id.re);
        im.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final float xc = view.getWidth() / 2;
                final float yc = view.getHeight() / 2;

                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        mPrevAngle1=mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                        if(-120<mCurrAngle && mCurrAngle<120)
                            view.clearAnimation();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        mPrevAngle = mCurrAngle;
                        mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                        if(-120<mCurrAngle && mCurrAngle<120) {
                            animate(mPrevAngle, mCurrAngle, 0, view);
                        }
                        else
                        mCurrAngle= mPrevAngle;
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if(-120<mCurrAngle && mCurrAngle<120) {
                            mCurrAngle = (mCurrAngle - mCurrAngle % 24) / 24;
                            if(mCurrAngle>=0)
                                mCurrAngle=mCurrAngle+5;
                            else
                                mCurrAngle=mCurrAngle+4;
                            Toast.makeText(getApplicationContext(), "" + mCurrAngle, Toast.LENGTH_SHORT).show();
                        }
                        mPrevAngle1 = mCurrAngle = 0;
                        break;
                    }
                }
                return true;
            }
            private void animate(double fromDegrees, double toDegrees, long durationMillis,View view) {
                final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(durationMillis);
                rotate.setFillEnabled(true);
                rotate.setFillAfter(true);
                toDegrees = (toDegrees - toDegrees % 24) / 24;
                if(toDegrees>=0)
                    toDegrees=toDegrees+5;
                else
                    toDegrees=toDegrees+4;
                regulator_status.setText(("" +toDegrees).substring(0,1));
                view.startAnimation(rotate);
                System.out.println(mCurrAngle);
            }
        });
    }
}