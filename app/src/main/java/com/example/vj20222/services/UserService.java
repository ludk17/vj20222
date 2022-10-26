package com.example.vj20222.services;

import com.example.vj20222.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("users")
    Call<List<User>> all();
}
