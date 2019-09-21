package com.estudos.unifor.corrida.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.estudos.unifor.corrida.R;
import com.estudos.unifor.corrida.dao.RoomUsuarioDAO;
import com.estudos.unifor.corrida.dao.UsuarioDAO;
import com.estudos.unifor.corrida.database.CorridaDatabase;
import com.estudos.unifor.corrida.model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    private RoomUsuarioDAO dao;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        setTitle("Inscrever-se");
        inicializacaoDosCampos();
        carregaUsuario();

        CorridaDatabase database = Room
                .databaseBuilder(this, CorridaDatabase.class, "corrida.db")
                .allowMainThreadQueries()
                .build();

        dao = database.getRoomUsuarioDAO();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_usuario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_usuario_botao_registrar){
            finalizaFormulario();
        };
        return super.onOptionsItemSelected(item);

    }

    private void carregaUsuario(){
        usuario = new Usuario();
    }

    private void inicializacaoDosCampos(){
        campoNome = findViewById(R.id.activity_formulario_usuario_nome);
        campoEmail = findViewById(R.id.activity_formulario_usuario_email);
        campoSenha = findViewById(R.id.activity_formulario_usuario_senha);
    }

    private void preencheCampos(){
        campoNome.setText(usuario.getNome());
        campoEmail.setText(usuario.getEmail());
        campoSenha.setText(usuario.getSenha());
    }

    private void preencheUsuario(){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
    }

    private void finalizaFormulario(){
        preencheUsuario();
        dao.salva(usuario);
        finish();
    }

}
