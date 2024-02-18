package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryResultado extends JpaRepository<Resultado, Integer> {

}
