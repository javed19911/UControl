package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

public class Floor {

    public Floor() {
    }

    @Embedded
    private mFloor floor;

    @Relation(parentColumn = "id", entityColumn = "floor_id", entity = mRoom.class)
    private List<mRoom> rooms;

    public mFloor getFloor() {
        return floor;
    }

    public void setFloor(mFloor floor) {
        this.floor = floor;
    }

    public List<mRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<mRoom> rooms) {
        this.rooms = rooms;
    }
}
