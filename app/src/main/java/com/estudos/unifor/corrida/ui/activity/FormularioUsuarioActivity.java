package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.estudos.unifor.corrida.R;
import com.estudos.unifor.corrida.dao.UsuarioDAO;
import com.estudos.unifor.corrida.model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        setTitle("Inscrever-se");

        final UsuarioDAO dao = new UsuarioDAO();

        final TextView campoNome = findViewById(R.id.activity_formulario_usuario_nome);
        final View campoFeminino = findViewById(R.id.activity_formulario_usuario_genero_feminino);
        final View campoMasculino = findViewById(R.id.activity_formulario_usuario_genero_masculino);
        final TextView campoEmail = findViewById(R.id.activity_formulario_usuario_email);
        final TextView campoSenha = findViewById(R.id.activity_formulario_usuario_senha);
        final TextView campoTelefone = findViewById(R.id.activity_formulario_usuario_telefone);
        final TextView campoDisciplina = findViewById(R.id.activity_formulario_usuario_disciplina);
        final TextView campoTurma = findViewById(R.id.activity_formulario_usuario_turma);


        Button botaoSalvar = findViewById(R.id.activity_formulario_usuario_botao_registrar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nome = campoNome.getText().toString();

               String email = campoEmail.getText().toString();
               String senha = campoSenha.getText().toString();
               String telefone = campoTelefone.getText().toString();
               String disciplina = campoDisciplina.getText().toString();
               String turma = campoTurma.getText().toString();

               Usuario usuarioCriado = new Usuario(nome, email, senha);
               dao.salva(usuarioCriado);

               finish();

            }
        });
    }
}
