package com.estudos.unifor.corrida.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.estudos.unifor.corrida.dao.RoomUsuarioDAO;
import com.estudos.unifor.corrida.dao.UsuarioDAO;
import com.estudos.unifor.corrida.model.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class CorridaDatabase extends RoomDatabase {

    public abstract RoomUsuarioDAO getRoomUsuarioDAO();

}
