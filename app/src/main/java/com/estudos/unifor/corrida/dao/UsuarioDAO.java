package com.estudos.unifor.corrida.dao;

import com.estudos.unifor.corrida.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final static List<Usuario> usuarios = new ArrayList<>();

    public void salva(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> todos() {
        return new ArrayList<>(usuarios);
    }

}
