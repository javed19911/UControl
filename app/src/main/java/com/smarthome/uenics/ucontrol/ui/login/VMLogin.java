package com.smarthome.uenics.ucontrol.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.smarthome.uenics.ucontrol.data.DataManager;
import com.smarthome.uenics.ucontrol.data.model.others.LoginResponse;
import com.smarthome.uenics.ucontrol.ui.base.BaseViewModel;
import com.smarthome.uenics.ucontrol.utils.rx.SchedulerProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VMLogin extends BaseViewModel<iLogin> {

    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isRemember = new MutableLiveData<>(false);

    public MutableLiveData<String> email_error =new MutableLiveData<>("");
    public MutableLiveData<String> password_error =new MutableLiveData<>("");

/*
    public String getEmail_error() {
        return email_error;
    }

    public void setEmail_error(String email_error) {
        this.email_error = email_error;
    }

    public String getPassword_error() {
        return password_error;
    }

    public void setPassword_error(String password_error) {
        this.password_error = password_error;
    }
*/

    /*public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        if (email.contains("@")){
            email = email.substring(0,email.lastIndexOf("@"));
        }
        this.email.setValue(email);
    }

    public String getPassword() {
        return password.getValue();
    }

    public void setPassword(String password) {
        this.password.setValue(password);

    }*/

    public VMLogin(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        if (getDataManager().isRememberCredentials()){
            email.setValue(getDataManager().getCurrentUserName());
            password.setValue(getDataManager().getPassword());
            isRemember.setValue(getDataManager().isRememberCredentials());
        }
    }


    public void validateLogin(){

        if (email.getValue() == null || email.getValue().isEmpty()){
            if (getNavigator() != null) {
                getNavigator().showMessage("Please enter User Name");
            }
            email_error.setValue( "Please enter User Name");
            return;
        }
        if (password.getValue() == null || password.getValue().isEmpty()){
            if (getNavigator() != null) {
                getNavigator().showMessage("Invalid Password");
            }
            password_error.setValue( "Invalid Password");
            return;


        }

        setIsLoading(true);
        getDataManager().login(email.getValue(),password.getValue())
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getStatus().equalsIgnoreCase("success")) {
                                getDataManager().setCurrentUserEmail(loginResponse.getEmail());
                                getDataManager().setCurrentUserName(email.getValue());
                                getDataManager().setCurrentUserId(loginResponse.getId());
                                getDataManager().setPassword(password.getValue());
                                getDataManager().setRememberCredentials(isRemember.getValue());
                                getDataManager().setAccessToken(loginResponse.getAuthentication_token());

                                getDataManager().deleteAllParameters();
                                getDataManager().insertParameters(loginResponse.getParameters());
                                getDataManager().saveCommodities(loginResponse.getCommodities());


                                /*if (!loginResponse.getRole().equalsIgnoreCase("supervisor")) {
                                    if (getNavigator() != null){
                                        getNavigator().openDashboard();
                                    }
                                }else{*/
                                    if (getNavigator() != null){
                                        getNavigator().openDashboard();
                                    }

                                /*if (getNavigator() != null) {
                                    getNavigator().showMessage(loginResponse.getUser().getName() +"_"+ loginResponse.getUser().getCompany());
                                }*/
//                                }

                            }else{
                                if (getNavigator() != null) {
                                    getNavigator().showMessage(loginResponse.getStatus());
                                }
                            }
                        }else{
                            if (getNavigator() != null) {
                                getNavigator().showMessage("Data Error....");
                            }
                        }
                        setIsLoading(false);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        setIsLoading(false);
                        if (getNavigator() != null) {
                            getNavigator().handleError(t);
                        }
                    }
                });

    }
}
