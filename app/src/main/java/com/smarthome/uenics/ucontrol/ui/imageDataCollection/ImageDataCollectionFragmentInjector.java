package com.smarthome.uenics.ucontrol.ui.imageDataCollection;


import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.Camera2BasicFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.CameraFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.UploadFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImageDataCollectionFragmentInjector {

    @ContributesAndroidInjector
    abstract CameraFragment bindCameraFragment();



    @ContributesAndroidInjector
    abstract Camera2BasicFragment bindCamera2BasicFragment();

    @ContributesAndroidInjector
    abstract UploadFragment bindUploadFragment();
}
