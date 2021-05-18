package com.smarthome.uenics.ucontrol.utils;

import android.app.Activity;
import android.content.Context;

//import com.google.firebase.analytics.FirebaseAnalytics;

public class Analytics {
    //FirebaseAnalytics analytics;
    Context context;
    Activity activity;

    public Analytics(Context context,Activity activity)
    {
        //analytics = FirebaseAnalytics.getInstance(context);
        this.context=context;
        this.activity=activity;
    }


//    public void timer(int time) {
//        String stringTime = String.valueOf(time);
//        analytics.setUserProperty("Timeset", stringTime);
//    }

    public void showScreenName(String name){
        //analytics.setCurrentScreen(activity, name, null /* class override */);
    }
}
