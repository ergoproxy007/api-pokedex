package com.dtorres.api.pokedex.query.infrastructure.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.dtorres.api.pokedex.commons.response.ResponseDTO;
import com.dtorres.api.pokedex.query.application.handler.HandlerPokemonFindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api")
public class PokemonQueryController {

    private static final String POKEMONS_ROUTE = "/pokemons/{name}";

    private final HandlerPokemonFindByName handlerPokemonFindByName;

    @Autowired
    public PokemonQueryController(HandlerPokemonFindByName handlerPokemonFindByName) {
        this.handlerPokemonFindByName = handlerPokemonFindByName;
    }

    @GetMapping(value = POKEMONS_ROUTE,
                consumes = APPLICATION_JSON_VALUE,
                produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> findByName(
            @PathVariable(name = "name", required = true) String name,
            @RequestParam(defaultValue = "Y", required = false) String queryAbility) {
        return ok(handlerPokemonFindByName.execute(name, queryAbility));
    }
}
