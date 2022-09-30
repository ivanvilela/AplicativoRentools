package com.example.rentoolstcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PneumaticaActivity extends AppCompatActivity {
private ImageView imagemVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneumatica);

        imagemVoltar = findViewById(R.id.imagemVoltar);
        imagemVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PneumaticaActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}