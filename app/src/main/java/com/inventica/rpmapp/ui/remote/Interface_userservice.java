package com.inventica.rpmapp.ui.remote;


import com.inventica.rpmapp.ui.modles.CountryResponse;
import com.inventica.rpmapp.ui.modles.StateResponse;


import org.openapitools.client.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Interface_userservice {

    @POST("Authentication/Post_ValidateLogin")
    @FormUrlEncoded
    Call<LoginResponse> getValidateLoginPostNew(@Field("User_Email") String userEmail, @Field("User_Version") String app_version);


    @GET("api/DropDown/GetCountry")
    Call<List<CountryResponse>> get_countrylist();

    @GET("api/DropDown/GetProvinceState")
    Call<List<StateResponse>> get_provincestatelist(@Query("id") int country_id);

}
