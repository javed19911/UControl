package com.smarthome.uenics.ucontrol.ui.dashboard;


import com.smarthome.uenics.ucontrol.ui.dashboard.fragment.HomeFragment;
import com.smarthome.uenics.ucontrol.ui.dashboard.fragment.NoHomeFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.Camera2BasicFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.CameraFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.UploadFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DashboardFragmentInjector {

    @ContributesAndroidInjector
    abstract NoHomeFragment bindNoHomeFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();


}
