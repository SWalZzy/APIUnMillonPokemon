package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Mercado;
import com.ada.proyectofinal.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMercado extends JpaRepository<Mercado, Integer> {

    @Query(value = "SELECT * FROM mercado WHERE id = :id", nativeQuery = true)
    Mercado findById(int id);
}
