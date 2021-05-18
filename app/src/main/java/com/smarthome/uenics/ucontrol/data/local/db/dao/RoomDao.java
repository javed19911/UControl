package com.smarthome.uenics.ucontrol.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.smarthome.uenics.ucontrol.data.model.others.Room;
import com.smarthome.uenics.ucontrol.data.model.others.mRoom;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    Long insert(mRoom room);

    @Transaction
    @Insert(onConflict = REPLACE)
    void insertAll(List<mRoom> rooms);

    @Transaction
    @Query("SELECT * FROM Room")
    LiveData<List<mRoom>> loadAll();

    @Transaction
    @Query("SELECT * FROM Room")
    LiveData<List<Room>> loadAllDetails();

    @Transaction
    @Delete
    void delete(mRoom room);

    @Transaction
    @Query("DELETE FROM Room")
    public void Truncate();
}
