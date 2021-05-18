package com.smarthome.uenics.ucontrol.ui.base;

import android.text.TextUtils;

import com.smarthome.uenics.ucontrol.data.remote.ApiModule;
import com.smarthome.uenics.ucontrol.di.component.AppComponent;
import com.smarthome.uenics.ucontrol.di.component.DaggerAppComponent;
import com.smarthome.uenics.ucontrol.utils.AppLogger;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MyApplication extends DaggerApplication {


    /*@Inject
    CalligraphyConfig mCalligraphyConfig;*/

    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        /* DaggerAppComponent.builder()
                .application(this)
                .baseURL(ApiModule.BASE_URL)
                .build()
                .inject(this);*/

        AppLogger.init();

        /* CalligraphyConfig.initDefault(mCalligraphyConfig);*/
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        AppComponent component = DaggerAppComponent.builder()
                .application(this)
                .baseURL(ApiModule.BASE_URL)
                .build();
        component.inject(this);

        return component;

    }


}
