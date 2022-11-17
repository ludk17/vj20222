package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vj20222.database.AppDatabase;
import com.example.vj20222.entities.User;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.UserService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getInstance(this);

        User user = new User();
        user.name = "Jose Jose";
        user.avatar = "https://img.png";
        user.edad = 33;
        user.createdAt = "2022-11-17";

//        db.userDao().create(user);


        List<User> users = db.userDao().getAll();

        Log.i("MAIN_APP", new Gson().toJson(users));

        User u1 = db.userDao().find(1);

        Log.i("MAIN_APP", new Gson().toJson(u1));


    }


}