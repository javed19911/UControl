package com.smarthome.uenics.ucontrol.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.data.local.prefs.PreferencesHelper;
import com.smarthome.uenics.ucontrol.ui.dashboard.DashboardActivity;
import com.smarthome.uenics.ucontrol.ui.login.LoginActivity;

import javax.inject.Inject;


public abstract class BaseNavigationActivity<T extends ViewDataBinding, V extends BaseViewModel> extends BaseActivity<T, V> implements NavigationView.OnNavigationItemSelectedListener {

    @IdRes
    protected abstract int getDrawerLayoutId();

    @IdRes
    protected abstract int getToolbarId();

    @Inject
    PreferencesHelper preferencesHelper;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(getToolbarId());
        drawer = findViewById(getDrawerLayoutId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        TextView textView = headerView.findViewById(R.id.user_name);
        textView.setText("Welcome "  + preferencesHelper.getCurrentUserName());*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.index) {
            Intent mode_intent = new Intent(this, DashboardActivity.class);
            mode_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mode_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mode_intent);
        }
        else if (id == R.id.logout) {

            //clear_pref_data(NavigationHelper.this);
            Intent mode_intent = new Intent(this, LoginActivity.class);
            mode_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mode_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mode_intent);
        }
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
