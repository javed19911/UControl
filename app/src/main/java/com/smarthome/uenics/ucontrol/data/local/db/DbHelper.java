
package com.smarthome.uenics.ucontrol.data.local.db;


import androidx.lifecycle.LiveData;

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


public interface DbHelper {

    /// parameters
    LiveData<List<mParameter>> getAllParameters();

    LiveData<Boolean> deleteAllParameters();

    LiveData<Boolean> insertParameters(final List<mParameter> parameters);

    /// home
    LiveData<List<Home>> getAllHomes();

    LiveData<Boolean> deleteAllHomes();

    LiveData<Boolean> deleteHome(mHome home);

    LiveData<Boolean> insertHome(mHome home);

    LiveData<Boolean> insertHomes(final List<mHome> homes);

    /// floors
    LiveData<List<Floor>> getAllFloor();

    LiveData<Boolean> deleteFloor(mFloor floor);

    LiveData<Boolean> deleteAllFloors();

    LiveData<Boolean> insertFloor(mFloor floor);

    LiveData<Boolean> insertFloors(final List<mFloor> floors);

    /// rooms
    LiveData<List<Room>> getAllRooms();

    LiveData<Boolean> deleteRoom(mRoom room);

    LiveData<Boolean> deleteAllRooms();

    LiveData<Boolean> insertRoom(mRoom room);

    LiveData<Boolean> insertRooms(final List<mRoom> rooms);

    /// switchBoards
    LiveData<List<SwitchBoard>> getAllSwitchBoard();

    LiveData<Boolean> deleteSwitchBoard(mSwitchBoard switchBoard);

    LiveData<Boolean> deleteAllSwitchBoards();

    LiveData<Boolean> insertSwitchBoard(mSwitchBoard switchBoard);

    LiveData<Boolean> insertSwitchBoards(final List<mSwitchBoard> switchBoards);

    /// switches
    LiveData<List<mSwitch>> getAllSwitches();

    LiveData<Boolean> deleteSwitch(mSwitch mSwitch);

    LiveData<Boolean> deleteAllSwitches();

    LiveData<Boolean> insertSwitch(mSwitch mSwitch);

    LiveData<Boolean> insertSwitches(final List<mSwitch> switches);



}
