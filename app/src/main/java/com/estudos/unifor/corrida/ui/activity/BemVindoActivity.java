package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.estudos.unifor.corrida.R;

public class BemVindoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        Toast.makeText(this, "Bem Vindo", Toast.LENGTH_LONG).show();
    }
}
