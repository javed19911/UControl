
package com.smarthome.uenics.ucontrol.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.smarthome.uenics.ucontrol.data.local.db.DbHelper;
import com.smarthome.uenics.ucontrol.data.local.prefs.PreferencesHelper;
import com.smarthome.uenics.ucontrol.data.model.others.DefaultResponse;
import com.smarthome.uenics.ucontrol.data.model.others.Floor;
import com.smarthome.uenics.ucontrol.data.model.others.Home;
import com.smarthome.uenics.ucontrol.data.model.others.LoginResponse;
import com.smarthome.uenics.ucontrol.data.model.others.Room;
import com.smarthome.uenics.ucontrol.data.model.others.SwitchBoard;
import com.smarthome.uenics.ucontrol.data.model.others.mFloor;
import com.smarthome.uenics.ucontrol.data.model.others.mHome;
import com.smarthome.uenics.ucontrol.data.model.others.mParameter;
import com.smarthome.uenics.ucontrol.data.model.others.mRoom;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitch;
import com.smarthome.uenics.ucontrol.data.model.others.mSwitchBoard;
import com.smarthome.uenics.ucontrol.data.remote.APIInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private Retrofit mRetrofit;

    private final DbHelper mDbHelper;

    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, Retrofit retrofit) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mRetrofit = retrofit;
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getPassword() {
        return mPreferencesHelper.getPassword();
    }

    @Override
    public void setPassword(String password) {
        mPreferencesHelper.setPassword(password);
    }

    @Override
    public Boolean isRememberCredentials() {
        return mPreferencesHelper.isRememberCredentials();
    }

    @Override
    public void setRememberCredentials(Boolean rememberCredentials) {
        mPreferencesHelper.setRememberCredentials(rememberCredentials);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public ArrayList<String> getCommodities() {
        return mPreferencesHelper.getCommodities();
    }

    @Override
    public void saveCommodities(ArrayList<String> commodities) {
        mPreferencesHelper.saveCommodities(commodities);
    }


    @Override
    public Call<LoginResponse> login(String email, String password) {

        if (!email.contains("@")){
            email = email.concat("@intellolabs.com");
        }

        return mRetrofit
                .create(APIInterface.class).login(email,password);
    }

    @Override
    public Call<DefaultResponse> UploadImage(String commodity_id, MultipartBody.Part upload_image) {
        return null;
    }


    @Override
    public LiveData<List<mParameter>> getAllParameters() {
        return mDbHelper.getAllParameters();
    }

    @Override
    public LiveData<Boolean> deleteAllParameters() {
        return mDbHelper.deleteAllParameters();
    }

    @Override
    public LiveData<Boolean> insertParameters(List<mParameter> parameters) {
        return mDbHelper.insertParameters(parameters);
    }

    @Override
    public LiveData<List<Home>> getAllHomes() {
        return mDbHelper.getAllHomes();
    }

    @Override
    public LiveData<Boolean> deleteAllHomes() {
        return mDbHelper.deleteAllHomes();
    }

    @Override
    public LiveData<Boolean> deleteHome(mHome home) {
        return mDbHelper.deleteHome(home);
    }

    @Override
    public LiveData<Boolean> insertHome(mHome home) {
        return mDbHelper.insertHome(home);
    }

    @Override
    public LiveData<Boolean> insertHomes(List<mHome> homes) {
        return mDbHelper.insertHomes(homes);
    }

    @Override
    public LiveData<List<Floor>> getAllFloor() {
        return mDbHelper.getAllFloor();
    }

    @Override
    public LiveData<Boolean> deleteFloor(mFloor floor) {
        return mDbHelper.deleteFloor(floor);
    }

    @Override
    public LiveData<Boolean> deleteAllFloors() {
        return mDbHelper.deleteAllFloors();
    }

    @Override
    public LiveData<Boolean> insertFloor(mFloor floor) {
        return mDbHelper.insertFloor(floor);
    }

    @Override
    public LiveData<Boolean> insertFloors(List<mFloor> floors) {
        return mDbHelper.insertFloors(floors);
    }

    @Override
    public LiveData<List<Room>> getAllRooms() {
        return mDbHelper.getAllRooms();
    }

    @Override
    public LiveData<Boolean> deleteRoom(mRoom room) {
        return mDbHelper.deleteRoom(room);
    }

    @Override
    public LiveData<Boolean> deleteAllRooms() {
        return mDbHelper.deleteAllFloors();
    }

    @Override
    public LiveData<Boolean> insertRoom(mRoom room) {
        return mDbHelper.insertRoom(room);
    }

    @Override
    public LiveData<Boolean> insertRooms(List<mRoom> rooms) {
        return mDbHelper.insertRooms(rooms);
    }

    @Override
    public LiveData<List<SwitchBoard>> getAllSwitchBoard() {
        return mDbHelper.getAllSwitchBoard();
    }

    @Override
    public LiveData<Boolean> deleteSwitchBoard(mSwitchBoard switchBoard) {
        return mDbHelper.deleteSwitchBoard(switchBoard);
    }

    @Override
    public LiveData<Boolean> deleteAllSwitchBoards() {
        return mDbHelper.deleteAllSwitchBoards();
    }

    @Override
    public LiveData<Boolean> insertSwitchBoard(mSwitchBoard switchBoard) {
        return mDbHelper.insertSwitchBoard(switchBoard);
    }

    @Override
    public LiveData<Boolean> insertSwitchBoards(List<mSwitchBoard> switchBoards) {
        return mDbHelper.insertSwitchBoards(switchBoards);
    }

    @Override
    public LiveData<List<mSwitch>> getAllSwitches() {
        return mDbHelper.getAllSwitches();
    }

    @Override
    public LiveData<Boolean> deleteSwitch(mSwitch mSwitch) {
        return mDbHelper.deleteSwitch(mSwitch);
    }

    @Override
    public LiveData<Boolean> deleteAllSwitches() {
        return mDbHelper.deleteAllSwitches();
    }

    @Override
    public LiveData<Boolean> insertSwitch(mSwitch mSwitch) {
        return mDbHelper.insertSwitch(mSwitch);
    }

    @Override
    public LiveData<Boolean> insertSwitches(List<mSwitch> switches) {
        return mDbHelper.insertSwitches(switches);
    }
}
