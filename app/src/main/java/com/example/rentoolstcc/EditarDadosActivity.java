package com.example.rentoolstcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class EditarDadosActivity extends AppCompatActivity {
    private ImageView imagemVoltar;
    private FirebaseAuth mAuth;
    FirebaseFirestore conexao = FirebaseFirestore.getInstance(); // Firebase Cloud Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados);

        mAuth = FirebaseAuth.getInstance();

        imagemVoltar = findViewById(R.id.imagemVoltar);
        imagemVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EditarDadosActivity.this, PerfilActivity.class);
                startActivity(it);
                finish();
            }
        });

        EditText edAtualizarNome = findViewById(R.id.edAtualizarNome);
        Button btnAtualizarNome = findViewById(R.id.btnAtualizarNome);

            btnAtualizarNome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("usuarios");
                    HashMap<String,Object> userMap = new HashMap<>();
                    userMap.put("nome",edAtualizarNome.getText().toString());
                    Toast.makeText(EditarDadosActivity.this, "sucesso", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
    }


}