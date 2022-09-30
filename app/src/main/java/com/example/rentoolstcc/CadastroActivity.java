package com.example.rentoolstcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadastroNome, edtCadastroCpf, edtCadastroEmail, edtCadastroSenha;
    private FirebaseAuth mAuth; // Firebase Authentication
    FirebaseFirestore conexao = FirebaseFirestore.getInstance(); // Firebase Cloud Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText edtCadastroNome = findViewById(R.id.edtCadastroNome);
        EditText edtCadastroCpf = findViewById(R.id.edtCadastroCpf);
        EditText edtCadastroEmail = findViewById(R.id.edtCadastroEmail);
        EditText edtCadastroSenha = findViewById(R.id.edtCadastroSenha);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        mAuth = FirebaseAuth.getInstance(); // Authentication

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioModel usuario = new UsuarioModel(edtCadastroNome.getText().toString(), edtCadastroCpf.getText().toString(), edtCadastroEmail.getText().toString(), edtCadastroSenha.getText().toString());

                String nome = edtCadastroNome.getText().toString();
                String cpf = edtCadastroCpf.getText().toString();
                String email = edtCadastroEmail.getText().toString();
                String senha = edtCadastroSenha.getText().toString();


                if (edtCadastroNome.getText().toString().isEmpty() ||
                        edtCadastroCpf.getText().toString().isEmpty() ||
                        edtCadastroEmail.getText().toString().isEmpty() ||
                        edtCadastroSenha.getText().toString().isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Preencha todos com campos!", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                                startActivity(it);
                                finish();
                            }
                        }
                    });
                    conexao.collection("usuarios")
                            .add(usuario)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CadastroActivity.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(CadastroActivity.this, "Erro ao registrar.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(CadastroActivity.this, "Erro ao conectar", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}