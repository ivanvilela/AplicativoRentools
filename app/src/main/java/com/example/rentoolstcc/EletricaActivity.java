package com.example.rentoolstcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EletricaActivity extends AppCompatActivity {


    private ImageView imageVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eletrica);


        imageVoltar = findViewById(R.id.imageVoltar);
        imageVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EletricaActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });


    }
}