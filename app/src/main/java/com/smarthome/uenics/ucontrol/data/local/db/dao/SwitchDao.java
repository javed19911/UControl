package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.smarthome.uenics.ucontrol.data.model.others.mSwitch;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SwitchDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    Long insert(mSwitch mSwitch);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mSwitch> switches);

    @Transaction
    @Query("SELECT * FROM Switch")
    LiveData<List<mSwitch>> loadAll();

    @Transaction
    @Delete
    void delete(mSwitch mSwitch);

    @Transaction
    @Query("DELETE FROM Switch")
    public void Truncate();
}
