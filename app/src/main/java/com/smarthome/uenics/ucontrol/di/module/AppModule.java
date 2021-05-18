package com.smarthome.uenics.ucontrol.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.smarthome.uenics.ucontrol.data.AppDataManager;
import com.smarthome.uenics.ucontrol.data.DataManager;
import com.smarthome.uenics.ucontrol.data.local.db.AppDatabase;
import com.smarthome.uenics.ucontrol.data.local.db.AppDbHelper;
import com.smarthome.uenics.ucontrol.data.local.db.DbHelper;
import com.smarthome.uenics.ucontrol.data.local.prefs.AppPreferencesHelper;
import com.smarthome.uenics.ucontrol.data.local.prefs.PreferencesHelper;
import com.smarthome.uenics.ucontrol.di.DatabaseInfo;
import com.smarthome.uenics.ucontrol.di.PreferenceInfo;
import com.smarthome.uenics.ucontrol.utils.AppConstants;
import com.smarthome.uenics.ucontrol.utils.rx.AppSchedulerProvider;
import com.smarthome.uenics.ucontrol.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }


  /*  @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }*/

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


}
