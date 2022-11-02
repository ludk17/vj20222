package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.vj20222.adapters.AnimeAdapter;
import com.example.vj20222.entities.Anime;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaAnimeActivity extends AppCompatActivity {

    private RecyclerView rvAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anime);

        rvAnime = findViewById(R.id.rvAnime);
        rvAnime.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);

        service.get().enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {

                rvAnime.setAdapter(new AnimeAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Log.e("MAIN_APP", t.toString());
            }
        });


    }
}