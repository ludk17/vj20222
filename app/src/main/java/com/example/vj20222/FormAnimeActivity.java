package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vj20222.entities.Anime;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormAnimeActivity extends AppCompatActivity {

    private Anime anime = new Anime();
    private EditText etFormAnimeName;
    private EditText etFormAnimeDescription;
    private EditText etFormAnimePosterURL;
    private Button btnSaveAnime;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anime);

        etFormAnimeName = findViewById(R.id.etFormAnimeName);
        etFormAnimeDescription = findViewById(R.id.etFormAnimeDescription);
        etFormAnimePosterURL = findViewById(R.id.etFormAnimePosterURL);

        etFormAnimePosterURL.setText("https://m.media-amazon.com/images/I/710NAj9qNVL._AC_UY1000_.jpg");

        btnSaveAnime = findViewById(R.id.btnSaveAnime);
        retrofit = new RetrofitFactory(this).build();

        Intent intent  = getIntent();
        String animeJson = intent.getStringExtra("ANIME_DATA");

        Log.i("MAIN_APP", "animeJson:" + animeJson);

        if (animeJson != null) {
            anime = new Gson().fromJson(animeJson, Anime.class);
            etFormAnimeName.setText(anime.name);
            etFormAnimeDescription.setText(anime.description);
            etFormAnimePosterURL.setText(anime.posterURL);
        }

        btnSaveAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnime(anime);
            }
        });

    }

    private void saveAnime(Anime anime) {

        if(anime.name == "" || anime.description == "" || anime.posterURL == "") {
            Toast.makeText(this, "Llenar datos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        anime.name = etFormAnimeName.getText().toString();
        anime.description = etFormAnimeDescription.getText().toString();
        anime.posterURL = etFormAnimePosterURL.getText().toString();

        Log.i("MAIN_ACTIVITY", new Gson().toJson(anime));

        if(anime.id == 0) {
            callCreateAPI(anime);
        } else {
            callUpdateAPI(anime);
        }

    }

    private void callUpdateAPI(Anime anime) {
        AnimeService service = retrofit.create(AnimeService.class);
        service.update(anime.id, anime).enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                if(response.code() != 200) {
                    Toast.makeText(FormAnimeActivity.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormAnimeActivity.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {
                Log.e("MAIN_APP",  t.toString());
            }
        });
    }

    private void callCreateAPI(Anime anime) {
        AnimeService service = retrofit.create(AnimeService.class);
        service.create(anime).enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                if(response.code() != 201) {
                    Toast.makeText(FormAnimeActivity.this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormAnimeActivity.this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {
                Log.e("MAIN_APP",  t.toString());
            }
        });
    }
}