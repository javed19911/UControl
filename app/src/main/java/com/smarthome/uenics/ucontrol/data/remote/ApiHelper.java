package com.smarthome.uenics.ucontrol.data.remote;

import com.smarthome.uenics.ucontrol.data.model.others.DefaultResponse;
import com.smarthome.uenics.ucontrol.data.model.others.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;

public interface ApiHelper {


    Call<LoginResponse> login(String email, String password);
    Call<DefaultResponse> UploadImage(String commodity_id, MultipartBody.Part upload_image);

}
