package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.smarthome.uenics.ucontrol.data.enums.eSwitchCategory;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Switch")
public class mSwitch {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName; // editable by user
    private String name; // configured from device

    @ForeignKey
            (entity = mSwitchBoard.class,
                    parentColumns = "id",
                    childColumns = "switch_board_id",
                    onDelete = CASCADE)
    @ColumnInfo(name = "switch_board_id")
    private int switchBoardId;

    private eSwitchCategory category = eSwitchCategory.SWITCH;
    private boolean enabled = false;  // if it is configured
    private int status = 0;  // ON/OFF or speed of fan or dimmer value


    //getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getSwitchBoardId() {
        return switchBoardId;
    }

    public eSwitchCategory getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getStatus() {
        return status;
    }
    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSwitchBoardId(int switchBoardId) {
        this.switchBoardId = switchBoardId;
    }

    public void setCategory(eSwitchCategory category) {
        this.category = category;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}