package com.dtorres.api.pokedex.query.infrastructure.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.dtorres.api.pokedex.query.domain.dto.DtoGeneralPokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PokemonQueryController {

    private static final String POKEMONS_ROUTE = "/pokemons";

    @GetMapping(value = POKEMONS_ROUTE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DtoGeneralPokemon>> get(
            @RequestParam(required = false, defaultValue = "0") long pageIndex,
            @RequestParam(required = false, defaultValue = "10") long pageSize) {
        DtoGeneralPokemon dto = new DtoGeneralPokemon();
        dto.setName("PIKACHU");
        List<DtoGeneralPokemon> pokemons = Collections.singletonList(dto);
        return ok(pokemons);
    }
}
