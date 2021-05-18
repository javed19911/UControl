package com.smarthome.uenics.ucontrol.ui.login;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.smarthome.uenics.ucontrol.BR;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.ActivityLoginBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseActivity;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.ImageDataCollectionActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, VMLogin> implements iLogin{

    @Inject
    ViewModelProviderFactory factory;

    private VMLogin view_model;

    @Override
    public int getBindingVariable() {
        return BR.vmLogin;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public VMLogin getViewModel() {
        view_model = new ViewModelProvider(this,factory).get(VMLogin.class);
        return view_model;
    }

    @Override
    public void onSetup() {
        super.onSetup();
        view_model.setNavigator(this);
    }

    @Override
    public void showProgress(Boolean IsShown) {

    }

    @Override
    public void handleError(Throwable throwable) {
        showMessage(throwable.getLocalizedMessage());
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getViewDataBinding().getRoot(),message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openDashboard() {

        Intent intent = new Intent(this, ImageDataCollectionActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void openSelection() {
//        Intent intent = new Intent(this, SelectionActivity.class);
//        startActivity(intent);
//        finish();
    }
}
