package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.entities.Resultado;
import com.ada.proyectofinal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ControllerBatalla {

    @Autowired
    private ServicioEntrenador servicioEntrenador;

    @Autowired
    private ServicioResultado servicioResultado;

    @GetMapping("/batalla")
    public List<Entrenador> devolverEntrenadoresParaBatalla() {
        List<Entrenador> entrenadores = servicioEntrenador.recuperarBots();
        List<Pokemon> pokemons;
        List<Entrenador> entrenadoresParBatalla = new ArrayList<>();
        for (Entrenador entrenador : entrenadores) {
            pokemons = entrenador.getPokemons();
            for (Pokemon pokemon : pokemons) {
                pokemon.setAlineacion(null);
            }
            entrenadoresParBatalla.add(new Entrenador(entrenador.getId(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getIcono(), entrenador.getDinero(), pokemons, entrenador.getResultados(), entrenador.getUsuario()));
        }
        return entrenadoresParBatalla;
    }

    @PostMapping("/batalla/{idEntrenador}")
    public Resultado batallar(@PathVariable("idEntrenador") int idEntrenador) {
        Entrenador entrenador = servicioEntrenador.findById(idEntrenador);
        List<Pokemon> pokemonsEntrenador = entrenador.getPokemons();
        //Alineacion alineacion = entrenador.getPokemons().get(0).getAlineacion();
        Entrenador entrenador1 = servicioEntrenador.recuperarEntrenador1();
        List<Pokemon> pokemonsEntrenador1 = entrenador1.getPokemons();
        //Alineacion alineacion1 = entrenador1.getPokemons().get(0).getAlineacion();
        Resultado resultado = new Resultado();
        Random random = new Random();
        int dadoEntrenador = random.nextInt(20) + 1;
        int dadoEntrenador1 = random.nextInt(20) + 1;
        if (dadoEntrenador > dadoEntrenador1) {
            resultado.setGanador(entrenador.getNombre());
            resultado.setPerdedor(entrenador1.getNombre());
            resultado.setEntrenador(entrenador);
            resultado.getEntrenador().setPokemons(pokemonsEntrenador);
            entrenador.setDinero(entrenador.getDinero() + entrenador.getDinero() * 0.20f);
            entrenador1.setDinero(entrenador1.getDinero() + entrenador1.getDinero() * 0.08f);
            servicioEntrenador.save(entrenador);
            servicioResultado.save(resultado);
        } else if (dadoEntrenador < dadoEntrenador1) {
            resultado.setGanador(entrenador1.getNombre());
            resultado.setPerdedor(entrenador.getNombre());
            resultado.setEntrenador(entrenador1);
            resultado.getEntrenador().setPokemons(pokemonsEntrenador1);
            entrenador1.setDinero(entrenador1.getDinero() + entrenador1.getDinero() * 0.20f);
            entrenador.setDinero(entrenador.getDinero() + entrenador.getDinero() * 0.08f);
            servicioEntrenador.save(entrenador1);
            servicioResultado.save(resultado);
        } else {
            resultado.setEmpate(true);
        }
        return resultado;
    }
}
