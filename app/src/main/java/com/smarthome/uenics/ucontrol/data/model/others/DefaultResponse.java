package com.smarthome.uenics.ucontrol.data.model.others;

public class DefaultResponse {
    private String status;
    private int response_code;
    private String error_msg ="Something went wrong...";

    //getter


    public String getStatus() {
        return status;
    }

    public int getResponse_code() {
        return response_code;
    }

    public String getError_msg() {
        return error_msg;
    }


    //setter


    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
