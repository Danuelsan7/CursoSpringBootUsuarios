package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired //automátocamente crea un objeto y la guarda en variable
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login",method =  RequestMethod.POST) //define la url
    public String login(@RequestBody Usuario usuario){
        //return List.of("1","2","3");

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

       if (usuarioLogueado != null){
           //crear jwt
          String tokenJwt =  jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());
           return tokenJwt;
       }
       return "FAIL";
    }

    private String helloWorld(){
        return "Hello World";
    }

    //Agrega Comentario prueba a AuthController
}
