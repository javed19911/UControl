
package com.smarthome.uenics.ucontrol.data.local.prefs;


import com.smarthome.uenics.ucontrol.data.DataManager;

import java.util.ArrayList;


public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getPassword();

    void setPassword(String password);

    Boolean isRememberCredentials();

    void setRememberCredentials(Boolean rememberCredentials);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    ArrayList<String> getCommodities();

    void saveCommodities(ArrayList<String> commodities);
}
