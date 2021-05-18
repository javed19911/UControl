
package com.smarthome.uenics.ucontrol.di.builder;

import com.smarthome.uenics.ucontrol.ui.dashboard.DashboardActivity;
import com.smarthome.uenics.ucontrol.ui.dashboard.DashboardFragmentInjector;
import com.smarthome.uenics.ucontrol.ui.dashboard.DashboardModule;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.ImageDataCollectionActivity;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.ImageDataCollectionFragmentInjector;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.ImageDataCollectionModule;
import com.smarthome.uenics.ucontrol.ui.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {DashboardModule.class, DashboardFragmentInjector.class})
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector(modules = {ImageDataCollectionModule.class, ImageDataCollectionFragmentInjector.class})
    abstract ImageDataCollectionActivity bindImageDataCollectionActivity();


}
