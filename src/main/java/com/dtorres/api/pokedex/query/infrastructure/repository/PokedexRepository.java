package com.dtorres.api.pokedex.query.infrastructure.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.api.pokedex.query.domain.dao.DaoGetPokemonByName;
import com.dtorres.api.pokedex.query.domain.model.PokemonGeneral;
import com.dtorres.api.pokedex.query.infrastructure.rest.client.service.internal.RestClientPokedexService;
import com.dtorres.api.pokedex.query.infrastructure.rest.model.PokemonResponse;
import com.dtorres.api.pokedex.query.infrastructure.rest.model.PokemonTypeResponse;
import com.dtorres.api.pokedex.query.infrastructure.utils.PokedexAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class PokedexRepository implements DaoGetPokemonByName {

    private final RestClientPokedexService service;

    public PokedexRepository(RestClientPokedexService service) {
        this.service = service;
    }

    @Override
    public CompletionStage<PokemonGeneral> findByName(String name) {
        PokemonResponse response = service.findByName(name);
        return supplyAsync(() -> PokedexAssembler.convertToPokemonGeneral(response));
    }

}
