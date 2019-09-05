package com.estudos.unifor.corrida.model;

public class Usuario {
    private final String nome;
    private final String email;
    private final String senha;
    private final String telefone;
    private final String disciplina;
    private final String turma;

    public Usuario(String nome, String email, String senha, String telefone, String disciplina, String turma) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.disciplina = disciplina;
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getTurma() {
        return turma;
    }
}
