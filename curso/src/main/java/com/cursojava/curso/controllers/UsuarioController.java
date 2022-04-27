package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UsuarioController {

    @Autowired //automátocamente crea un objeto y la guarda en variable
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}",method =  RequestMethod.GET) //define la url
   public Usuario getUsuario(@PathVariable Long id){
       //return List.of("1","2","3");
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Daniel");
        usuario.setApellido("Moreno");
        usuario.setEmail("daniel.moreno@gmail.com");
        usuario.setTelefono("4-13-11-31");
        usuario.setPassword("holaMundo");
        return  usuario;
   }

    @RequestMapping(value = "api/usuarios",method =  RequestMethod.GET ) //define la url
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        //return List.of("1","2","3");
        if(!validarToken(token)){
            return  null;
        }
          return usuarioDao.getUsuarios();
    }


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
            return  usuarioId != null;

    }


    @RequestMapping(value = "api/usuarios",method =  RequestMethod.POST) //define la url
    public void registrarUsuarios(@RequestBody Usuario usuario){
        //return List.of("1","2","3");

        //Argon2 argon2 =
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);


         usuarioDao.registrar(usuario);
    }


    @RequestMapping(value = "api/usuarioeew")
    public Usuario editar(){
        //return List.of("1","2","3");
        Usuario usuario = new Usuario();
        usuario.setNombre("Daniel");
        usuario.setApellido("Moreno");
        usuario.setEmail("daniel.moreno@gmail.com");
        usuario.setTelefono("4-13-11-31");
        usuario.setPassword("holaMundo");
        return  usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}",method =  RequestMethod.DELETE) //define la url
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){
        if(!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuariossss")
    public Usuario buscar(){
        //return List.of("1","2","3");
        Usuario usuario = new Usuario();
        usuario.setNombre("Daniel");
        usuario.setApellido("Moreno");
        usuario.setEmail("daniel.moreno@gmail.com");
        usuario.setTelefono("4-13-11-31");
        usuario.setPassword("holaMundo");
        return  usuario;
    }
}
