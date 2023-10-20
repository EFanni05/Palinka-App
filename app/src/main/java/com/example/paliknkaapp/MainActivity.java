package com.example.paliknkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rogzit;
    private Button keres;
    private Button kiir;
    private TextView text;
    private DB_Palinka database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        rogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Rogzit.class);
                startActivity(i);
                finish();
            }
        });
        keres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Keres.class);
                startActivity(i);
                finish();
            }
        });
        kiir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lekerdezes();
            }
        });
    }

    public void lekerdezes(){
        Cursor adatok = database.lekerdezes();
        if(adatok == null){
            Toast.makeText(this, "Hiba történt a lekérdezés közben", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(adatok.getCount() == 0){
            Toast.makeText(this, "Még nincs adat felvéve", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            StringBuilder builder = new StringBuilder();
            while (adatok.moveToNext()){
                builder.append("ID: ").append(adatok.getInt(0)).append("\n")
                        .append("Főző: ").append(adatok.getInt(1)).append("\n")
                        .append("Gyümölcs: ").append(adatok.getInt(2)).append("\n")
                        .append("Alkoholtartalom: ").append(adatok.getInt(3)).append("%\n\n");
            }
            text.setText(builder);
        }
    }
    private void init(){
        rogzit = findViewById(R.id.felvetelmain);
        keres = findViewById(R.id.kereslmain);
        kiir = findViewById(R.id.lista);
        text = findViewById(R.id.listatext);
        database = new DB_Palinka(MainActivity.this);
    }
}