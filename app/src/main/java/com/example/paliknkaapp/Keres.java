package com.example.paliknkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Keres extends AppCompatActivity {

    private EditText fozo;
    private EditText gyumolcs;
    private Button keres;
    private Button back;
    private TextView text;
    private DB_Palinka database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keres);
        init();
        keres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fozo = fozo.getText().toString();
                String Gyumolcs = gyumolcs.getText().toString();
                if (Fozo.isEmpty()){
                    Toast.makeText(Keres.this, "Üres mező: Főző", Toast.LENGTH_SHORT).show();
                } else if (Gyumolcs.isEmpty()) {
                    Toast.makeText(Keres.this, "Üres mező: Gyümölcs", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor data = database.Adatlekerdezes(Fozo, Gyumolcs);
                    if (data.getCount() == 0){
                        text.setText("A megadott adatokkal nincs találat");
                    }
                    else{
                        StringBuilder builder = new StringBuilder();
                        while (data.moveToNext()){
                            builder.append("Alkohol tartalom:").append(data.getInt(0))
                                    .append("%").append("\n");
                        }
                        text.setText(builder);
                    }
                }
            }
        });
    }

    private void init(){
        fozo = findViewById(R.id.fozokeres);
        gyumolcs = findViewById(R.id.gyumolcskeres);
        keres = findViewById(R.id.search);
        back = findViewById(R.id.backkeres);
        text = findViewById(R.id.eredmeny);
        database = new DB_Palinka(Keres.this);
    }
}