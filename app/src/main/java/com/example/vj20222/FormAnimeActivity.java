package com.example.vj20222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vj20222.entities.Anime;
import com.example.vj20222.entities.Image;
import com.example.vj20222.entities.ImageResponse;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;
import com.example.vj20222.services.ImageService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormAnimeActivity extends AppCompatActivity {

    private final static int CAMERA_REQUEST = 1000;

    private Anime anime = new Anime();
    private EditText etFormAnimeName;
    private EditText etFormAnimeDescription;
    private EditText etFormAnimePosterURL;
    private Button btnSaveAnime;
    private Button btnTakePhoto;
    private Button btnOpenGallery;

    private ImageView ivPhoto;

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anime);

        etFormAnimeName = findViewById(R.id.etFormAnimeName);
        etFormAnimeDescription = findViewById(R.id.etFormAnimeDescription);
        etFormAnimePosterURL = findViewById(R.id.etFormAnimePosterURL);

//        etFormAnimePosterURL.setText("https://m.media-amazon.com/images/I/710NAj9qNVL._AC_UY1000_.jpg");

        btnSaveAnime = findViewById(R.id.btnSaveAnime);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        btnOpenGallery = findViewById(R.id.btnOpenGallery);

        ivPhoto = findViewById(R.id.ivPhoto);

        retrofit = new RetrofitFactory(this).build("","");

        Intent intent  = getIntent();
        String animeJson = intent.getStringExtra("ANIME_DATA");

        Log.i("MAIN_APP", "animeJson:" + animeJson);

        if (animeJson != null) {
            anime = new Gson().fromJson(animeJson, Anime.class);
            etFormAnimeName.setText(anime.name);
            etFormAnimeDescription.setText(anime.description);
            etFormAnimePosterURL.setText(anime.posterURL);
        }

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // preguntar si tiene permisos
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    abrirCamara();
                } else {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, 100);
                }
                // pediro los permisios
            }
        });

        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
                }
            }
        });

        btnSaveAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnime(anime);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPhoto.setImageBitmap(imageBitmap);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();

            String imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);


            Retrofit retrofit = new RetrofitFactory(this)
                    .build("https://api.imgur.com", "Client-ID 8bcc638875f89d9");

            ImageService imageService = retrofit.create(ImageService.class);
            Image image = new Image();
            image.image = imgBase64;

            imageService.sendImage(image).enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    ImageResponse r = response.body();

                  //gurdarPelicula()

                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {

                }
            });




        }

        if(requestCode == 1001) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap imageBitmap = BitmapFactory.decodeFile(picturePath);
            ivPhoto.setImageBitmap(imageBitmap);


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();

            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Log.i("MAIN_APP", encoded);



        }

    }

    private void GuardarPelicula() {
        Retrofit r2 = new RetrofitFactory(FormAnimeActivity.this).build("http/api", "");

        AnimeService s = r2.create(AnimeService.class);

        s.create(new Anime()).enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {

            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);

    }


    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST); //requestCode numero cualquiera
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