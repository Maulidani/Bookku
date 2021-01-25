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

    @GET("lihat_filsafat_dan_psikologi.php")
    Call<Value> lihatfilsafatdanpsikologi();

    @GET("lihat_agama.php")
    Call<Value> lihatagama();

    @GET("lihat_sosial.php")
    Call<Value> lihatsosial();

    @GET("lihat_bahasa.php")
    Call<Value> lihatbahasa();

    @GET("lihat_sains_dan_matematika.php")
    Call<Value> lihatsainsdanmatematika();

    @GET("lihat_teknologi.php")
    Call<Value> lihatteknologi();

    @GET("lihat_seni_dan_rekreasi.php")
    Call<Value> lihatsenidanrekreasi();

    @GET("lihat_literatur_dan_sastra.php")
    Call<Value> lihatliteraturdansastra();

    @GET("lihat_sejarah_dan_geografi.php")
    Call<Value> lihatsejarahdangeografi();


    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);
}
