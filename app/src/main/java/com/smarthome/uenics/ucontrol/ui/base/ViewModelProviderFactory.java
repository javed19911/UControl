package com.smarthome.uenics.ucontrol.ui.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smarthome.uenics.ucontrol.data.DataManager;
import com.smarthome.uenics.ucontrol.ui.dashboard.VMDashbord;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.VMImageDataCollection;
import com.smarthome.uenics.ucontrol.ui.login.VMLogin;
import com.smarthome.uenics.ucontrol.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final DataManager dataManager;
  private final SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
   if (modelClass.isAssignableFrom(VMImageDataCollection.class)) {
      //noinspection unchecked
      return (T) new VMImageDataCollection(dataManager,schedulerProvider);
    }else if (modelClass.isAssignableFrom(VMLogin.class)) {
      //noinspection unchecked
      return (T) new VMLogin(dataManager,schedulerProvider);
    }else if (modelClass.isAssignableFrom(VMDashbord.class)) {
      //noinspection unchecked
      return (T) new VMDashbord(dataManager,schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}