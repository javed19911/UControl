package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

public class SwitchBoard {

    public SwitchBoard() {
    }

    @Embedded
    private mSwitchBoard switchBoard;

    @Relation(parentColumn = "id", entityColumn = "switch_board_id", entity = mSwitch.class)
    private List<mSwitch> switches;

    public mSwitchBoard getSwitchBoard() {
        return switchBoard;
    }

    public void setSwitchBoard(mSwitchBoard switchBoard) {
        this.switchBoard = switchBoard;
    }

    public List<mSwitch> getSwitches() {
        return switches;
    }

    public void setSwitches(List<mSwitch> switches) {
        this.switches = switches;
    }
}
