package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAnimeActivity extends AppCompatActivity {


    private Button btnGoAnimeLista;
    private Button btnGoAnimeForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_anime);

        btnGoAnimeLista = findViewById(R.id.btnGoAnimeLista);
        btnGoAnimeForm = findViewById(R.id.btnGoAnimeForm);

        btnGoAnimeLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAnimeActivity.this, ListaAnimeActivity.class);
                startActivity(intent);
            }
        });

        btnGoAnimeForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAnimeActivity.this, FormAnimeActivity.class);
                startActivity(intent);
            }
        });

    }
}