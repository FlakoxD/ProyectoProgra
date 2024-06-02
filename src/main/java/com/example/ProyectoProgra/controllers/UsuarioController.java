package com.example.ProyectoProgra.controllers;

import com.example.ProyectoProgra.dao.UsuarioDao;
import com.example.ProyectoProgra.modelos.Usuario;
import com.example.ProyectoProgra.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuarios(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Andrew");
        usuario.setApellido("Sieka");
        usuario.setEmail("andrew@gmail.com");
        usuario.setTelefono("11111111");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
     public List<Usuario> getUsuario(@RequestHeader(value ="Authorization") String token) {
        if (!validarToken(token)){
            return null;
        }

        return usuarioDao.getUsuario();
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "usuario45")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Andrew");
        usuario.setApellido("Sieka");
        usuario.setEmail("andrew@gmail.com");
        usuario.setTelefono("11111111");

        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void Remover(@PathVariable Long id, @RequestHeader(value ="Authorization") String token){
        if (!validarToken(token)){
            return ;
        }
        usuarioDao.remover(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

}
