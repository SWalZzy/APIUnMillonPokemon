package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario,Integer> {

    @Query(value = "SELECT * FROM usuario WHERE username = :username AND password = :password", nativeQuery = true)
    Usuario checkUser(String username, String password);

    @Query(value = "SELECT * FROM usuario WHERE password = :password",nativeQuery = true)
    Usuario checkIfUserExist(String password);
}
