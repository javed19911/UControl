package com.smarthome.uenics.ucontrol.ui.dashboard;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smarthome.uenics.ucontrol.ui.imageDataCollection.ImageDataCollectionActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(DashboardActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    FragmentManager provideFragmentManager(DashboardActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
