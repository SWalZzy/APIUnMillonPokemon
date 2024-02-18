package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Usuario;
import com.ada.proyectofinal.services.ServicioUsuario;
import com.ada.proyectofinal.services.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ControllerUsuario {
    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<String> checkLogIn(@RequestBody Usuario usuario){
        if(servicioUsuario.checkUser(usuario.getUsername(),usuario.getPassword())){
            return new ResponseEntity<>(tokenGenerator.generateToken(usuario.getUsername()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> addUser(@RequestBody Usuario usuario){
        if(servicioUsuario.save(usuario)) {
            return new ResponseEntity<>(tokenGenerator.generateToken(usuario.getUsername()),HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
