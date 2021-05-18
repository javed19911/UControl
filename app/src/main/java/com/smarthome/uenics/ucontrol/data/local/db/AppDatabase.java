
package com.smarthome.uenics.ucontrol.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.smarthome.uenics.ucontrol.data.local.db.dao.FloorDao;
import com.smarthome.uenics.ucontrol.data.local.db.dao.HomeDao;
import com.smarthome.uenics.ucontrol.data.local.db.dao.ParameterDao;
import com.smarthome.uenics.ucontrol.data.local.db.dao.RoomDao;
import com.smarthome.uenics.ucontrol.data.local.db.dao.SwitchBoardDao;
import com.smarthome.uenics.ucontrol.data.local.db.dao.SwitchDao;
import com.smarthome.uenics.ucontrol.data.model.others.Home;
import com.smarthome.uenics.ucontrol.data.model.others.mFloor;
import com.smarthome.uenics.ucontrol.data.model.others.mHome;
import com.smarthome.uenics.ucontrol.data.model.others.mParameter;
import com.smarthome.uenics.ucontrol.data.model.others.mRoom;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitch;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitchBoard;
import com.smarthome.uenics.ucontrol.utils.Converters;


@Database(entities = {mParameter.class, mHome.class, mFloor.class,
        mRoom.class, mSwitchBoard.class, mSwitch.class},
        version = 1,exportSchema = false)

@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {

     public abstract ParameterDao parameterDao();
     public abstract HomeDao homeDao();
     public abstract FloorDao floorDao();
     public abstract RoomDao roomDao();
     public abstract SwitchBoardDao switchBoardDao();
     public abstract SwitchDao switchDao();

}
