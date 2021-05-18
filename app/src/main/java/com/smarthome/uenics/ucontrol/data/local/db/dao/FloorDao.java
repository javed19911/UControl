package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.smarthome.uenics.ucontrol.data.model.others.Floor;
import com.smarthome.uenics.ucontrol.data.model.others.mFloor;
import com.smarthome.uenics.ucontrol.data.model.others.mHome;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FloorDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    Long insert(mFloor floor);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mFloor> floors);

    @Transaction
    @Query("SELECT * FROM Floor")
    LiveData<List<mFloor>> loadAll();

    @Transaction
    @Query("SELECT * FROM Floor")
    LiveData<List<Floor>> loadAllDetails();

    @Transaction
    @Delete
    void delete(mFloor floor);

    @Transaction
    @Query("DELETE FROM Floor")
    public void Truncate();
}
