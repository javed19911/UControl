package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Home")
public class mHome {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String pic;

    @Ignore
    private List<mFloor> floors = null;

    public mHome() {
    }

    public mHome(Home home) {
        this.id = home.getHome().id;
    }

//getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public List<mFloor> getFloors() {
        return floors;
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setFloors(List<mFloor> floors) {
        this.floors = floors;
    }
}
