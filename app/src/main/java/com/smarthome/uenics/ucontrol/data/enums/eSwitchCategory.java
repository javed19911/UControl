package com.smarthome.uenics.ucontrol.data.enums;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public enum eSwitchCategory {
    DIMMER("D"),
    FAN("F"),
    STEPPER_FAN("SF"),
    SWITCH("SW"),
    AC("AC"),
    FRIDGE("FR"),
    TEMPERATURE_SENSOR("TS"),
    SOCKET("SO"),
    DIMMER_SOCKET("DSO"),
    STORAGE_TANK_SENSOR("STS"),
    FILTER_TANK_SENSOR("FTS"),
    CAMERA("CAM");

    String value;

    eSwitchCategory(String value) {
        this.value = value;
    }


    @NonNull
    @Override
    public String toString() {
        return value;
    }

    public static eSwitchCategory getSwitchCategory(final String category) {
        for (eSwitchCategory switchCategory : eSwitchCategory.values()) {
            if (category.toUpperCase().equals(switchCategory.toString())) {
                return switchCategory;
            }
        }
        return null;
    }
}
