package com.smarthome.uenics.ucontrol.ui.dashboard;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.smarthome.uenics.ucontrol.BR;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.ActivityDashboardBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseActivity;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.dashboard.dialog.AddHomeDialog;

import javax.inject.Inject;


public class DashboardActivity extends BaseActivity<ActivityDashboardBinding,VMDashbord> implements iDashboard{

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FragmentManager fragmentManager;


    private VMDashbord view_model;

    /*@Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }*/

    @Override
    public int getBindingVariable() {
        return BR.vmDashboard;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public VMDashbord getViewModel() {
        view_model = new ViewModelProvider(this,factory).get(VMDashbord.class);
        view_model.setFragmentManager(fragmentManager);
        return view_model;
    }

    @Override
    public void onSetup() {
        super.onSetup();
        view_model.setNavigator(this);

    }

    @Override
    public void showAddHomeDialog() {
        new AddHomeDialog().show(fragmentManager,AddHomeDialog.TAG);
    }
}
