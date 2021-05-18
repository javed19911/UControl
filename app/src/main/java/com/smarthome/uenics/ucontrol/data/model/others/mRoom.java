package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.smarthome.uenics.ucontrol.data.enums.eRoomCategory;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Room")
public class mRoom {

    private eRoomCategory category = eRoomCategory.NORMAL;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey
            (entity = mFloor.class,
                    parentColumns = "id",
                    childColumns = "floor_id",
                    onDelete = CASCADE)
    private int floor_id;

    private String version;
    private String name;
    private String mac;
    private String pic;
    private boolean is_master;
    private int temperature;

    @Ignore
    private List<mSwitchBoard> switchBoards = null;

    //getter

    public eRoomCategory getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public int getFloor_id() {
        return floor_id;
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

    public String getPic() {
        return pic;
    }

    public boolean isIs_master() {
        return is_master;
    }

    public int getTemperature() {
        return temperature;
    }

    public List<mSwitchBoard> getSwitchBoards() {
        return switchBoards;
    }
    //setter

    public void setCategory(eRoomCategory category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
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

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setIs_master(boolean is_master) {
        this.is_master = is_master;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setSwitchBoards(List<mSwitchBoard> switchBoards) {
        this.switchBoards = switchBoards;
    }
}
