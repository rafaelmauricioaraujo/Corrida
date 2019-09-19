package com.estudos.unifor.corrida.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.estudos.unifor.corrida.model.Usuario;

@Dao
public interface RoomUsuarioDAO {
    
    @Insert
    void salva(Usuario usuario);
}
