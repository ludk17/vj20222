package com.example.vj20222.services;


import com.example.vj20222.entities.Image;
import com.example.vj20222.entities.ImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImageService {


    @POST("3/image")
    Call<ImageResponse> sendImage(@Body Image image);

}
