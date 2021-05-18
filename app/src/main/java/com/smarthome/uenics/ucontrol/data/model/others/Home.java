package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

public class Home {


    public Home() {
    }

    @Embedded
    private mHome home;

    @Relation(parentColumn = "id", entityColumn = "home_id", entity = mFloor.class)
    private List<mFloor> floors;

    public mHome getHome() {
        return home;
    }

    public void setHome(mHome home) {
        this.home = home;
    }

    public List<mFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<mFloor> floors) {
        this.floors = floors;
    }
}
