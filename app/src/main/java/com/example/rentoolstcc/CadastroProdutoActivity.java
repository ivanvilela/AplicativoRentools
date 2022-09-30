package com.example.rentoolstcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadastroProdutoActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore conexaoDB = FirebaseFirestore.getInstance();

    private EditText edtNome, edtPreco, edtCodigo;
    private RadioGroup rgGrupo;
    private RadioButton rbPneumatica, rbMecanica, rbEletrica;
    private Button btnCadastrar, btnListar, btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtPreco = findViewById(R.id.edtPreco);
        rgGrupo = findViewById(R.id.rgGrupo);
        rbPneumatica = findViewById(R.id.rbPneumatica);
        rbEletrica = findViewById(R.id.rbEletrica);
        rbMecanica = findViewById(R.id.rbMecanica);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnListar = findViewById(R.id.btnListar);


        //EVENTO BOTÃO CADASTRAR EQUIPAMENTOS - ADMINISTRADOR
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int itemRadioGroupSelecionado = rgGrupo.getCheckedRadioButtonId();
                String opcao;

                if (edtCodigo.getText().toString().isEmpty() || edtNome.getText().toString().isEmpty() ||
                        edtPreco.getText().toString().isEmpty() ||
                        rgGrupo.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(CadastroProdutoActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {

                    RadioButton rbCategoriaSelecionada = findViewById(itemRadioGroupSelecionado);
                    opcao = rbCategoriaSelecionada.getText().toString();

                    Produto produto = new Produto(Integer.parseInt(edtCodigo.getText().toString()),
                            edtNome.getText().toString(),
                            opcao,
                            Double.parseDouble(edtPreco.getText().toString()));

                    conexaoDB.collection("produtos")
                            .document(edtCodigo.getText().toString())
                            .set(produto)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CadastroProdutoActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(CadastroProdutoActivity.this, "Erro ao casdastrar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(CadastroProdutoActivity.this, "Erro ao cadastar", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            });

                    //EVENTO BOTÃO LISTAR EQUIPAMENTOS - ADMINISTRADOR
                    btnListar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent it = new Intent(CadastroProdutoActivity.this, ListaProdutos.class);
                            startActivity(it);
                        }
                    });

                    //EVENTO BOTÃO SAIR TELA ADMINISTRADOR
                    btSair.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(CadastroProdutoActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
    }
}