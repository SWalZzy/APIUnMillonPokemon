package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Usuario;
import com.ada.proyectofinal.repositories.RepositoryUsuario;
import com.ada.proyectofinal.utils.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {
    @Autowired
    RepositoryUsuario repositoryUsuario;

    public boolean checkUser(String username, String password) {
        Usuario usuario = null;
        usuario = repositoryUsuario.checkUser(username, password);
        return usuario != null;
    }

    public List<Usuario> findAll() {
        return repositoryUsuario.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return repositoryUsuario.findById(id);
    }

    public void saveAll(List<Usuario> usuarios) {
        repositoryUsuario.saveAll(usuarios);
    }

    public boolean save(Usuario usuario) {
        if(!usuario.getUsername().equals("")|| usuario.getPassword().equals("")) {
            Usuario usuarioEncontrado = repositoryUsuario.checkIfUserExist(usuario.getPassword());
            if (usuarioEncontrado == null) {
                repositoryUsuario.save(usuario);
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public void deleteById(int id) {
        repositoryUsuario.deleteById(id);
    }

    public void deleteAll() {
        repositoryUsuario.deleteAll();
    }
}
