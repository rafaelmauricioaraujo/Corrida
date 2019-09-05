package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.estudos.unifor.corrida.R;

public class FormularioUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        setTitle("Inscrever-se");
    }
}
