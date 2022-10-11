package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String apellido = intent.getStringExtra("APELLIDO");
        String saludo = intent.getStringExtra("SALUDO");

        TextView tvMostrarSAludo = findViewById(R.id.tvMostrarSaludo);
        tvMostrarSAludo.setText(saludo);

    }
}