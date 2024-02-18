package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAlineacion extends JpaRepository<Alineacion, Integer>{
    @Query(value = "SELECT * FROM alineacion WHERE id = :id", nativeQuery = true)
    Alineacion findById(int id);
}
