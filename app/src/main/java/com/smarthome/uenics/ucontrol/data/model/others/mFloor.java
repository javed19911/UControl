package com.smarthome.uenics.ucontrol.data.model.others;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Floor")
public class mFloor {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey
            (entity = mHome.class,
                    parentColumns = "id",
                    childColumns = "home_id",
                    onDelete = CASCADE)
    private int home_id;

    private String name;
    private String mac;
    private String pic;

    @Ignore
    private List<mRoom> rooms = null;


    //getter

    public int getId() {
        return id;
    }

    public int getHome_id() {
        return home_id;
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

    public List<mRoom> getRooms() {
        return rooms;
    }



    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setHome_id(int home_id) {
        this.home_id = home_id;
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

    public void setRooms(List<mRoom> rooms) {
        this.rooms = rooms;
    }
}
