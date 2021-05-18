package com.smarthome.uenics.ucontrol.data.model.others;

import com.google.gson.annotations.SerializedName;

public class mUser {

    @SerializedName("user_id")
    private Long id;

    @SerializedName("user_name")
    private String name;

    @SerializedName("user_email")
    private String email;

    @SerializedName("user_password")
    private String password;

    @SerializedName("user_company")
    private String company;

    //getter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    //setter


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
