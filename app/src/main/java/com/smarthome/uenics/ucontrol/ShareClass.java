package com.smarthome.uenics.ucontrol;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareClass {
    public static final String PREFS_NAME = "AOP_PREFS1";
    public static final String PREFS_KEY = "AOP_PREFS_String";
    Context context;
    public ShareClass(Context context) {
        super();
        this.context=context;
    }
    public void save(String key,String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(key, text); //3
        editor.commit(); //4
    }

    public String getValue(String key,String Default) {
        SharedPreferences settings;
        String text=null;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(key, Default);
        return text;

    }
    public void clearSharedPreference() {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(String key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(key);
        editor.commit();
    }

}
