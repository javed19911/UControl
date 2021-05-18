package com.smarthome.uenics.ucontrol.data.local.db;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smarthome.uenics.ucontrol.data.model.others.Floor;
import com.smarthome.uenics.ucontrol.data.model.others.Home;
import com.smarthome.uenics.ucontrol.data.model.others.Room;
import com.smarthome.uenics.ucontrol.data.model.others.SwitchBoard;
import com.smarthome.uenics.ucontrol.data.model.others.mFloor;
import com.smarthome.uenics.ucontrol.data.model.others.mHome;
import com.smarthome.uenics.ucontrol.data.model.others.mParameter;
import com.smarthome.uenics.ucontrol.data.model.others.mRoom;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitch;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitchBoard;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


    @Override
    public LiveData<List<mParameter>> getAllParameters() {
        return mAppDatabase.parameterDao().loadAll();
    }

    @Override
    public LiveData<Boolean> deleteAllParameters() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.parameterDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertParameters(List<mParameter> parameters) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.parameterDao().insertAll(parameters);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<List<Home>> getAllHomes() {
        return mAppDatabase.homeDao().loadAllDetails();
    }

    @Override
    public LiveData<Boolean> deleteAllHomes() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.homeDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> deleteHome(mHome home) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.homeDao().delete(home);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertHome(mHome home) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.homeDao().insert(home);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertHomes(List<mHome> homes) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.homeDao().insertAll(homes);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<List<Floor>> getAllFloor() {
        return mAppDatabase.floorDao().loadAllDetails();
    }

    @Override
    public LiveData<Boolean> deleteFloor(mFloor floor) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.floorDao().delete(floor);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> deleteAllFloors() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.floorDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertFloor(mFloor floor) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.floorDao().insert(floor);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertFloors(List<mFloor> floors) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.floorDao().insertAll(floors);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<List<Room>> getAllRooms() {
        return mAppDatabase.roomDao().loadAllDetails();
    }

    @Override
    public LiveData<Boolean> deleteRoom(mRoom room) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.roomDao().delete(room);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> deleteAllRooms() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.roomDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertRoom(mRoom room) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.roomDao().insert(room);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertRooms(List<mRoom> rooms) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.roomDao().insertAll(rooms);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<List<SwitchBoard>> getAllSwitchBoard() {
        return mAppDatabase.switchBoardDao().loadAllDetails();
    }

    @Override
    public LiveData<Boolean> deleteSwitchBoard(mSwitchBoard switchBoard) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchBoardDao().delete(switchBoard);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> deleteAllSwitchBoards() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchBoardDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertSwitchBoard(mSwitchBoard switchBoard) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchBoardDao().insert(switchBoard);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertSwitchBoards(List<mSwitchBoard> switchBoards) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchBoardDao().insertAll(switchBoards);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<List<mSwitch>> getAllSwitches() {
        return mAppDatabase.switchDao().loadAll();
    }

    @Override
    public LiveData<Boolean> deleteSwitch(mSwitch mSwitch) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchDao().delete(mSwitch);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> deleteAllSwitches() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchDao().Truncate();
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertSwitch(mSwitch mSwitch) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchDao().insert(mSwitch);
            response.postValue(true);
        });
        return response;
    }

    @Override
    public LiveData<Boolean> insertSwitches(List<mSwitch> switches) {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        AsyncTask.execute(()-> {
            mAppDatabase.switchDao().insertAll(switches);
            response.postValue(true);
        });
        return response;
    }


}
