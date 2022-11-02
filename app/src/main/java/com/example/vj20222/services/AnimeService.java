package com.example.vj20222.services;


import com.example.vj20222.entities.Anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimeService {

    @GET("anime")
    Call<List<Anime>> get();

    @POST("anime")
    Call<Anime> create(@Body Anime anime);

    @PUT("anime/{id}")
    Call<Anime> update(@Path("id") int id, @Body Anime anime);

}
