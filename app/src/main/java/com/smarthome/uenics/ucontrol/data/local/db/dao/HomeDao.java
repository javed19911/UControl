package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;


import com.smarthome.uenics.ucontrol.data.model.others.Home;
import com.smarthome.uenics.ucontrol.data.model.others.mHome;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitch;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface HomeDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    Long insert(mHome home);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mHome> homes);

    @Transaction
    @Query("SELECT * FROM Home")
    LiveData<List<mHome>> loadAll();

    @Transaction
    @Query("SELECT * FROM Home")
    LiveData<List<Home>> loadAllDetails();

    @Transaction
    @Delete
    void delete(mHome home);

    @Transaction
    @Query("DELETE FROM Home")
    public void Truncate();
}
