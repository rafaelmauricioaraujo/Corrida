package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.estudos.unifor.corrida.R;
import com.estudos.unifor.corrida.dao.RoomUsuarioDAO;
import com.estudos.unifor.corrida.dao.UsuarioDAO;
import com.estudos.unifor.corrida.database.CorridaDatabase;
import com.estudos.unifor.corrida.model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    private RoomUsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        setTitle("Inscrever-se");

        CorridaDatabase database = Room
                .databaseBuilder(this, CorridaDatabase.class, "corrida.db")
                .allowMainThreadQueries()
                .build();

        dao = database.getRoomUsuarioDAO();

        final TextView campoNome = findViewById(R.id.activity_formulario_usuario_nome);
        final TextView campoEmail = findViewById(R.id.activity_formulario_usuario_email);
        final TextView campoSenha = findViewById(R.id.activity_formulario_usuario_senha);

        Button botaoSalvar = findViewById(R.id.activity_formulario_usuario_botao_registrar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String nome = campoNome.getText().toString();
               String email = campoEmail.getText().toString();
               String senha = campoSenha.getText().toString();

               Usuario usuarioCriado = new Usuario(nome, email, senha);
               dao.salva(usuarioCriado);

               finish();
            }
        });
    }
}
