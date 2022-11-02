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

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ed = "10 ";

        User user = new User();
        user.edad = Integer.parseInt(ed);


        TextView tvSaludo = findViewById(R.id.tvSaludo);
        EditText etSaludo = findViewById(R.id.etSaludo);

//        Log.i("MAIN_APP", sharedPreferences.getString("AUTHORIZATION", null));

        Retrofit retrofit = new RetrofitFactory(this).build();
        UserService service = retrofit.create(UserService.class);

        service.all().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("MAIN_APP", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("MAIN_APP", "FAIlED");
                Log.e("MAIN_APP", t.toString());
            }
        });




        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };


//        Retrofit retrofit = RetrofitFactory.build("abc");





        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String text = etSaludo.getText().toString();
//                tvSaludo.setText(text);
//                Log.d("MAIN_APP", "Hola Mundo desde implementación en variable");

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                intent.putExtra("NOMBRE", "Luis");
                intent.putExtra("APELLIDO", "Mendoza");
                intent.putExtra("SALUDO", etSaludo.getText().toString());

                startActivity(intent);

            }
        });
    }


}