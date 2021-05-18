package com.smarthome.uenics.ucontrol.ui.login;

public interface iLogin {


   /* void setUserName(String name);
    void setPassword(String password);
    void setRemembered(Boolean remembered);

    String getUserName();
    String getPassword();
    Boolean getRemembered();*/

    void showProgress(Boolean IsShown);

    void handleError(Throwable throwable);
    void showMessage(String message);

    void openDashboard();
    void openSelection();
}
