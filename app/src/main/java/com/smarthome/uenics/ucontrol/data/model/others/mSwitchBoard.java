package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "SwitchBoard")
public class mSwitchBoard {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey
            (entity = mRoom.class,
                    parentColumns = "id",
                    childColumns = "room_id",
                    onDelete = CASCADE)
    private int room_id;

    private String version;
    private String name;
    private String mac;
    private boolean is_master; // can it control other switch boards.
    private int temperature;

    @Ignore
    private List<mSwitch> switches = null;

    public mSwitchBoard() {
    }

    public mSwitchBoard(SwitchBoard switchBoard) {
        this.id = switchBoard.getSwitchBoard().getId();
        this.room_id = switchBoard.getSwitchBoard().getRoom_id();
        this.name = switchBoard.getSwitchBoard().getName();
        this.mac = switchBoard.getSwitchBoard().getMac();
        this.is_master = switchBoard.getSwitchBoard().isIs_master();
        this.temperature = switchBoard.getSwitchBoard().getTemperature();
        this.version = switchBoard.getSwitchBoard().getVersion();
        this.switches = switchBoard.getSwitches();
    }

    //getter

    public int getId() {
        return id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getMac() {
        return mac;
    }

    public boolean isIs_master() {
        return is_master;
    }

    public int getTemperature() {
        return temperature;
    }

    public List<mSwitch> getSwitches() {
        return switches;
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setIs_master(boolean is_master) {
        this.is_master = is_master;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setSwitches(List<mSwitch> switches) {
        this.switches = switches;
    }
}
