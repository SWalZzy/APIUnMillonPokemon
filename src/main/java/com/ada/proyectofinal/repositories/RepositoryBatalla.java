package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Batalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBatalla extends JpaRepository<Batalla, Integer> {
    @Query(value = "SELECT * FROM batalla where id = :id", nativeQuery = true)
    Batalla findById(int id);
}
