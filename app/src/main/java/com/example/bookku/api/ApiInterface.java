package com.example.bookku.api;

import com.example.bookku.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("lihat.php")
    Call<Value> lihat();

    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);
}
