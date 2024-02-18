package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Batalla;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Resultado;
import com.ada.proyectofinal.repositories.RepositoryBatalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioBatalla {

    @Autowired
    RepositoryBatalla repositoryBatalla;

    public List<Batalla> findAll(){
        return repositoryBatalla.findAll();
    }

    public Batalla findById(int id){
        return repositoryBatalla.findById(id);
    }

    public void save(Batalla batalla){
        repositoryBatalla.save(batalla);
    }

    public void deleteById(int id){
        repositoryBatalla.deleteById(id);
    }

    public void deleteAll(){
        repositoryBatalla.deleteAll();
    }
}
