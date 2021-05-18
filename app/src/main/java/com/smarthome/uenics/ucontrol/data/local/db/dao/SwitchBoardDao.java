package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.smarthome.uenics.ucontrol.data.model.others.SwitchBoard;
import com.smarthome.uenics.ucontrol.data.model.others.mFloor;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitchBoard;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SwitchBoardDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    Long insert(mSwitchBoard switchBoard);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mSwitchBoard> switchBoards);

    @Transaction
    @Query("SELECT * FROM SwitchBoard")
    LiveData<List<mSwitchBoard>> loadAll();

    @Transaction
    @Query("SELECT * FROM SwitchBoard")
    LiveData<List<SwitchBoard>> loadAllDetails();

    @Transaction
    @Delete
    void delete(mSwitchBoard switchBoard);

    @Transaction
    @Query("DELETE FROM SwitchBoard")
    public void Truncate();
}
