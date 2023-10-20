package com.example.paliknkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Rogzit extends AppCompatActivity {

    private EditText fozo;
    private EditText gyumolcs;
    private EditText alkohol;
    private Button add;
    private Button back;
    private DB_Palinka database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzit);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Rogzit.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fozo = fozo.getText().toString();
                String Gyumocs = gyumolcs.getText().toString();
                String Alkohol = alkohol.getText().toString();
                if (Fozo.isEmpty()){
                    Toast.makeText(Rogzit.this, "Üres mező: Főző", Toast.LENGTH_SHORT).show();
                } else if (Gyumocs.isEmpty()) {
                    Toast.makeText(Rogzit.this, "Üres mező: Gyümölcs", Toast.LENGTH_SHORT).show();
                } else if (Alkohol.isEmpty()) {
                    Toast.makeText(Rogzit.this, "Üres mező: Alkoholszázalék", Toast.LENGTH_SHORT).show();
                }
                else{
                    int a = Integer.parseInt(Alkohol);
                    if(database.Add(Fozo, Gyumocs, a)){
                        Toast.makeText(Rogzit.this, "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Rogzit.this, "Nem sikerült az adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        fozo = findViewById(R.id.fozo);
        gyumolcs = findViewById(R.id.gyumolcs);
        alkohol = findViewById(R.id.alkohol);
        add = findViewById(R.id.add);
        back = findViewById(R.id.backrogzit);
        database = new DB_Palinka(Rogzit.this);
    }
}