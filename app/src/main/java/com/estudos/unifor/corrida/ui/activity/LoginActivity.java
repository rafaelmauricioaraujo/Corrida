package com.estudos.unifor.corrida.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.estudos.unifor.corrida.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log in");

    }
}
