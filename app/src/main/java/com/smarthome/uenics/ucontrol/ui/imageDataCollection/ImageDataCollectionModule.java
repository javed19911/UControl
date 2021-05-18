package com.smarthome.uenics.ucontrol.ui.imageDataCollection;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDataCollectionModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ImageDataCollectionActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    FragmentManager provideFragmentManager(ImageDataCollectionActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
