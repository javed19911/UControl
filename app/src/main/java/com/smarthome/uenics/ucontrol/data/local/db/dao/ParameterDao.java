package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.smarthome.uenics.ucontrol.data.model.others.mParameter;

import java.util.List;


import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ParameterDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    void insert(mParameter parameter);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mParameter> parameters);

    @Transaction
    @Query("SELECT * FROM parameter")
    LiveData<List<mParameter>> loadAll();

    @Transaction
    @Query("DELETE FROM parameter")
    public void Truncate();
}
