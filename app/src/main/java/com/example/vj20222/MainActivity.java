package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvSaludo = findViewById(R.id.tvSaludo);
        EditText etSaludo = findViewById(R.id.etSaludo);


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

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