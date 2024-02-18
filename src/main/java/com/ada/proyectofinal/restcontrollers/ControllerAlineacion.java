package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.DataInicializer;
import com.ada.proyectofinal.services.ServicioAlineacion;
import com.ada.proyectofinal.services.ServicioEntrenador;
import com.ada.proyectofinal.services.ServicioPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerAlineacion {
    @Autowired
    private ServicioAlineacion servicioAlineacion;
    @Autowired
    private ServicioEntrenador servicioEntrenador;
    @Autowired
    private DataInicializer dataInicializer;

    @PostMapping("/asignarAlineacion")
    public void asignarAlineacion(){
        List<Entrenador> entrenadores = servicioEntrenador.findAll();
        for(int i= 0; i < entrenadores.size(); i++){
            Entrenador entrenador = entrenadores.get(i);
            dataInicializer.asignarAlineaciones();
        }
    }

}
