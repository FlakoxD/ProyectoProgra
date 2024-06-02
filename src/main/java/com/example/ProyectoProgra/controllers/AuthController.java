package com.example.ProyectoProgra.controllers;

import com.example.ProyectoProgra.dao.UsuarioDao;
import com.example.ProyectoProgra.modelos.Usuario;
import com.example.ProyectoProgra.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLog =usuarioDao.verificarCredenciales(usuario);
        if (usuarioLog != null){

            String token = jwtUtil.create(String.valueOf(usuarioLog.getId()), usuarioLog.getEmail());

            return token;
        }
        return "FAIL";
    }

}
