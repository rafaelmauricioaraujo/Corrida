package com.estudos.unifor.corrida.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estudos.unifor.corrida.R;
import com.estudos.unifor.corrida.dao.RoomUsuarioDAO;
import com.estudos.unifor.corrida.dao.UsuarioDAO;
import com.estudos.unifor.corrida.database.CorridaDatabase;
import com.estudos.unifor.corrida.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class FormularioUsuarioActivity extends AppCompatActivity {

    private Button btnFoto;
    private ImageView image;
    private String caminhoDoArquivo;

    private RoomUsuarioDAO dao;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private Usuario usuario;
    private Button btnRgistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        setTitle("Inscrever-se");

        btnFoto = findViewById(R.id.activity_registrar_botao_foto);
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFotoAction();
            }
        });
        image = findViewById(R.id.activity_registrar_imageView);


        inicializacaoDosCampos();
        carregaUsuario();

        CorridaDatabase database = Room
                .databaseBuilder(this, CorridaDatabase.class, "corrida.db")
                .allowMainThreadQueries()
                .build();

        dao = database.getRoomUsuarioDAO();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(resultCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoDoArquivo);
                image.setImageBitmap(bitmap);
            }
        }
    }

    private void tirarFotoAction() {
        Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tirarFoto.resolveActivity(getPackageManager()) != null) {
            File arquivoFoto = null;
            arquivoFoto = criarArquivo();

            if(arquivoFoto != null){
                caminhoDoArquivo = arquivoFoto.getAbsolutePath();
                Uri fotoUri = FileProvider.getUriForFile(FormularioUsuarioActivity.this, "qweqwe", arquivoFoto);

                tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(tirarFoto, 1);
            }
        }
    }

    private File criarArquivo() {
        String nome = new SimpleDateFormat("yyyyMmdd HHmmss").format(new Date());
        File diretorio = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File foto = null;
        try {
            foto = File.createTempFile(nome, ".jpg", diretorio);
        } catch (IOException e) {
            Log.d("log", "Erro : " + e.toString());
        }
        return foto;
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
        Toast.makeText(this, "usuario: " + usuario.getNome() + " salvo", Toast.LENGTH_LONG).show();
        finish();
    }

}
