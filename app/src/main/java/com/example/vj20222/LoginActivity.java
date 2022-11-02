package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.Credentials;

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private Button btnLogin;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("com.example.vj20222.MyAPPVJ", MODE_PRIVATE);

        if (sharedPreferences.getString("AUTHORIZATION", null) != null) {
            redirectToMainActivity();
        } else {

            eUsername = findViewById(R.id.etLoginUsername);
            ePassword = findViewById(R.id.etLoginPassword);
            btnLogin = findViewById(R.id.btnLogin);

            setUpLoginButton();
        }

    }

    private void setUpLoginButton() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credentials = Credentials.basic(eUsername.getText().toString(), ePassword.getText().toString());

                SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
                sharedEditor.putString("AUTHORIZATION", credentials);
                sharedEditor.apply();

                redirectToMainActivity();


                Toast.makeText(getApplicationContext(), "Mensaje que se muestra en el TOAST", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void redirectToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}