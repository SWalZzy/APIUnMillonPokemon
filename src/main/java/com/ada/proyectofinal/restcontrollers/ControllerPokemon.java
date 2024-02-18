package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioPokemon;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class ControllerPokemon {
    @Autowired
    private ServicioPokemon servicioPokemon;

    @GetMapping
    public List<Pokemon> showAllPokemons(){
        return servicioPokemon.findAll();
    }

    @GetMapping("/entrenador/{id}")
    public List<Pokemon> showTrainerPokemons(@PathVariable("id") int id){
        return servicioPokemon.getPokemonsByEntrenadorId(id);
    }

    @GetMapping("/{id}")
    public Optional<Pokemon> showPokemon(@PathVariable("id") int id){
        return servicioPokemon.findById(id);
    }
}
