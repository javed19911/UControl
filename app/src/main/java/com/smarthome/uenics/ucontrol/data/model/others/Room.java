package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.smarthome.uenics.ucontrol.data.enums.eRoomCategory;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

public class Room {

    public Room() {
    }

    @Embedded
    private mRoom room;

    @Relation(parentColumn = "id", entityColumn = "room_id", entity = mSwitchBoard.class)
    private List<mSwitchBoard> switchBoards;

    public mRoom getRoom() {
        return room;
    }

    public void setRoom(mRoom room) {
        this.room = room;
    }

    public List<mSwitchBoard> getSwitchBoards() {
        return switchBoards;
    }

    public void setSwitchBoards(List<mSwitchBoard> switchBoards) {
        this.switchBoards = switchBoards;
    }
}
