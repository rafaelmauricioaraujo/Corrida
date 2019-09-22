package com.estudos.unifor.corrida.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.estudos.unifor.corrida.model.Usuario;

import java.util.List;

@Dao
public interface RoomUsuarioDAO {
    
    @Insert
    void salva(Usuario usuario);

    @Query("SELECT * FROM USUARIO WHERE USUARIO.email = :email AND USUARIO.senha = :senha")
    List<Usuario> getUsuarioByNome(String email, String senha);

}
