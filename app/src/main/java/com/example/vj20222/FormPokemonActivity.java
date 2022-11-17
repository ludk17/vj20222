package com.example.vj20222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.vj20222.entities.ImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pokemon);

        // configuro el boton
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Bitmap
        // bitmap a base64


        // retrofit -> URL
/*
        imageService.sendImage(image).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ImageResponse r = response.body(); //url
                // et.setTxt(url);
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
*/
    }

}