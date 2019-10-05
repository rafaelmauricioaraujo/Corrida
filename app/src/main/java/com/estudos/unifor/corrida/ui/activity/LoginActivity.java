package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.estudos.unifor.corrida.R;
import com.estudos.unifor.corrida.dao.RoomUsuarioDAO;
import com.estudos.unifor.corrida.database.CorridaDatabase;
import com.estudos.unifor.corrida.model.Usuario;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private RoomUsuarioDAO dao;
    private String email;
    private String senha;
    private Button btnLogin;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log in");

        CorridaDatabase database = Room
                .databaseBuilder(this, CorridaDatabase.class, "corrida.db")
                .allowMainThreadQueries()
                .build();

        dao = database.getRoomUsuarioDAO();

        btnLogin = findViewById(R.id.activity_login_botao_entrar);
        btnRegistrar = findViewById(R.id.activity_login_botao_registrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "Clicou no login", Toast.LENGTH_LONG).show();
                login(view);
            }
        });
    }

    public void registrar(View view){
        Intent intent = new Intent(this, FormularioUsuarioActivity.class);
        startActivity(intent);
    }

    public void login(View view){

        EditText campoEmail = (EditText) findViewById(R.id.activity_login_email);
        EditText campoSenha = (EditText) findViewById(R.id.activity_login_senha);

        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();

        Intent BemVindoIntent = new Intent(this, BemVindoActivity.class);
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        Intent maps = new Intent(this, MapsActivity.class);

        List<Usuario> usuarios = dao.getTodos();

        for(int i = 0; i < usuarios.size(); i++){

            Usuario usuario = usuarios.get(i);

            if(usuario.getEmail().equals(email) & usuario.getSenha().equals(senha)){
                startActivity(maps);
            }else{
                Toast.makeText(this, "usuario ou senha não encontrados ", Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }
}
