package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.repositories.RepositoryAlineacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioAlineacion {
    @Autowired
    RepositoryAlineacion repositoryAlineacion;

    public List<Alineacion> findAll(){
        return repositoryAlineacion.findAll();
    }
    public Alineacion findById(int id){
        return repositoryAlineacion.findById(id);
    }
    public void save(Alineacion alineacion){
        repositoryAlineacion.save(alineacion);
    }
    public void deleteById(int id){
        repositoryAlineacion.deleteById(id);
    }
    public void deleteAll(){
        repositoryAlineacion.deleteAll();
    }

}
