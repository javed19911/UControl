package com.smarthome.uenics.ucontrol.ui.dashboard.fragment;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.FragmentCameraImageBinding;
import com.smarthome.uenics.ucontrol.databinding.FragmentNoHomeBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseFragment;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.dashboard.VMDashbord;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.VMImageDataCollection;

import javax.inject.Inject;


public class NoHomeFragment extends BaseFragment<FragmentNoHomeBinding, VMDashbord> {

    @Inject
    ViewModelProviderFactory factory;

    FragmentNoHomeBinding fragmentNoHomeBinding;

    VMDashbord vmDashbord;

    @Override
    public int getBindingVariable() {
        return BR.vmDashboard;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_no_home;
    }

    @Override
    public VMDashbord getViewModel() {
        vmDashbord = new ViewModelProvider(getBaseActivity(), factory).get(VMDashbord.class);
        return vmDashbord;
    }


}