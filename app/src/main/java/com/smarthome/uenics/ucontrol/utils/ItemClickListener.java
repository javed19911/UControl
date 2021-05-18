package com.smarthome.uenics.ucontrol.utils;

import android.view.View;

public interface ItemClickListener<S> {
    void onClick(View view, S item, boolean isLongClick);
}
