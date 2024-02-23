package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioPokemon;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class ControllerPokemon {
    @Autowired
    private ServicioPokemon servicioPokemon;

    @GetMapping("/pokemons")
    public List<Pokemon> showAllPokemons(){
        return servicioPokemon.findAll();
    }

    @GetMapping("/entrenador/{id}")
    public List<Pokemon> showTrainerPokemons(@PathVariable("id") int id){
        return servicioPokemon.getPokemonsByEntrenadorId(id);
    }
    @GetMapping("/pokemon/{id}")
    public Pokemon showPokemon(@PathVariable("id") int id){
        return servicioPokemon.findById(id);
    }

    @PutMapping("/update/{id}")
    public void updatePokemon(@PathVariable("id") int id, @RequestBody Pokemon pokemon){
        Pokemon pokemonToUpdate = servicioPokemon.findById(id);
        pokemonToUpdate.setName(pokemon.getName());
        pokemonToUpdate.setType(pokemon.getType());
        pokemonToUpdate.setLevel(pokemon.getLevel());
        pokemonToUpdate.setHp(pokemon.getHp());
        pokemonToUpdate.setAttack(pokemon.getAttack());
        pokemonToUpdate.setDefence(pokemon.getDefence());
        pokemonToUpdate.setSpeed(pokemon.getSpeed());
        pokemonToUpdate.setGender(pokemon.getGender());
        pokemonToUpdate.setValor(pokemon.getValor());
        pokemonToUpdate.setHiresURL(pokemon.getHiresURL());
        servicioPokemon.save(pokemonToUpdate);
    }


    @PostMapping("/comprarVida/{id}")
    public void comprarVida(@PathVariable("id") int idPokemon){
        Pokemon pokemon = servicioPokemon.findById(idPokemon);
        Entrenador entrenador = pokemon.getEntrenador();
        if(pokemon.getHp() < 100 && entrenador.getDinero() >= 30000f){
            pokemon.setHp(pokemon.getHp() + 5);
            entrenador.setDinero(entrenador.getDinero() - 30000f);
            servicioPokemon.save(pokemon);
        }else{
            System.out.println("No Puedes comprar vida");
        }
    }
}
