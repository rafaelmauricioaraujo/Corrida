package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.estudos.unifor.corrida.R;

public class LoginActivity extends AppCompatActivity {

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
}
