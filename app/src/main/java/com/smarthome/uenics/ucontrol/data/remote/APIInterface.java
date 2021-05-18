package com.smarthome.uenics.ucontrol.data.remote;


import com.smarthome.uenics.ucontrol.data.model.others.DefaultResponse;
import com.smarthome.uenics.ucontrol.data.model.others.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {

    //@Headers("Content-Type: application/json")
    @POST("api/users/login")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);


}
