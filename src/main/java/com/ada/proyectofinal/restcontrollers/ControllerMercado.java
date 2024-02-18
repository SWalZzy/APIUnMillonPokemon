package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.Parser.Parser;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Mercado;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioEntrenador;
import com.ada.proyectofinal.services.ServicioMercado;
import com.ada.proyectofinal.services.ServicioPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerMercado {
    @Autowired
    private ServicioMercado servicioMercado;

    @Autowired
    private ServicioPokemon servicioPokemon;

    @Autowired
    private ServicioEntrenador servicioEntrenador;


    @GetMapping("/mostrarMercado")
    public ResponseEntity<List<Pokemon>> mostrarMercado(){
        List<Pokemon> pokemons = servicioMercado.ListarPokemonsDelMercado();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @PostMapping("/comprarPokemon/{idPokemon}")
    public ResponseEntity<String> comprarPokemon(@PathVariable("idPokemon") int idPokemon, @RequestBody Entrenador entrenador){
        List<Pokemon> pokemons = servicioMercado.comprarPokemon();
        Pokemon pokemonComprar = new Pokemon();
        for(Pokemon pokemon : pokemons){
            if(pokemon.getId()==idPokemon){
               pokemonComprar = pokemon;
               break;
            }
        }
        if (entrenador.getDinero()<=pokemonComprar.getValor()||entrenador.getPokemons().size()>=20){
            return new ResponseEntity<>("No se pudo comprar el pokemon",HttpStatus.NOT_ACCEPTABLE);
        }else{
            entrenador.setDinero(entrenador.getDinero()-pokemonComprar.getValor());
            pokemonComprar.setMercado(null);
            pokemonComprar.setEntrenador(entrenador);
            servicioPokemon.save(pokemonComprar);
            List<Pokemon> pokemonsEntrenador = entrenador.getPokemons();
            pokemonsEntrenador.add(pokemonComprar);
            entrenador.setPokemons(pokemonsEntrenador);
            servicioEntrenador.save(entrenador);

            return new ResponseEntity<>("Se ha hecho la transaccion correctamente",HttpStatus.OK);
        }
    }
    @PostMapping("/venderPokemon/{idPokemon}")
    public ResponseEntity<String> venderPokemon(@RequestBody Entrenador entrenador, @PathVariable("idPokemon") int idPokemon){
        Pokemon pokemonVenta = null;
        for(Pokemon pokemon : entrenador.getPokemons()){
            if(pokemon.getId()==idPokemon){
                pokemon.setEntrenador(null);
                pokemonVenta = pokemon;
                break;
            }
        }
        entrenador.setDinero(entrenador.getDinero()+pokemonVenta.getValor());
        Mercado mercado = servicioMercado.findById(1);
        pokemonVenta.setMercado(mercado);
        servicioPokemon.save(pokemonVenta);
        servicioEntrenador.save(entrenador);
        return new ResponseEntity<>("Se ha vendido de manera exitosa",HttpStatus.OK);
    }
}
