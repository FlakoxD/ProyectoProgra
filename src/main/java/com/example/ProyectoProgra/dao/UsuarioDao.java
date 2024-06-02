package com.example.ProyectoProgra.dao;

import com.example.ProyectoProgra.modelos.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuario();

    void remover(Long id);

    void registrar(Usuario usuario);

    Usuario verificarCredenciales(Usuario usuario);
}
