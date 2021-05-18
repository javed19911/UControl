package com.smarthome.uenics.ucontrol.data.model.others;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse extends DefaultResponse{
    private long id;
    private String email;
    private String authentication_token;

    @SerializedName("commodities")
    private ArrayList<String> commodities = null;

    @SerializedName("parameters")
    private ArrayList<mParameter> parameters = null;


    //getter
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthentication_token() {
        return authentication_token;
    }

    public ArrayList<String> getCommodities() {
        return commodities;
    }

    public ArrayList<mParameter> getParameters() {
        return parameters;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthentication_token(String authentication_token) {
        this.authentication_token = authentication_token;
    }

    public void setCommodities(ArrayList<String> commodities) {
        this.commodities = commodities;
    }

    public void setParameters(ArrayList<mParameter> parameters) {
        this.parameters = parameters;
    }
}
