package com.smarthome.uenics.ucontrol.di.component;

import android.app.Application;

import com.smarthome.uenics.ucontrol.data.remote.ApiModule;
import com.smarthome.uenics.ucontrol.di.builder.ActivityBuilder;
import com.smarthome.uenics.ucontrol.di.module.AppModule;
import com.smarthome.uenics.ucontrol.ui.base.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ApiModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

    void inject(MyApplication app);


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder baseURL(String Url);

        AppComponent build();
    }
}
