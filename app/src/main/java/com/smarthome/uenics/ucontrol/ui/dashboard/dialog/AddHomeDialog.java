package com.smarthome.uenics.ucontrol.ui.dashboard.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.AddHomeDialogBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseDialog;

public class AddHomeDialog extends BaseDialog {
    public static String TAG = "add_home";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddHomeDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout. add_home_dialog, null, false);
        dialog.setContentView(binding.getRoot());
        //binding.setHome();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }
}
