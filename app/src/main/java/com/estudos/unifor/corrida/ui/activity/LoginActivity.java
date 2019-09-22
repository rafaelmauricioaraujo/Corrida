package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log in");
    }

    public void registrar(View view){
        Intent intent = new Intent(this, FormularioUsuarioActivity.class);
        startActivity(intent);
    }

    public void login(View view){

        TextView campoEmail = findViewById(R.id.activity_login_email);
        TextView campoSenha = findViewById(R.id.activity_login_senha);

        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();

        Intent intent = new Intent(this, BemVindoActivity.class);

        CorridaDatabase database = Room
                .databaseBuilder(this, CorridaDatabase.class, "corrida.db")
                .allowMainThreadQueries()
                .build();

        dao = database.getRoomUsuarioDAO();

        List<Usuario> usuarios = dao.getUsuarioByNome(email, senha);
        Usuario usuario = new Usuario();
        usuario = usuarios.get(0);


        if(usuario.getEmail() == email & usuario.getSenha() == senha){
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuário: " + email + "ou senha não encontrados", Toast.LENGTH_LONG).show();
        }
    }
}
