package com.smarthome.uenics.ucontrol.ui.dashboard.fragment;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.FragmentHomeBinding;
import com.smarthome.uenics.ucontrol.databinding.FragmentNoHomeBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseFragment;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.dashboard.VMDashbord;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, VMDashbord> {

    @Inject
    ViewModelProviderFactory factory;

    FragmentHomeBinding fragmentHomeBinding;

    VMDashbord vmDashbord;

    @Override
    public int getBindingVariable() {
        return BR.vmDashboard;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public VMDashbord getViewModel() {
        vmDashbord = new ViewModelProvider(getBaseActivity(), factory).get(VMDashbord.class);
        return vmDashbord;
    }


}